package com.mrcrayfish.mightymail.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.resources.ResourceLocation;

/**
 * Author: MrCrayfish
 */
public class IconButton extends Button
{
    private final int iconU;
    private final int iconV;
    private final int iconWidth;
    private final int iconHeight;
    private final ResourceLocation texture;
    private final int sourceWidth;
    private final int sourceHeight;

    public IconButton(int x, int y, int iconU, int iconV, int iconWidth, int iconHeight, ResourceLocation texture, int sourceWidth, int sourceHeight, OnPress onPress)
    {
        super(x, y, 18, 18, CommonComponents.EMPTY, onPress);
        this.iconU = iconU;
        this.iconV = iconV;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.texture = texture;
        this.sourceWidth = sourceWidth;
        this.sourceHeight = sourceHeight;
    }

    @Override
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
    {
        super.renderButton(poseStack, mouseX, mouseY, partialTicks);
        int contentLeft = (this.width - this.iconWidth) / 2;
        int contentTop = (this.height - this.iconHeight) / 2;
        int iconX = this.x + contentLeft;
        int iconY = this.y + contentTop;
        float brightness = this.active ? 1.0F : 0.5F;
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, this.texture);
        RenderSystem.setShaderColor(brightness, brightness, brightness, this.alpha);
        GuiComponent.blit(poseStack, iconX, iconY, this.iconU, this.iconV, this.iconWidth, this.iconHeight, this.sourceWidth, this.sourceHeight);
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
