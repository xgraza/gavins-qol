package rated.x.module.impl.player;

import rated.x.module.MayBeConsideredACheat;
import rated.x.module.Module;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;
import rated.x.module.property.Property;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "AFK",
        description = "Does various things to aid in AFKing",
        category = ModuleCategory.PLAYER)
@MayBeConsideredACheat
public final class ModuleAFK extends Module
{
    private final Property<Boolean> disconnectProperty = new Property<Boolean>("Disconnect")
            .setValue(false)
            .setDescription("If to disconnect on an event");

}
