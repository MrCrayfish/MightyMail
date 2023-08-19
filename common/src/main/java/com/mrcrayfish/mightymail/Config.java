package com.mrcrayfish.mightymail;

import com.mrcrayfish.framework.api.config.ConfigProperty;
import com.mrcrayfish.framework.api.config.ConfigType;
import com.mrcrayfish.framework.api.config.FrameworkConfig;
import com.mrcrayfish.framework.api.config.IntProperty;

/**
 * Author: MrCrayfish
 */
public class Config
{
    @FrameworkConfig(id = Constants.MOD_ID, name = "server", type = ConfigType.SERVER)
    public static final Server SERVER = new Server();

    public static class Server
    {
        @ConfigProperty(name = "mailboxInventoryRows", comment = "The maximum amount of items that can be queued for delivery for a mail box")
        public final IntProperty mailboxInventoryRows = IntProperty.create(1, 1, 6);

        @ConfigProperty(name = "mailQueueSize", comment = "The maximum amount of items that can be queued for delivery for a mail box")
        public final IntProperty mailQueueSize = IntProperty.create(18, 0, 256);
    }
}
