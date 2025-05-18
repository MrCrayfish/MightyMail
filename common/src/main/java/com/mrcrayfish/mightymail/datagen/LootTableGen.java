package com.mrcrayfish.mightymail.datagen;

import com.mrcrayfish.mightymail.core.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class LootTableGen extends LootTableProvider
{
    public LootTableGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(output, Set.of(), List.of(new SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK)), lookupProvider);
    }

    private static class Blocks extends BlockLootSubProvider
    {
        private Blocks(HolderLookup.Provider provider)
        {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        public void generate() {}

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer)
        {
            this.drop(consumer, ModBlocks.MAIL_BOX_OAK.get());
            this.drop(consumer, ModBlocks.MAIL_BOX_SPRUCE.get());
            this.drop(consumer, ModBlocks.MAIL_BOX_BIRCH.get());
            this.drop(consumer, ModBlocks.MAIL_BOX_JUNGLE.get());
            this.drop(consumer, ModBlocks.MAIL_BOX_ACACIA.get());
            this.drop(consumer, ModBlocks.MAIL_BOX_DARK_OAK.get());
            this.drop(consumer, ModBlocks.MAIL_BOX_MANGROVE.get());
            this.drop(consumer, ModBlocks.MAIL_BOX_CHERRY.get());
            this.drop(consumer, ModBlocks.MAIL_BOX_CRIMSON.get());
            this.drop(consumer, ModBlocks.MAIL_BOX_WARPED.get());
            this.drop(consumer, ModBlocks.POST_BOX.get());
        }

        private void drop(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer, Block block)
        {
            consumer.accept(block.getLootTable().orElseThrow(), this.createSingleItemTable(block));
        }
    }
}
