/*
 * Copyright (c) xgraza 2025
 */

package rated.x;

import rated.x.config.ConfigManager;
import rated.x.gui.GUIManager;
import rated.x.input.InputManager;
import rated.x.listener.bus.EventBus;
import rated.x.module.ModuleManager;

/**
 * @author xgraza
 * @since 1.0.0
 */
public enum RatedX
{
    INSTANCE;

    public static final EventBus EVENT_BUS = new EventBus();

    private final ConfigManager configManager = new ConfigManager();
    private final InputManager inputManager = new InputManager();
    private final GUIManager guiManager = new GUIManager();
    private final ModuleManager moduleManager = new ModuleManager();

    void init()
    {
        configManager.init();
        inputManager.init();
        guiManager.init();
        moduleManager.init(configManager, inputManager, guiManager);

        configManager.load();
    }

    public ConfigManager getConfigManager()
    {
        return configManager;
    }

    public InputManager getInputManager()
    {
        return inputManager;
    }

    public GUIManager getGUIManager()
    {
        return guiManager;
    }

    public ModuleManager getModuleManager()
    {
        return moduleManager;
    }
}
