/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.impl.visual;

import rated.x.listener.bus.Listener;
import rated.x.listener.event.EventGamma;
import rated.x.module.Module;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "Fullbright",
        category = ModuleCategory.VISUAL,
        description = "Allows you to be able to see in the dark")
public final class ModuleFullbright extends Module
{
    @Listener
    public void onGamma(final EventGamma event)
    {
        event.setGamma(100.0f);
        event.cancel();
    }
}
