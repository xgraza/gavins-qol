/*
 * Copyright (c) xgraza 2025
 */

package rated.x.gui;

import net.minecraft.client.Minecraft;
import rated.x.RatedX;
import rated.x.listener.bus.Listener;
import rated.x.listener.event.EventRenderHUD;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class GUIManager
{
    private static final Minecraft MC = Minecraft.getInstance();

    private final Map<Class<? extends GUIModule>, GUIModule> moduleInstanceMap = new LinkedHashMap<>();
    private final List<GUIModule> moduleList = new LinkedList<>();

    public void init()
    {
        RatedX.EVENT_BUS.subscribe(this);
    }

    @Listener
    public void onRenderHUD(final EventRenderHUD event)
    {
        if (MC.screen instanceof GUIEditorScreen)
        {
            return;
        }
        for (final GUIModule guiModule : moduleList)
        {
            if (!guiModule.isEnabled())
            {
                continue;
            }
            guiModule.render(event.graphics(), event.screenWidth(), event.screenHeight(), false);
        }
    }

    public void addModule(final GUIModule module)
    {
        moduleInstanceMap.put(module.getClass(), module);
        moduleList.add(module);
    }

    public List<GUIModule> getModules()
    {
        return moduleList;
    }
}
