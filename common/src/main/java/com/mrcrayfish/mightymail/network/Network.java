package com.mrcrayfish.mightymail.network;

import com.mrcrayfish.framework.api.FrameworkAPI;
import com.mrcrayfish.framework.api.network.FrameworkNetwork;
import com.mrcrayfish.mightymail.network.message.MessageClearMessage;
import com.mrcrayfish.mightymail.network.message.MessageSendPackage;
import com.mrcrayfish.mightymail.network.message.MessageSetMailboxName;
import com.mrcrayfish.mightymail.network.message.MessageShowDeliveryResult;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.network.protocol.PacketFlow;

/**
 * Author: MrCrayfish
 */
public class Network
{
    public static final FrameworkNetwork PLAY = FrameworkAPI
            .createNetworkBuilder(Utils.resource("play"), 1)
            .registerPlayMessage("set_mailbox_name", MessageSetMailboxName.class, MessageSetMailboxName.STREAM_CODEC, MessageSetMailboxName::handle, PacketFlow.SERVERBOUND)
            .registerPlayMessage("send_package", MessageSendPackage.class, MessageSendPackage.STREAM_CODEC, MessageSendPackage::handle, PacketFlow.SERVERBOUND)
            .registerPlayMessage("clear_message", MessageClearMessage.class, MessageClearMessage.STREAM_CODEC, MessageClearMessage::handle, PacketFlow.CLIENTBOUND)
            .registerPlayMessage("show_delivery_result", MessageShowDeliveryResult.class, MessageShowDeliveryResult.STREAM_CODEC, MessageShowDeliveryResult::handle, PacketFlow.CLIENTBOUND)
            .build();

    public static void init() {}

    public static FrameworkNetwork getPlay()
    {
        return PLAY;
    }
}
