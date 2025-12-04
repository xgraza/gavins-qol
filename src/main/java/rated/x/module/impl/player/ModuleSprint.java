/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.impl.player;

import rated.x.listener.bus.Listener;
import rated.x.listener.event.EventTick;
import rated.x.module.Module;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "Sprint",
        description = "Enables sprinting for you",
        category = ModuleCategory.PLAYER)
public final class ModuleSprint extends Module
{
    @Override
    public void onDisable()
    {
        super.onDisable();
        if (MC.options == null)
        {
            return;
        }
        MC.options.keySprint.setDown(false);
    }

    @Listener
    public void onTick(final EventTick event)
    {
        MC.options.keySprint.setDown(true);
    }
}
