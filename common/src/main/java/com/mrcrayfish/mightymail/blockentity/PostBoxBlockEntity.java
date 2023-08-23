package com.mrcrayfish.mightymail.blockentity;

import com.mrcrayfish.mightymail.core.ModBlockEntities;
import com.mrcrayfish.mightymail.inventory.PostBoxMenu;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Author: MrCrayfish
 */
public class PostBoxBlockEntity extends BasicLootBlockEntity
{
    public static final int CONTAINER_SIZE = 6;

    public PostBoxBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.POST_BOX.get(), pos, state, CONTAINER_SIZE);
    }

    @Override
    public boolean isMatchingContainerMenu(AbstractContainerMenu menu)
    {
        return menu instanceof PostBoxMenu postBox && postBox.getContainer() == this;
    }

    @Override
    protected Component getDefaultName()
    {
        return Utils.translation("container", "post_box");
    }

    @Override
    protected AbstractContainerMenu createMenu(int windowId, Inventory playerInventory)
    {
        return new PostBoxMenu(windowId, playerInventory, this);
    }
}
