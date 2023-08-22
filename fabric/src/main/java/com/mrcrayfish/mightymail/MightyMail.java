package com.mrcrayfish.mightymail;

import com.mrcrayfish.framework.FrameworkSetup;
import net.fabricmc.api.ModInitializer;

public class MightyMail implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        FrameworkSetup.run();
        Bootstrap.init();
    }
}
