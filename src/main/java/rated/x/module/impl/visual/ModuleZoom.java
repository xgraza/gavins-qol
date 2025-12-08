package rated.x.module.impl.visual;

import org.lwjgl.glfw.GLFW;
import rated.x.mixin.duck.IOptionInstance;
import rated.x.module.Module;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "Zoom",
        description = "Zooms your FoV to be able to see better",
        category = ModuleCategory.VISUAL)
public final class ModuleZoom extends Module
{
    private int oldFOV = -1;

    public ModuleZoom()
    {
        getInput().setKey(GLFW.GLFW_KEY_C);
        getInput().setPersistent(false);
    }

    @Override
    public void onEnable()
    {
        super.onEnable();
        if (MC.options == null || MC.player == null || MC.level == null)
        {
            setEnabled(false);
            return;
        }
        oldFOV = MC.options.fov().get();
        forceSetFOV(20);
    }

    @Override
    public void onDisable()
    {
        super.onDisable();
        if (oldFOV != -1)
        {
            forceSetFOV(oldFOV);
        }
        oldFOV = -1;
    }

    @SuppressWarnings("unchecked")
    private void forceSetFOV(final int value)
    {
        ((IOptionInstance<Integer>) (Object) MC.options.fov()).hook_ratedX$setValue(value);
    }
}
