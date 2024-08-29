package com.mrcrayfish.mightymail.network.message;

import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.mightymail.network.play.ServerPlayHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Author: MrCrayfish
 */
public record MessageSetMailboxName(BlockPos pos, String name)
{
    public static void encode(MessageSetMailboxName message, FriendlyByteBuf buffer)
    {
        buffer.writeBlockPos(message.pos);
        buffer.writeUtf(message.name);
    }

    public static MessageSetMailboxName decode(FriendlyByteBuf buffer)
    {
        BlockPos pos = buffer.readBlockPos();
        String name = buffer.readUtf();
        return new MessageSetMailboxName(pos, name);
    }

    public static void handle(MessageSetMailboxName message, MessageContext context)
    {
        context.execute(() -> ServerPlayHandler.handleMessageSetMailboxName(message, context.getPlayer().orElse(null)));
        context.setHandled(true);
    }
}
