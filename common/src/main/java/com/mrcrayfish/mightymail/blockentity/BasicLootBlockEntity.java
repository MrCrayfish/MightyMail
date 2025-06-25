package com.mrcrayfish.mightymail.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

/**
 * Author: MrCrayfish
 */
public abstract class BasicLootBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer
{
    protected final int[] slots;
    protected final int containerSize;
    protected NonNullList<ItemStack> items;

    public BasicLootBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int containerSize)
    {
        super(type, pos, state);
        this.items = NonNullList.withSize(containerSize, ItemStack.EMPTY);
        this.slots = IntStream.range(0, containerSize).toArray();
        this.containerSize = containerSize;
    }

    public abstract boolean isMatchingContainerMenu(AbstractContainerMenu menu);

    @Override
    public int getContainerSize()
    {
        return this.containerSize;
    }

    @Override
    protected NonNullList<ItemStack> getItems()
    {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items)
    {
        this.items = items;
    }

    @Override
    protected void saveAdditional(ValueOutput output)
    {
        super.saveAdditional(output);
        if(!this.trySaveLootTable(output))
        {
            ContainerHelper.saveAllItems(output, this.items);
        }
    }

    @Override
    public void loadAdditional(ValueInput input)
    {
        super.loadAdditional(input);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if(!this.tryLoadLootTable(input))
        {
            ContainerHelper.loadAllItems(input, this.items);
        }
    }

    @Override
    public int[] getSlotsForFace(Direction direction)
    {
        return this.slots;
    }

    @Override
    public boolean canPlaceItem(int slotIndex, ItemStack stack)
    {
        return this.isSlotInsertable(slotIndex); // Additional check for container max stack size
    }

    @Override
    public boolean canPlaceItemThroughFace(int slotIndex, ItemStack stack, @Nullable Direction direction)
    {
        return this.canPlaceItem(slotIndex, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slotIndex, ItemStack stack, Direction direction)
    {
        return this.canTakeItem(this, slotIndex, stack);
    }

    /**
     * A utility method to check if the slot at the given index is empty or is less than it's max
     * stack size with additional respect to the max stack size of the container; something vanilla
     * unfortunately doesn't do.
     *
     * @param slotIndex the index of the slot to check
     * @return True if the slot is available
     */
    protected boolean isSlotInsertable(int slotIndex)
    {
        ItemStack target = this.getItem(slotIndex);
        return target.isEmpty() || target.getCount() < target.getMaxStackSize() && target.getCount() < this.getMaxStackSize();
    }
}
