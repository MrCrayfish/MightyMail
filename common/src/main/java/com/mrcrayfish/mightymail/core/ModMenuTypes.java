package com.mrcrayfish.mightymail.core;

import com.mrcrayfish.framework.api.registry.RegistryContainer;
import com.mrcrayfish.framework.api.registry.RegistryEntry;
import com.mrcrayfish.mightymail.inventory.PostBoxMenu;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.world.inventory.MenuType;

/**
 * Author: MrCrayfish
 */
@RegistryContainer
public class ModMenuTypes
{
    public static final RegistryEntry<MenuType<PostBoxMenu>> POST_BOX = RegistryEntry.menuType(Utils.resource("post_box"), PostBoxMenu::new);

}
