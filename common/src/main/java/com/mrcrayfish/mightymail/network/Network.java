package com.mrcrayfish.mightymail.network;

import com.mrcrayfish.framework.api.FrameworkAPI;
import com.mrcrayfish.framework.api.network.FrameworkNetwork;
import com.mrcrayfish.mightymail.Constants;
import com.mrcrayfish.mightymail.network.message.MessageClearMessage;
import com.mrcrayfish.mightymail.network.message.MessageSendPackage;
import com.mrcrayfish.mightymail.network.message.MessageSetMailboxName;
import com.mrcrayfish.mightymail.network.message.MessageShowDeliveryResult;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.resources.ResourceLocation;

/**
 * Author: MrCrayfish
 */
public class Network
{
    public static final FrameworkNetwork PLAY = FrameworkAPI
            .createNetworkBuilder(new ResourceLocation(Constants.MOD_ID, "play"), 1)
            .registerPlayMessage("set_mailbox_name", MessageSetMailboxName.class, MessageSetMailboxName::encode, MessageSetMailboxName::decode, MessageSetMailboxName::handle, PacketFlow.SERVERBOUND)
            .registerPlayMessage("send_package", MessageSendPackage.class, MessageSendPackage::encode, MessageSendPackage::decode, MessageSendPackage::handle, PacketFlow.SERVERBOUND)
            .registerPlayMessage("clear_message", MessageClearMessage.class, MessageClearMessage::encode, MessageClearMessage::decode, MessageClearMessage::handle, PacketFlow.CLIENTBOUND)
            .registerPlayMessage("show_delivery_result", MessageShowDeliveryResult.class, MessageShowDeliveryResult::encode, MessageShowDeliveryResult::decode, MessageShowDeliveryResult::handle, PacketFlow.CLIENTBOUND)
            .build();

    public static void init() {}

    public static FrameworkNetwork getPlay()
    {
        return PLAY;
    }
}
