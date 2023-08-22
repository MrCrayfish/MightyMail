package com.mrcrayfish.mightymail.platform;

import com.mrcrayfish.mightymail.Constants;

import java.util.ServiceLoader;

/**
 * Author: MrCrayfish
 */
public class Services
{
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> serviceClass)
    {
        final T loadedService = ServiceLoader.load(serviceClass).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + serviceClass.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, serviceClass);
        return loadedService;
    }
}
