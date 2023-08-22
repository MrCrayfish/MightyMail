package com.mrcrayfish.mightymail;

import com.mrcrayfish.framework.FrameworkSetup;
import com.mrcrayfish.framework.Registration;
import com.mrcrayfish.mightymail.core.ModBlocks;
import com.mrcrayfish.mightymail.util.Utils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class MightyMail implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        FrameworkSetup.run();
        Bootstrap.init();

        CreativeModeTab.Builder builder = FabricItemGroup.builder(Utils.resource("creative_tab"));
        builder.icon(() -> new ItemStack(ModBlocks.MAIL_BOX_OAK.get()));
        builder.title(Component.translatable("itemGroup." + Constants.MOD_ID));
        builder.displayItems((params, output) -> {
            Registration.get(Registries.BLOCK).stream().filter(entry -> entry.getId().getNamespace().equals(Constants.MOD_ID)).forEach(entry -> {
                output.accept((Block) entry.get());
            });
        });
        builder.build();
    }
}
