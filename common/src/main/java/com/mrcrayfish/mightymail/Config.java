package com.mrcrayfish.mightymail;

import com.mrcrayfish.framework.api.config.BoolProperty;
import com.mrcrayfish.framework.api.config.ConfigProperty;
import com.mrcrayfish.framework.api.config.ConfigType;
import com.mrcrayfish.framework.api.config.FrameworkConfig;
import com.mrcrayfish.framework.api.config.IntProperty;
import com.mrcrayfish.framework.api.config.ListProperty;
import com.mrcrayfish.framework.api.config.validate.Validator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

/**
 * Author: MrCrayfish
 */
public class Config
{
    @FrameworkConfig(id = Constants.MOD_ID, name = "server", type = ConfigType.SERVER_SYNC)
    public static final Server SERVER = new Server();

    public static class Server
    {
        @ConfigProperty(name = "maxMailboxesPerPlayer", comment = """
                The maximum amount of mailboxes a player is allowed to register/own.""")
        public final IntProperty maxMailboxesPerPlayer = IntProperty.create(16, 1, Integer.MAX_VALUE);

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

        @ConfigProperty(name = "allowedDimensions", comment = """
            A list of dimensions you are allowed to place mailboxes. An empty list means that
            mailboxes can be placed in any dimension.
            An example of how the list is defined:
            allowedDimensions = [
                "minecraft:overworld",
                "minecraft:the_nether",
                ...
            ]
            ^ Note: This is just an example. Write your list below.""")
        public final ListProperty<String> allowedDimensions = ListProperty.create(ListProperty.STRING, new Validator<>() {
            @Override
            public boolean test(String value) {
                return ResourceLocation.isValidResourceLocation(value);
            }
            @Override
            public Component getHint() {
                return Component.literal("Must a valid ResourceLocation, e.g. \"namespace:path\"");
            }
        });
    }
}
