package com.mrcrayfish.mightymail;

import com.mrcrayfish.framework.api.config.BoolProperty;
import com.mrcrayfish.framework.api.config.ConfigProperty;
import com.mrcrayfish.framework.api.config.ConfigType;
import com.mrcrayfish.framework.api.config.FrameworkConfig;
import com.mrcrayfish.framework.api.config.IntProperty;
import com.mrcrayfish.framework.api.config.ListProperty;

/**
 * Author: MrCrayfish
 */
public class Config
{
    @FrameworkConfig(id = Constants.MOD_ID, name = "server", type = ConfigType.SERVER_SYNC)
    public static final Server SERVER = new Server();

    public static class Server
    {
        @ConfigProperty(name = "mailboxInventoryRows", comment = """
                The maximum amount of items that can be queued for delivery for a mail box""")
        public final IntProperty mailboxInventoryRows = IntProperty.create(1, 1, 6);

        @ConfigProperty(name = "mailQueueSize", comment = """
            The maximum amount of items that can be queued for delivery for a mail box""")
        public final IntProperty mailQueueSize = IntProperty.create(18, 0, 256);

        @ConfigProperty(name = "banSendingItemsWithInventories", comment = """
            If enabled, this will ban items with an inventory (like a Shulker Box) being sent through
            a Post Box. This prevents players from creating massive NBT on a single item, which can
            cause issues for your server/world save.""")
        public final BoolProperty banSendingItemsWithInventories = BoolProperty.create(true);

        @ConfigProperty(name = "bannedItems", comment = """
            Prevents items contained in this list from being sent through a Post Box.
            An example of how the list is defined:
            bannedItems = [
                "minecraft:water_bucket",
                "minecraft:diamond",
                "mighty_mail:mailbox"
                ...
            ]
            ^ Note: This is just an example. Write your list below.""")
        public final ListProperty<String> bannedItems = ListProperty.create(ListProperty.STRING);
    }
}
