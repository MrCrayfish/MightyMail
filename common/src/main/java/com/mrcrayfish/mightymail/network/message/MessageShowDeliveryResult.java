package com.mrcrayfish.mightymail.network.message;

import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.mightymail.mail.DeliveryResult;
import com.mrcrayfish.mightymail.network.play.ClientPlayHandler;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Optional;

/**
 * Author: MrCrayfish
 */
public record MessageShowDeliveryResult(DeliveryResult result)
{
    public static void encode(MessageShowDeliveryResult message, FriendlyByteBuf buf)
    {
        buf.writeBoolean(message.result.success());
        buf.writeOptional(message.result.message(), (buf1, s) -> buf1.writeUtf(s, 256));
    }

    public static MessageShowDeliveryResult decode(FriendlyByteBuf buf)
    {
        boolean success = buf.readBoolean();
        Optional<String> message = buf.readOptional(buf1 -> buf1.readUtf(256));
        return new MessageShowDeliveryResult(new DeliveryResult(success, message));
    }

    public static void handle(MessageShowDeliveryResult message, MessageContext context)
    {
        context.execute(() -> ClientPlayHandler.handleMessageShowDeliveryResult(message));
        context.setHandled(true);
    }
}
