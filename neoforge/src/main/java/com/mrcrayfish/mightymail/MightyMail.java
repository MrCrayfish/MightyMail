package com.mrcrayfish.mightymail;

import com.mrcrayfish.mightymail.client.ClientHandler;
import com.mrcrayfish.mightymail.datagen.BlockTagGen;
import com.mrcrayfish.mightymail.datagen.LootTableGen;
import com.mrcrayfish.mightymail.datagen.RecipeGen;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(Constants.MOD_ID)
public class MightyMail
{
    public MightyMail(IEventBus bus)
    {
        bus.addListener(this::onCommonSetup);
        bus.addListener(this::onClientSetup);
        bus.addListener(this::onGatherData);
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(Bootstrap::init);
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(ClientHandler::setup);
    }

    private void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new RecipeGen(output, lookupProvider));
        generator.addProvider(event.includeServer(), new LootTableGen(output, lookupProvider));
        generator.addProvider(event.includeServer(), new BlockTagGen(output, lookupProvider));
    }
}