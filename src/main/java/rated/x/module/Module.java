/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module;

import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import rated.x.RatedX;
import rated.x.input.Input;
import rated.x.input.InputHandler;
import rated.x.module.property.PropertyContainer;

/**
 * @author xgraza
 * @since 1.0.0
 */
public class Module extends PropertyContainer implements InputHandler
{
    static final String NO_DESCRIPTION = "No description was provided for this module";

    protected static final Minecraft MC = Minecraft.getInstance();

    private final ModuleManifest manifest;
    private final Input input;
    private boolean enabled;

    public Module()
    {
        manifest = getClass().getDeclaredAnnotation(ModuleManifest.class);
        if (manifest == null)
        {
            throw new RuntimeException("@ModuleManifest required!");
        }
        input = new Input(this, GLFW.GLFW_KEY_UNKNOWN, -1, true);
    }

    @Override
    public void onEnable()
    {
        RatedX.EVENT_BUS.subscribe(this);
    }

    @Override
    public void onDisable()
    {
        RatedX.EVENT_BUS.unsubscribe(this);
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
        if (enabled)
        {
            onEnable();
        } else
        {
            onDisable();
        }
    }

    @Override
    public Input getInput()
    {
        return input;
    }

    public ModuleManifest getManifest()
    {
        return manifest;
    }
}
