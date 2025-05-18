package com.mrcrayfish.mightymail.network.message;

import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.mightymail.network.play.ServerPlayHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

/**
 * Author: MrCrayfish
 */
public record MessageSetMailboxName(BlockPos pos, String name)
{
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageSetMailboxName> STREAM_CODEC = StreamCodec.of((buf, message) -> {
        buf.writeBlockPos(message.pos);
        buf.writeUtf(message.name);
    }, buf -> {
        BlockPos pos = buf.readBlockPos();
        String name = buf.readUtf();
        return new MessageSetMailboxName(pos, name);
    });

    public static void handle(MessageSetMailboxName message, MessageContext context)
    {
        context.execute(() -> ServerPlayHandler.handleMessageSetMailboxName(message, context.getPlayer().orElse(null)));
        context.setHandled(true);
    }
}
