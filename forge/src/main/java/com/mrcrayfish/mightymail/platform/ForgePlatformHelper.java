package com.mrcrayfish.mightymail.platform;

import com.mrcrayfish.mightymail.MightyMail;
import net.minecraft.world.item.CreativeModeTab;

/**
 * Author: MrCrayfish
 */
public class ForgePlatformHelper implements IPlatformHelper
{
    @Override
    public CreativeModeTab getCreativeModeTab()
    {
        return MightyMail.CREATIVE_TAB;
    }
}
