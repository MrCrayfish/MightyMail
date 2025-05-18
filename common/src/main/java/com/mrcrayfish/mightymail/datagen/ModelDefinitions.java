package com.mrcrayfish.mightymail.datagen;

import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;

import java.util.Optional;

public class ModelDefinitions
{
    public static final ModelTemplate MAIL_BOX = block("mail_box", TextureSlot.PARTICLE, TextureSlot.TEXTURE);
    public static final ModelTemplate MAIL_BOX_UNCHECKED = block("mail_box_unchecked", "_unchecked", TextureSlot.PARTICLE, TextureSlot.TEXTURE);

    private static ModelTemplate block(String name, TextureSlot... textures)
    {
        return new ModelTemplate(Optional.of(Utils.resource("block/" + name)), Optional.empty(), textures);
    }

    private static ModelTemplate block(String name, String suffix, TextureSlot ... textures)
    {
        return new ModelTemplate(Optional.of(Utils.resource("block/" + name)), Optional.of(suffix), textures);
    }
}
