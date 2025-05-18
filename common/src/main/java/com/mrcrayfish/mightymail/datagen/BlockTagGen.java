package com.mrcrayfish.mightymail.datagen;

import com.mrcrayfish.mightymail.core.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class BlockTagGen extends IntrinsicHolderTagsProvider<Block>
{
    public BlockTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(output, Registries.BLOCK, lookupProvider, block -> block.builtInRegistryHolder().key());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.MAIL_BOX_OAK.get())
                .add(ModBlocks.MAIL_BOX_SPRUCE.get())
                .add(ModBlocks.MAIL_BOX_BIRCH.get())
                .add(ModBlocks.MAIL_BOX_JUNGLE.get())
                .add(ModBlocks.MAIL_BOX_ACACIA.get())
                .add(ModBlocks.MAIL_BOX_DARK_OAK.get())
                .add(ModBlocks.MAIL_BOX_MANGROVE.get())
                .add(ModBlocks.MAIL_BOX_CHERRY.get())
                .add(ModBlocks.MAIL_BOX_CRIMSON.get())
                .add(ModBlocks.MAIL_BOX_WARPED.get())
                .add(ModBlocks.MAIL_BOX_PALE_OAK.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.POST_BOX.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.POST_BOX.get());
    }


}
