/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rated.x.config.ConfigManager;
import rated.x.gui.GUIManager;
import rated.x.gui.GUIModule;
import rated.x.input.InputManager;
import rated.x.module.impl.player.ModuleSprint;
import rated.x.module.impl.tweaks.ModuleNoToasts;
import rated.x.module.impl.visual.*;
import rated.x.util.FileUtil;

import java.io.File;
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
    private File configDirectory;

    private ConfigManager configManager;
    private InputManager inputManager;
    private GUIManager guiManager;

    public void init(final ConfigManager configManager,
                     final InputManager inputManager,
                     final GUIManager guiManager)
    {
        this.configManager = configManager;
        this.inputManager = inputManager;
        this.guiManager = guiManager;

        configDirectory = FileUtil.createNewRootDirectory("modules");

        addModules();
    }

    private void addModules()
    {
        addModule(new ModuleSprint());
        addModule(new ModuleNoToasts());
        addModule(new ModuleArmor());
        addModule(new ModuleClickGUI());
        addModule(new ModuleCoordinates());
        addModule(new ModuleFPS());
        addModule(new ModuleFullbright());
        addModule(new ModulePing());
        LOGGER.info("Registered {} modules", moduleList.size());
    }

    public void addModule(final Module module)
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
        if (module instanceof GUIModule)
        {
            guiManager.addModule((GUIModule) module);
        }
        configManager.addConfig(module);
    }

    public List<Module> getModules()
    {
        return moduleList;
    }

    public File getConfigDirectory()
    {
        return configDirectory;
    }
}
