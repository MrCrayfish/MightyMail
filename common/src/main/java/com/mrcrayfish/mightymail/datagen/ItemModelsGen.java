package com.mrcrayfish.mightymail.datagen;

import com.mrcrayfish.framework.api.datagen.FrameworkGenerator;
import com.mrcrayfish.mightymail.core.ModItems;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Map;

@SuppressWarnings("UnstableApiUsage")
public class ItemModelsGen extends FrameworkGenerator
{
    public ItemModelsGen(Map<Block, BlockModelDefinitionGenerator> generators, Map<Item, ClientItem> items, Map<ResourceLocation, ModelInstance> models)
    {
        super(generators, items, models);
    }

    @Override
    public void generate()
    {
        this.flatItemModel(ModItems.PACKAGE.get());
    }
}
