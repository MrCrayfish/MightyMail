package com.mrcrayfish.mightymail.client;

import com.mrcrayfish.mightymail.client.gui.screen.TextInputScreen;
import com.mrcrayfish.mightymail.mail.Mailbox;
import com.mrcrayfish.mightymail.network.Network;
import com.mrcrayfish.mightymail.network.message.MessageSetMailboxName;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

/**
 * Author: MrCrayfish
 */
public class ScreenHooks
{
    public static void openMailboxNameScreen(BlockPos pos)
    {
        Minecraft mc = Minecraft.getInstance();
        TextInputScreen screen = new TextInputScreen(Utils.translation("gui", "set_mailbox_name"), Component.empty(), name -> {
            Network.getPlay().sendToServer(new MessageSetMailboxName(pos, name));
            return true;
        });
        screen.setValidator(s -> !s.isBlank() && s.length() <= Mailbox.MAX_NAME_LENGTH);
        mc.setScreen(screen);
    }
}
