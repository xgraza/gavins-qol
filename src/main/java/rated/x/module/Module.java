/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import rated.x.RatedX;
import rated.x.config.IConfig;
import rated.x.input.Input;
import rated.x.input.InputHandler;
import rated.x.module.property.PropertyContainer;

import java.io.File;

/**
 * @author xgraza
 * @since 1.0.0
 */
public class Module extends PropertyContainer implements InputHandler, IConfig
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
        input = new Input(this, manifest.name(), GLFW.GLFW_KEY_UNKNOWN, -1);
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

    @Override
    public JsonElement toJSON()
    {
        final JsonObject object = new JsonObject();
        object.addProperty("enabled", enabled);
        return object;
    }

    @Override
    public void fromJSON(final JsonElement element)
    {
        if (!element.isJsonObject())
        {
            return;
        }
        final JsonObject object = element.getAsJsonObject();
        if (object.has("enabled"))
        {
            setEnabled(object.get("enabled").getAsBoolean());
        }
    }

    @Override
    public File getLocation()
    {
        return new File(RatedX.INSTANCE.getModuleManager().getConfigDirectory(),
                manifest.name() + ".json");
    }
}
