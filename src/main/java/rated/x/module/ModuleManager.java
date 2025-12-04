/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rated.x.input.InputManager;
import rated.x.module.impl.visual.ModuleClickGUI;
import rated.x.module.impl.visual.ModuleFullbright;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class ModuleManager
{
    private static final Logger LOGGER = LoggerFactory.getLogger("ModuleManager");

    private final Map<Class<? extends Module>, Module> moduleInstanceMap = new LinkedHashMap<>();
    private final List<Module> moduleList = new LinkedList<>();
    private InputManager inputManager;

    public void init(final InputManager inputManager)
    {
        this.inputManager = inputManager;
        addModule(new ModuleFullbright());
        addModule(new ModuleClickGUI());
        LOGGER.info("Registered {} modules", moduleList.size());
    }

    private void addModule(final Module module)
    {
        try
        {
            module.loadProperties();
            inputManager.registerInput(module.getInput());
        } catch (final IllegalAccessException e)
        {
            LOGGER.error("Failed to load properties", e);
            return;
        }
        moduleInstanceMap.put(module.getClass(), module);
        moduleList.add(module);
    }

    public List<Module> getModules()
    {
        return moduleList;
    }
}
