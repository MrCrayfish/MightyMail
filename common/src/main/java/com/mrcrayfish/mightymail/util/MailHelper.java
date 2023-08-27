package com.mrcrayfish.mightymail.util;

import com.mrcrayfish.mightymail.Config;
import net.minecraft.world.item.ItemStack;

/**
 * Author: MrCrayfish
 */
public class MailHelper
{
    /**
     * Determines if the given ItemStack is an item that can't be sent through mail. By default,
     * items that have inventory are banned (such as Shulker Boxes) unless explicitly disabled in
     * mod's configuration. An item is also blocked if the item id is contained in the banned items
     * list, which again is defined in the mod's configuration. Banned items are mainly to prevent
     * creating large NBT on a single item, which can affect servers/world saves.
     * <p>
     * This method can only be called while in a game/server since it depends on a configuration
     * sent from the server.
     *
     * @param stack the ItemStack to check if it is banned
     * @return True if the ItemStack is banned
     */
    public static boolean isBannedItem(ItemStack stack)
    {
        // Check if the item can fit inside container items
        if(Config.SERVER.banSendingItemsWithInventories.get() && !stack.getItem().canFitInsideContainerItems())
        {
            return true;
        }

        // Check if the item is not on the banned item list
        String name = stack.getItem().getDescriptionId();
        return Config.SERVER.bannedItems.get().contains(name);
    }
}
