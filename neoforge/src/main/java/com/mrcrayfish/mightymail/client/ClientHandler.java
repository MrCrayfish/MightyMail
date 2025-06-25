package com.mrcrayfish.mightymail.client;

import com.mrcrayfish.mightymail.Constants;
import com.mrcrayfish.mightymail.client.gui.screen.PostBoxScreen;
import com.mrcrayfish.mightymail.core.ModBlocks;
import com.mrcrayfish.mightymail.core.ModMenuTypes;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

/**
 * Author: MrCrayfish
 */
@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class ClientHandler
{
    @SuppressWarnings("deprecation")
    public static void setup()
    {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_OAK.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_SPRUCE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_BIRCH.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_JUNGLE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_ACACIA.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_DARK_OAK.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_MANGROVE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_CHERRY.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_CRIMSON.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_WARPED.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAIL_BOX_PALE_OAK.get(), ChunkSectionLayer.CUTOUT);
    }

    @SubscribeEvent
    private static void onRegisterMenuScreens(RegisterMenuScreensEvent event)
    {
        event.register(ModMenuTypes.POST_BOX.get(), PostBoxScreen::new);
    }
}
