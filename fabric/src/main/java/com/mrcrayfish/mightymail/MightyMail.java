package com.mrcrayfish.mightymail;

import com.mrcrayfish.framework.FrameworkSetup;
import com.mrcrayfish.mightymail.core.ModBlocks;
import com.mrcrayfish.mightymail.util.Utils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MightyMail implements ModInitializer
{
    public static final CreativeModeTab CREATIVE_TAB = FabricItemGroupBuilder.build(Utils.resource("creative_tab"), () -> {
        return new ItemStack(ModBlocks.MAIL_BOX_OAK.get());
    });

    @Override
    public void onInitialize()
    {
        FrameworkSetup.run();
        Bootstrap.init();
    }
}
