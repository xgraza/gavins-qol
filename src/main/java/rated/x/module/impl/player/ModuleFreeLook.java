package rated.x.module.impl.player;

import net.minecraft.client.CameraType;
import net.minecraft.util.Mth;
import rated.x.listener.bus.Listener;
import rated.x.listener.event.EventEntityTurn;
import rated.x.listener.event.EventTick;
import rated.x.module.MayBeConsideredACheat;
import rated.x.module.Module;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "FreeLook",
        description = "Allows you to freely move your camera around your player",
        category = ModuleCategory.PLAYER)
@MayBeConsideredACheat
public final class ModuleFreeLook extends Module
{
    public static final ModuleFreeLook INSTANCE = new ModuleFreeLook();

    private boolean overrideRotate;
    private float x, y;

    @Override
    public void onDisable()
    {
        super.onDisable();
        overrideRotate = false;
    }

    @Listener
    public void onTick(final EventTick event)
    {
        if (!overrideRotate || MC.options == null)
        {
            return;
        }
        final CameraType cameraType = MC.options.getCameraType();
        if (cameraType.equals(CameraType.THIRD_PERSON_FRONT))
        {
            MC.options.setCameraType(CameraType.FIRST_PERSON);
        }
    }

    @Listener
    public void onEntityTurn(final EventEntityTurn event)
    {
        if (!event.getEntity().equals(MC.player) || MC.options.getCameraType().isFirstPerson())
        {
            overrideRotate = false;
            return;
        }
        x += (float) event.getX();
        y += (float) event.getY();
        y = Mth.clamp(y, -90.0f, 90.0f);
        overrideRotate = true;
        event.cancel();
    }

    public boolean isOverrideRotate()
    {
        return overrideRotate;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }
}
