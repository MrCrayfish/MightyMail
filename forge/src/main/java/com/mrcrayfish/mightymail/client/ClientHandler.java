package com.mrcrayfish.mightymail.client;

import com.mrcrayfish.mightymail.client.gui.screen.PostBoxScreen;
import com.mrcrayfish.mightymail.core.ModBlocks;
import com.mrcrayfish.mightymail.core.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

/**
 * Author: MrCrayfish
 */
public class ClientHandler
{
    @SuppressWarnings("deprecation")
    public static void setup()
    {
        MenuScreens.register(ModMenuTypes.POST_BOX.get(), PostBoxScreen::new);
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
    }
}
