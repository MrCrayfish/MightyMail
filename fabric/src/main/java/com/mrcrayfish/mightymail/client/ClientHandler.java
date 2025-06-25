package com.mrcrayfish.mightymail.client;

import com.mrcrayfish.mightymail.client.gui.screen.PostBoxScreen;
import com.mrcrayfish.mightymail.core.ModBlocks;
import com.mrcrayfish.mightymail.core.ModMenuTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

/**
 * Author: MrCrayfish
 */
public class ClientHandler implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        MenuScreens.register(ModMenuTypes.POST_BOX.get(), PostBoxScreen::new);
        BlockRenderLayerMap.putBlock(ModBlocks.MAIL_BOX_OAK.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MAIL_BOX_SPRUCE.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MAIL_BOX_BIRCH.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MAIL_BOX_JUNGLE.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MAIL_BOX_ACACIA.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MAIL_BOX_DARK_OAK.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MAIL_BOX_MANGROVE.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MAIL_BOX_CHERRY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MAIL_BOX_CRIMSON.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MAIL_BOX_WARPED.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MAIL_BOX_PALE_OAK.get(), ChunkSectionLayer.CUTOUT);
    }
}
