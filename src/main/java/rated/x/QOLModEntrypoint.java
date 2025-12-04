/*
 * Copyright (c) xgraza 2025
 */

package rated.x;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rated.x.qol.BuildConfig;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class QOLModEntrypoint implements ClientModInitializer
{
    private static final Logger LOGGER = LoggerFactory.getLogger("Gavin's QOL");

    @Override public void onInitializeClient()
    {
        LOGGER.info("Initializing {} v{}-{}.{}/{}. This build was built at {}",
                BuildConfig.GROUP,
                BuildConfig.VERSION,
                BuildConfig.BRANCH,
                BuildConfig.BUILD,
                BuildConfig.HASH,
                BuildConfig.BUILD_TIME);
        RatedX.INSTANCE.init();
    }
}
