package com.mrcrayfish.mightymail.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/**
 * Author: MrCrayfish
 */
public class PostBoxSlot extends Slot
{
    public PostBoxSlot(Container container, int slot, int x, int y)
    {
        super(container, slot, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {
        return stack.getItem().canFitInsideContainerItems();
    }
}
