package com.mrcrayfish.mightymail.network.play;

import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.mightymail.inventory.PostBoxMenu;
import com.mrcrayfish.mightymail.item.PackageItem;
import com.mrcrayfish.mightymail.mail.DeliveryService;
import com.mrcrayfish.mightymail.network.Network;
import com.mrcrayfish.mightymail.network.message.MessageClearMessage;
import com.mrcrayfish.mightymail.network.message.MessageSendPackage;
import com.mrcrayfish.mightymail.network.message.MessageSetMailboxName;
import com.mrcrayfish.mightymail.util.MailHelper;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Class containing all the handling of network messages on the logical server side
 * <p>
 * Author: MrCrayfish
 */
public class ServerPlayHandler
{
    public static void handleMessageSetMailboxName(MessageSetMailboxName message, @Nullable ServerPlayer player)
    {
        if(player == null)
            return;

        DeliveryService.get(player.server).ifPresent(service -> {
            if(!service.renameMailbox(player, player.level(), message.getPos(), message.getName())) {
                player.sendSystemMessage(Utils.translation("gui", "rename_mailbox_failed"));
            }
        });
    }

    public static void handleMessageSendPackage(MessageSendPackage message, @Nullable ServerPlayer player, MessageContext context)
    {
        if(player != null && player.containerMenu instanceof PostBoxMenu postBox)
        {
            Container container = postBox.getContainer();
            if(container.isEmpty())
                return;

            // Check if all items in the container can be sent
            for(int i = 0; i < container.getContainerSize(); i++)
            {
                ItemStack stack = container.getItem(i);
                if(!stack.isEmpty() && MailHelper.isBannedItem(stack))
                {
                    return;
                }
            }

            DeliveryService.get(player.server).ifPresent(service -> {
                ItemStack stack = PackageItem.create(container, message.getMessage(), player.getGameProfile().getName());
                if(service.sendMail(message.getMailboxId(), stack)) {
                    container.clearContent();
                    Network.getPlay().sendToPlayer(() -> player, new MessageClearMessage());
                }
            });
        }
    }
}
