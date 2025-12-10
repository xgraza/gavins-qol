/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.clickgui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import rated.x.RatedX;
import rated.x.module.clickgui.elements.window.Window;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class ClickGUIScreen extends Screen
{
    private static final double WINDOW_WIDTH = 400.0;
    private static final double WINDOW_HEIGHT = 250.0;

    private Window window;

    public ClickGUIScreen()
    {
        super(Component.literal("RatedX ClickGUI"));
    }

    @Override
    protected void init()
    {
        // init is called when the screen is created or resized
        final int centerWidth = width / 2;
        final int centerHeight = height / 2;

        final double x = centerWidth - (WINDOW_WIDTH / 2.0);
        final double y = centerHeight - (WINDOW_HEIGHT / 2.0);

        if (window == null)
        {
            window = new Window(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
        } else
        {
            window.setX(x);
            window.setY(y);
        }
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

    @Override public boolean mouseScrolled(double mouseX, double mouseY, double f, double scroll)
    {
        window.mouseScroll(mouseX, mouseY, scroll);
        return super.mouseScrolled(mouseX, mouseX, f, scroll);
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
