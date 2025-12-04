/*
 * Copyright (c) xgraza 2025
 */

package rated.x;

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

    private final InputManager inputManager = new InputManager();
    private final ModuleManager moduleManager = new ModuleManager();

    void init()
    {
        inputManager.init();
        moduleManager.init(inputManager);
    }

    public InputManager getInputManager()
    {
        return inputManager;
    }

    public ModuleManager getModuleManager()
    {
        return moduleManager;
    }
}
