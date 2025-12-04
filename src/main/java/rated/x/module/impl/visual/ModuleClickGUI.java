/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.impl.visual;

import org.lwjgl.glfw.GLFW;
import rated.x.gui.clickgui.ClickGUIScreen;
import rated.x.module.Module;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "ClickGUI",
        description = "Displays this GUI to change settings",
        category = ModuleCategory.VISUAL)
public final class ModuleClickGUI extends Module
{
    private ClickGUIScreen instance;

    public ModuleClickGUI()
    {
        getInput().setKey(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @Override
    public void onEnable()
    {
        super.onEnable();
        if (MC.level == null || MC.player == null)
        {
            setEnabled(false);
            return;
        }
        //if (instance == null)
        {
            instance = new ClickGUIScreen();
        }
        MC.setScreen(instance);
    }
}
