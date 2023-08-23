package com.mrcrayfish.mightymail.inventory;

import com.mrcrayfish.mightymail.blockentity.PostBoxBlockEntity;
import com.mrcrayfish.mightymail.core.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ShulkerBoxSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/**
 * Author: MrCrayfish
 */
public class PostBoxMenu extends AbstractContainerMenu
{
    protected final Container container;

    public PostBoxMenu(int windowId, Inventory playerInventory)
    {
        this(windowId, playerInventory, new SimpleContainer(PostBoxBlockEntity.CONTAINER_SIZE));
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
            else if(!this.moveItemStackTo(slotStack, 0, this.container.getContainerSize(), false))
            {
                return ItemStack.EMPTY;
            }
            if(slotStack.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
            }
        }
        return stack;
    }
}
