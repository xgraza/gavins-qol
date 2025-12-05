/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.clickgui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import rated.x.RatedX;
import rated.x.module.clickgui.elements.Window;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class ClickGUIScreen extends Screen
{
    private Window window;

    public ClickGUIScreen()
    {
        super(Component.literal("RatedX ClickGUI"));
    }

    @Override
    protected void init()
    {
        if (window != null)
        {
            return;
        }
        // halfs
        int hw = width / 2;
        int hh = height / 2;

        double width = 400;
        double height = 250;

        window = new Window(hw - (width / 2.0), hh - (height / 2.0), width, height);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float tickDelta)
    {
        window.render(graphics, mouseX, mouseY);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f)
    {
        // intentionally empty
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean down)
    {
        window.mouseClick(event.x(), event.y(), event.button());
        return super.mouseClicked(event, down);
    }

    @Override
    public void onClose()
    {
        super.onClose();
        RatedX.INSTANCE.getConfigManager().save();
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
