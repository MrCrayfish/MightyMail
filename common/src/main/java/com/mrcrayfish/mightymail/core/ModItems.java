package com.mrcrayfish.mightymail.core;

import com.mrcrayfish.framework.api.registry.RegistryContainer;
import com.mrcrayfish.framework.api.registry.RegistryEntry;
import com.mrcrayfish.mightymail.item.PackageItem;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.world.item.Item;

/**
 * Author: MrCrayfish
 */
@RegistryContainer
public class ModItems
{
    public static final RegistryEntry<Item> PACKAGE = RegistryEntry.item(Utils.resource("package"), () -> new PackageItem(new Item.Properties().stacksTo(1)));
}
