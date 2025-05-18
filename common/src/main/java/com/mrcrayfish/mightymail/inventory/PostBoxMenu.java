package com.mrcrayfish.mightymail.inventory;

import com.mojang.authlib.GameProfile;
import com.mrcrayfish.framework.api.menu.IMenuData;
import com.mrcrayfish.mightymail.blockentity.PostBoxBlockEntity;
import com.mrcrayfish.mightymail.client.ClientMailbox;
import com.mrcrayfish.mightymail.core.ModMenuTypes;
import com.mrcrayfish.mightymail.mail.IMailbox;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: MrCrayfish
 */
public class PostBoxMenu extends AbstractContainerMenu
{
    protected final Container container;
    protected final List<IMailbox> mailboxes = new ArrayList<>();

    public PostBoxMenu(int windowId, Inventory playerInventory, CustomData data)
    {
        this(windowId, playerInventory, new SimpleContainer(PostBoxBlockEntity.CONTAINER_SIZE));
        this.mailboxes.addAll(data.mailboxes());
    }

    public PostBoxMenu(int windowId, Inventory playerInventory, Container container)
    {
        super(ModMenuTypes.POST_BOX.get(), windowId);
        checkContainerSize(container, 6);
        this.container = container;
        container.startOpen(playerInventory.player);

        for(int j = 0; j < 3; j++)
        {
            for(int i = 0; i < 2; i++)
            {
                this.addSlot(new PostBoxSlot(container, j * 2 + i, 235 + i * 18, 14 + j * 18));
            }
        }

        for(int j = 0; j < 3; j++)
        {
            for(int i = 0; i < 9; i++)
            {
                int slotIndex = i + j * 9 + 9;
                int slotX = 114 + i * 18;
                int slotY = 90 + j * 18;
                this.addSlot(new Slot(playerInventory, slotIndex, slotX, slotY));
            }
        }
        for(int i = 0; i < 9; i++)
        {
            int slotX = 114 + i * 18;
            int slotY = 90 + 58;
            this.addSlot(new Slot(playerInventory, i, slotX, slotY));
        }
    }

    public Container getContainer()
    {
        return this.container;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return this.container.stillValid(player);
    }

    @Override
    public void removed(Player player)
    {
        super.removed(player);
        this.container.stopOpen(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if(slot.hasItem())
        {
            ItemStack slotStack = slot.getItem();
            stack = slotStack.copy();
            if(slotIndex < this.container.getContainerSize())
            {
                if(!this.moveItemStackTo(slotStack, this.container.getContainerSize(), this.slots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(slotStack.getItem().canFitInsideContainerItems())
            {
                if(!this.moveItemStackTo(slotStack, 0, this.container.getContainerSize(), false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(slotIndex < this.slots.size() - 9)
            {
                if(!this.moveItemStackTo(slotStack, this.slots.size() - 9, this.slots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(!this.moveItemStackTo(slotStack, this.container.getContainerSize(), this.slots.size() - 9, false))
            {
                return ItemStack.EMPTY;
            }
            if(slotStack.isEmpty())
            {
                slot.setByPlayer(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
            }
        }
        return stack;
    }

    public List<IMailbox> getMailboxes()
    {
        return Collections.unmodifiableList(this.mailboxes);
    }

    public record CustomData(List<IMailbox> mailboxes) implements IMenuData<CustomData>
    {
        public static final StreamCodec<RegistryFriendlyByteBuf, CustomData> STREAM_CODEC = StreamCodec.of((buf, data) -> {
            buf.writeCollection(data.mailboxes(), (buf1, mailbox) -> {
                buf1.writeUUID(mailbox.getId());
                buf1.writeOptional(mailbox.getOwner(), (buf2, profile) -> {
                    buf2.writeUUID(profile.getId());
                    buf2.writeOptional(Optional.ofNullable(profile.getName()), FriendlyByteBuf::writeUtf);
                });
                buf1.writeOptional(mailbox.getCustomName(), FriendlyByteBuf::writeUtf);
            });
        }, buf -> {
            List<IMailbox> list = buf.readList(buf1 -> {
                UUID mailboxId = buf1.readUUID();
                Optional<GameProfile> profile = buf1.readOptional(buf2 -> {
                    UUID playerId = buf2.readUUID();
                    Optional<String> name = buf2.readOptional(FriendlyByteBuf::readUtf);
                    return new GameProfile(playerId, name.orElse("Unknown"));
                });
                Optional<String> mailboxName = buf1.readOptional(FriendlyByteBuf::readUtf);
                return new ClientMailbox(mailboxId, profile, mailboxName);
            });
            return new CustomData(list);
        });

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CustomData> codec()
        {
            return STREAM_CODEC;
        }
    }
}
