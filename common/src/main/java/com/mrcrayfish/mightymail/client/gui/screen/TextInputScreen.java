package com.mrcrayfish.mightymail.client.gui.screen;

import com.google.common.base.MoreObjects;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

/**
 * Author: MrCrayfish
 */
public class TextInputScreen extends Screen
{
    public static final ResourceLocation SET_NAME_TEXTURE = Utils.resource("textures/gui/set_name.png");
    public static final int WINDOW_WIDTH = 160;
    public static final int WINDOW_HEIGHT = 72;

    protected final Component hint;
    protected final Function<String, Boolean> callback;
    protected Function<String, Boolean> validator = s -> true;
    protected EditBox editBox;
    protected Button closeButton;
    protected Button acceptButton;
    protected Component acceptLabel;
    protected String input = "";

    public TextInputScreen(Component title, Component hint, Function<String, Boolean> callback)
    {
        super(title);
        this.hint = hint;
        this.callback = callback;
    }

    /**
     * Sets the validator of the text input. Do note that you will also need to validate this
     * on the logical server side too.
     *
     * @param validator a function accepting a string that can be used to test if valid
     */
    public void setValidator(Function<String, Boolean> validator)
    {
        this.validator = validator;
    }

    /**
     * Sets the label of the accept button
     *
     * @param acceptLabel a component
     */
    public void setAcceptLabel(Component acceptLabel)
    {
        this.acceptLabel = acceptLabel;
    }

    @Override
    protected void init()
    {
        int startX = (this.width - WINDOW_WIDTH) / 2;
        int startY = (this.height - WINDOW_HEIGHT) / 2;
        this.addRenderableWidget(this.editBox = new EditBox(this.minecraft.font, startX + 6, startY + 20, WINDOW_WIDTH - 12, 20, this.hint));
        this.editBox.setResponder(s -> {
            boolean valid = this.validator.apply(s);
            this.editBox.setTextColor(valid ? 0xFFFFFF : 0xFF0000);
            this.acceptButton.active = valid;
            this.input = s;
        });
        if(!this.input.isBlank())
        {
            this.editBox.setValue(this.input);
        }

        this.addRenderableWidget(this.closeButton = new Button(startX + 6, startY + 45, (WINDOW_WIDTH - 12) / 2 - 2, 20, Component.literal("Close"), btn -> {
            this.minecraft.setScreen(null);
        }));

        this.addRenderableWidget(this.acceptButton = new Button(startX + (WINDOW_WIDTH - 12) / 2 + 2 + 6, startY + 45, (WINDOW_WIDTH - 12) / 2 - 2, 20, Component.literal("Accept"), btn -> {
            if(this.callback.apply(this.input)) {
                this.minecraft.setScreen(null);
            }
        }));
    }

    @Override
    public void tick()
    {
        this.editBox.tick();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick)
    {
        // x, y, width, height, corner_size, sub_image_width, sub_image_height, sub_image_u, sub_image_v
        this.renderBackground(poseStack);
        int startX = (this.width - WINDOW_WIDTH) / 2;
        int startY = (this.height - WINDOW_HEIGHT) / 2;
        RenderSystem.setShaderTexture(0, SET_NAME_TEXTURE);
        blit(poseStack, startX, startY, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.font.draw(poseStack, this.title, startX + 6, startY + 7, 0x404040);
        super.render(poseStack, mouseX, mouseY, partialTick);
    }
}
