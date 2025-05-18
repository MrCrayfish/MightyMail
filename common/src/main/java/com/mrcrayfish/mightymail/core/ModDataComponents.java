package com.mrcrayfish.mightymail.core;

import com.mrcrayfish.framework.api.registry.RegistryContainer;
import com.mrcrayfish.framework.api.registry.RegistryEntry;
import com.mrcrayfish.mightymail.mail.PackageInfo;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.core.component.DataComponentType;

/**
 * Author: MrCrayfish
 */
@RegistryContainer
public class ModDataComponents
{
     public static final RegistryEntry<DataComponentType<PackageInfo>> PACKAGE_INFO = RegistryEntry.dataComponentType(Utils.resource("package_info"), builder -> {
        return builder.persistent(PackageInfo.CODEC).networkSynchronized(PackageInfo.STREAM_CODEC);
    });
}
