package com.mrcrayfish.mightymail.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
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
        super(x, y, 18, 18, CommonComponents.EMPTY, onPress, DEFAULT_NARRATION);
        this.iconU = iconU;
        this.iconV = iconV;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.texture = texture;
        this.sourceWidth = sourceWidth;
        this.sourceHeight = sourceHeight;
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
    {
        super.renderWidget(graphics, mouseX, mouseY, partialTicks);
        int contentLeft = (this.width - this.iconWidth) / 2;
        int contentTop = (this.height - this.iconHeight) / 2;
        int iconX = this.getX() + contentLeft;
        int iconY = this.getY() + contentTop;
        float brightness = this.active ? 1.0F : 0.5F;
        RenderSystem.enableBlend();
        graphics.setColor(brightness, brightness, brightness, this.alpha);
        graphics.blit(this.texture, iconX, iconY, this.iconU, this.iconV, this.iconWidth, this.iconHeight, this.sourceWidth, this.sourceHeight);
        RenderSystem.disableBlend();
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    protected ClientTooltipPositioner createTooltipPositioner()
    {
        return DefaultTooltipPositioner.INSTANCE;
    }
}
