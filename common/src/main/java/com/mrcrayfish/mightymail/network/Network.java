package com.mrcrayfish.mightymail.network;

import com.mrcrayfish.framework.api.FrameworkAPI;
import com.mrcrayfish.framework.api.network.FrameworkNetwork;
import com.mrcrayfish.framework.api.network.MessageDirection;
import com.mrcrayfish.mightymail.Constants;
import com.mrcrayfish.mightymail.network.message.MessageClearMessage;
import com.mrcrayfish.mightymail.network.message.MessageSendPackage;
import com.mrcrayfish.mightymail.network.message.MessageSetMailboxName;
import com.mrcrayfish.mightymail.network.message.MessageUpdateMailboxes;
import net.minecraft.resources.ResourceLocation;

/**
 * Author: MrCrayfish
 */
public class Network
{
    public static final FrameworkNetwork PLAY = FrameworkAPI
            .createNetworkBuilder(new ResourceLocation(Constants.MOD_ID, "play"), 1)
            .registerPlayMessage(MessageSetMailboxName.class, MessageDirection.PLAY_SERVER_BOUND)
            .registerPlayMessage(MessageUpdateMailboxes.class, MessageDirection.PLAY_CLIENT_BOUND)
            .registerPlayMessage(MessageSendPackage.class, MessageDirection.PLAY_SERVER_BOUND)
            .registerPlayMessage(MessageClearMessage.class, MessageDirection.PLAY_CLIENT_BOUND)
            .build();

    public static void init() {}

    public static FrameworkNetwork getPlay()
    {
        return PLAY;
    }
}
