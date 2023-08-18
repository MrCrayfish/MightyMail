package com.mrcrayfish.mightymail.network.play;

import com.mrcrayfish.mightymail.client.gui.screen.PostBoxScreen;
import com.mrcrayfish.mightymail.network.message.MessageClearMessage;
import com.mrcrayfish.mightymail.network.message.MessageUpdateMailboxes;
import net.minecraft.client.Minecraft;

/**
 * Class containing all the handling of network messages on the client side
 * <p>
 * Author: MrCrayfish
 */
public class ClientPlayHandler
{
    public static void handleMessageUpdateMailboxes(MessageUpdateMailboxes message)
    {
        PostBoxScreen.updateMailboxes(message.getMailboxes());
    }

    public static void handleMessageClearMessage(MessageClearMessage message)
    {
        Minecraft mc = Minecraft.getInstance();
        if(mc.screen instanceof PostBoxScreen postBox)
        {
            postBox.clearMessage();
        }
    }
}
