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
    @Listener
    public void onTick(final EventTick event)
    {
        assert MC.player != null;
        if (!MC.player.isSprinting())
        {
            MC.player.setSprinting(true);
        }
    }
}
