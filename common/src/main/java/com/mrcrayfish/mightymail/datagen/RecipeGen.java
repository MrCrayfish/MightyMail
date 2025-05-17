package com.mrcrayfish.mightymail.datagen;

import com.mrcrayfish.mightymail.core.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class RecipeGen extends RecipeProvider
{
    public RecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        this.mailbox(output, ModBlocks.MAIL_BOX_OAK.get(), Blocks.OAK_PLANKS, Blocks.OAK_FENCE);
        this.mailbox(output, ModBlocks.MAIL_BOX_SPRUCE.get(), Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_FENCE);
        this.mailbox(output, ModBlocks.MAIL_BOX_BIRCH.get(), Blocks.BIRCH_PLANKS, Blocks.BIRCH_FENCE);
        this.mailbox(output, ModBlocks.MAIL_BOX_JUNGLE.get(), Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_FENCE);
        this.mailbox(output, ModBlocks.MAIL_BOX_ACACIA.get(), Blocks.ACACIA_PLANKS, Blocks.ACACIA_FENCE);
        this.mailbox(output, ModBlocks.MAIL_BOX_DARK_OAK.get(), Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_FENCE);
        this.mailbox(output, ModBlocks.MAIL_BOX_MANGROVE.get(), Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_FENCE);
        this.mailbox(output, ModBlocks.MAIL_BOX_CHERRY.get(), Blocks.CHERRY_PLANKS, Blocks.CHERRY_FENCE);
        this.mailbox(output, ModBlocks.MAIL_BOX_CRIMSON.get(), Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_FENCE);
        this.mailbox(output, ModBlocks.MAIL_BOX_WARPED.get(), Blocks.WARPED_PLANKS, Blocks.WARPED_FENCE);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.POST_BOX.get())
                .pattern("III")
                .pattern("ICI")
                .pattern("I I")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.CHEST)
                .unlockedBy("has_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_chest", has(Items.CHEST))
                .save(output);
    }

    private void mailbox(RecipeOutput output, ItemLike item, Block plank, Block fence)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, item)
                .pattern("PCP")
                .pattern("PPP")
                .pattern(" F ")
                .define('P', plank)
                .define('C', Blocks.CHEST)
                .define('F', fence)
                .unlockedBy("has_plank", has(plank))
                .unlockedBy("has_chest", has(Items.CHEST))
                .unlockedBy("has_fence", has(fence))
                .save(output);
    }
}
