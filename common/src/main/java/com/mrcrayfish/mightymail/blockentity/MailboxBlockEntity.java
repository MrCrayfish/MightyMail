package com.mrcrayfish.mightymail.blockentity;

import com.mrcrayfish.mightymail.Config;
import com.mrcrayfish.mightymail.block.MailboxBlock;
import com.mrcrayfish.mightymail.core.ModBlockEntities;
import com.mrcrayfish.mightymail.mail.DeliveryService;
import com.mrcrayfish.mightymail.mail.Mailbox;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.lang.ref.WeakReference;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: MrCrayfish
 */
public class MailboxBlockEntity extends BasicLootBlockEntity
{
    protected UUID uuid = UUID.randomUUID();
    protected WeakReference<Mailbox> mailboxRef;

    public MailboxBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.MAIL_BOX.get(), pos, state, Config.SERVER.mailboxInventoryRows.get() * 9);
    }

    public UUID getId()
    {
        return this.uuid;
    }

    public boolean deliverItem(ItemStack mail)
    {
        for(int i = 0; i < this.getContainerSize(); i++)
        {
            ItemStack stack = this.getItem(i);
            if(stack.isEmpty())
            {
                this.setItem(i, mail);
                this.setUnchecked();
                return true;
            }
            if(stack.getCount() == stack.getMaxStackSize())
            {
                continue;
            }
            if(ItemStack.isSameItemSameComponents(stack, mail) && stack.getCount() + mail.getCount() <= stack.getMaxStackSize())
            {
                stack.grow(mail.getCount());
                this.setChanged();
                this.setUnchecked();
                return true;
            }
        }
        return false;
    }

    public void setUnchecked()
    {
        this.level.setBlock(this.worldPosition, this.getBlockState().setValue(MailboxBlock.ENABLED, true), Block.UPDATE_ALL);
    }

    public Optional<Mailbox> getMailbox()
    {
        if(this.level instanceof ServerLevel serverLevel)
        {
            Optional<DeliveryService> optional = DeliveryService.get(serverLevel.getServer());
            if(optional.isPresent())
            {
                if(this.mailboxRef != null)
                {
                    Mailbox mailbox = this.mailboxRef.get();
                    if(mailbox != null && !mailbox.removed())
                    {
                        return Optional.of(mailbox);
                    }
                    this.mailboxRef = null;
                }

                DeliveryService service = optional.get();
                Mailbox mailbox = service.getOrCreateMailBox(this);
                if(mailbox != null)
                {
                    this.mailboxRef = new WeakReference<>(mailbox);
                    return Optional.of(mailbox);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    protected Component getDefaultName()
    {
        return Utils.translation("container", "mailbox");
    }

    @Override
    protected AbstractContainerMenu createMenu(int windowId, Inventory playerInventory)
    {
        int rows = this.getContainerSize() / 9;
        return new ChestMenu(this.getChestMenu(rows), windowId, playerInventory, this, rows);
    }

    @Override
    public boolean isMatchingContainerMenu(AbstractContainerMenu menu)
    {
        return menu instanceof ChestMenu chestMenu && chestMenu.getContainer() == this;
    }

    public MenuType<ChestMenu> getChestMenu(int rows)
    {
        return switch(rows)
        {
            case 1 -> MenuType.GENERIC_9x1;
            case 2 -> MenuType.GENERIC_9x2;
            case 3 -> MenuType.GENERIC_9x3;
            case 4 -> MenuType.GENERIC_9x4;
            case 5 -> MenuType.GENERIC_9x5;
            case 6 -> MenuType.GENERIC_9x6;
            default -> throw new IllegalArgumentException("Rows can only be a minimum of one and a maximum of six");
        };
    }

    @Override
    public Component getDisplayName()
    {
        Optional<Mailbox> mailboxOptional = this.getMailbox();
        if(mailboxOptional.isPresent())
        {
            Optional<String> customNameOptional = mailboxOptional.get().customName();
            if(customNameOptional.isPresent())
            {
                String customName = customNameOptional.get();
                if(!customName.isBlank())
                {
                    return Component.literal(customName);
                }
            }
        }
        return super.getDisplayName();
    }

    @Override
    public void loadAdditional(ValueInput input)
    {
        super.loadAdditional(input);
        input.read("UUID", UUIDUtil.CODEC).ifPresentOrElse(uuid -> {
            this.uuid = uuid;
        }, () -> this.uuid = UUID.randomUUID());
    }

    @Override
    protected void saveAdditional(ValueOutput output)
    {
        super.saveAdditional(output);
        output.store("UUID", UUIDUtil.CODEC, this.uuid);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state)
    {
        super.preRemoveSideEffects(pos, state);
        if(this.level instanceof ServerLevel serverLevel)
        {
            Optional<DeliveryService> optional = DeliveryService.get(serverLevel.getServer());
            optional.ifPresent(service -> service.removeMailbox(this.uuid));
            this.mailboxRef = null;
        }
    }

    public void regenerateId()
    {
        this.uuid = UUID.randomUUID();
        this.setChanged();
    }
}
