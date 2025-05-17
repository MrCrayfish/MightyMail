package com.mrcrayfish.mightymail;

import com.mrcrayfish.mightymail.datagen.BlockTagGen;
import com.mrcrayfish.mightymail.datagen.LootTableGen;
import com.mrcrayfish.mightymail.datagen.RecipeGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGeneration implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator)
    {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(RecipeGen::new);
        pack.addProvider(LootTableGen::new);
        pack.addProvider(BlockTagGen::new);
    }
}
