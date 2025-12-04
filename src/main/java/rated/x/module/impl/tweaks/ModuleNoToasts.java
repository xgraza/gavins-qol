/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.impl.tweaks;

import rated.x.listener.bus.Listener;
import rated.x.listener.event.EventRenderToasts;
import rated.x.module.Module;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "NoToasts",
        description = "Removes persistent toasts from your HUD",
        category = ModuleCategory.TWEAKS)
public final class ModuleNoToasts extends Module
{
    @Listener
    public void onRenderToasts(final EventRenderToasts event)
    {
        event.cancel();
    }
}
