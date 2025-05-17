package com.mrcrayfish.mightymail.network.message;

import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.mightymail.network.play.ClientPlayHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

/**
 * Author: MrCrayfish
 */
public record MessageClearMessage()
{
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageClearMessage> STREAM_CODEC = StreamCodec.unit(new MessageClearMessage());

    public static void handle(MessageClearMessage message, MessageContext context)
    {
        context.execute(() -> ClientPlayHandler.handleMessageClearMessage(message));
        context.setHandled(true);
    }
}
