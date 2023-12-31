package com.mrcrayfish.mightymail.blockentity;

import com.mrcrayfish.mightymail.Config;
import com.mrcrayfish.mightymail.block.MailboxBlock;
import com.mrcrayfish.mightymail.core.ModBlockEntities;
import com.mrcrayfish.mightymail.mail.DeliveryService;
import com.mrcrayfish.mightymail.mail.Mailbox;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

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
            if(ItemStack.isSameItemSameTags(stack, mail) && stack.getCount() + mail.getCount() <= stack.getMaxStackSize())
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

    public Mailbox getMailbox()
    {
        if(this.mailboxRef != null)
        {
            Mailbox mailbox = this.mailboxRef.get();
            if(mailbox != null && !mailbox.removed().booleanValue())
            {
                return mailbox;
            }
            this.mailboxRef = null;
        }

        if(this.level instanceof ServerLevel serverLevel)
        {
            Optional<DeliveryService> optional = DeliveryService.get(serverLevel.getServer());
            if(optional.isPresent())
            {
                DeliveryService service = optional.get();
                Mailbox mailbox = service.getOrCreateMailBox(this);
                this.mailboxRef = new WeakReference<>(mailbox);
                return mailbox;
            }
        }
        return null;
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
        Mailbox mailbox = this.getMailbox();
        if(mailbox != null)
        {
            String customName = mailbox.customName().getValue();
            if(customName != null && !customName.isBlank())
            {
                return Component.literal(customName);
            }
        }
        return super.getDisplayName();
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        if(tag.contains("UUID", Tag.TAG_INT_ARRAY))
        {
            this.uuid = tag.getUUID("UUID");
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tag.putUUID("UUID", this.uuid);
    }
}
