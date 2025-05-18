package com.mrcrayfish.mightymail;

import com.mrcrayfish.framework.api.datagen.FrameworkModelProvider;
import com.mrcrayfish.mightymail.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.recipes.RecipeProvider;

public class DataGeneration implements DataGeneratorEntrypoint
{
    @Override
    @SuppressWarnings({"UnstableApiUsage", "unchecked"})
    public void onInitializeDataGenerator(FabricDataGenerator generator)
    {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(RecipeGen.Runner::new);
        pack.addProvider(LootTableGen::new);
        pack.addProvider(BlockTagGen::new);
        pack.addProvider((FabricDataGenerator.Pack.Factory<FrameworkModelProvider>) output ->
            new FrameworkModelProvider(output, BlockStatesGen::new, ItemModelsGen::new));

    }
}
