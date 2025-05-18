package com.mrcrayfish.mightymail.client;

import com.mrcrayfish.mightymail.Constants;
import com.mrcrayfish.mightymail.client.gui.screen.PostBoxScreen;
import com.mrcrayfish.mightymail.core.ModBlocks;
import com.mrcrayfish.mightymail.core.ModMenuTypes;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

/**
 * Author: MrCrayfish
 */
@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientHandler
{
    @SuppressWarnings("deprecation")
    public static void setup()
    {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_OAK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_SPRUCE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_BIRCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_JUNGLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_ACACIA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_DARK_OAK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_MANGROVE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_CHERRY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_CRIMSON.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_WARPED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_PALE_OAK.get(), RenderType.cutout());
    }

    @SubscribeEvent
    private static void onRegisterMenuScreens(RegisterMenuScreensEvent event)
    {
        event.register(ModMenuTypes.POST_BOX.get(), PostBoxScreen::new);
    }
}
