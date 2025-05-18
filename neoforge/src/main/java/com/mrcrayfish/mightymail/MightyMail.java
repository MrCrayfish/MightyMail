package com.mrcrayfish.mightymail;

import com.mrcrayfish.framework.api.datagen.FrameworkModelProvider;
import com.mrcrayfish.mightymail.client.ClientHandler;
import com.mrcrayfish.mightymail.datagen.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

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

    @SuppressWarnings({"UnstableApiUsage"})
    private void onGatherData(GatherDataEvent.Client event)
    {
        event.createProvider(RecipeGen.Runner::new);
        event.createProvider(LootTableGen::new);
        event.createProvider(BlockTagGen::new);

        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        event.addProvider(new FrameworkModelProvider(output, BlockStatesGen::new, ItemModelsGen::new));
    }
}