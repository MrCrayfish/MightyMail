package com.mrcrayfish.mightymail.platform;

import com.mrcrayfish.mightymail.Constants;
import com.mrcrayfish.mightymail.platform.services.IClientHelper;

import java.util.ServiceLoader;

/**
 * Author: MrCrayfish
 */
public class ClientServices
{
    public static final IClientHelper PLATFORM = load(IClientHelper.class);

    public static <T> T load(Class<T> clazz)
    {
        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
