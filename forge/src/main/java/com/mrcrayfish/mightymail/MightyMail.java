package com.mrcrayfish.mightymail;

import com.mrcrayfish.framework.Registration;
import com.mrcrayfish.mightymail.client.ClientHandler;
import com.mrcrayfish.mightymail.core.ModBlocks;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class MightyMail
{
    public MightyMail()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::onCommonSetup);
        bus.addListener(this::onClientSetup);
        bus.addListener(this::onBuildCreativeModeTab);
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(Bootstrap::init);
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(ClientHandler::setup);
    }

    private void onBuildCreativeModeTab(CreativeModeTabEvent.Register event)
    {
        event.registerCreativeModeTab(Utils.resource("creative_tab"), builder -> {
            builder.icon(() -> new ItemStack(ModBlocks.MAIL_BOX_OAK.get()));
            builder.title(Component.translatable("itemGroup." + Constants.MOD_ID));
            builder.displayItems((params, output) -> {
                Registration.get(Registries.BLOCK).stream().filter(entry -> entry.getId().getNamespace().equals(Constants.MOD_ID)).forEach(entry -> {
                    output.accept((Block) entry.get());
                });
            });
        });
    }
}