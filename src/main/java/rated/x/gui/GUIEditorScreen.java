/*
 * Copyright (c) xgraza 2025
 */

package rated.x.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import java.awt.Color;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class GUIEditorScreen extends Screen
{
    private static final int BASE_NOT_SELECTED = new Color(60, 60, 60, 120).getRGB();
    private static final int BASE_SELECTED = new Color(227, 217, 115, 120).getRGB();

    private final GUIManager guiManager;
    private GUIModule draggingElement;
    private double dragX, dragY;

    public GUIEditorScreen(final GUIManager guiManager)
    {
        super(Component.literal("GUI Editor"));
        this.guiManager = guiManager;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float tickDelta)
    {
        if (draggingElement != null)
        {
            draggingElement.setX(mouseX + dragX);
            draggingElement.setY(mouseY + dragY);
            draggingElement.doBoundsCheck(graphics.guiWidth(), graphics.guiHeight());
        }

        for (final GUIModule module : guiManager.getModules())
        {
            int color = module.isMouseIn(mouseX, mouseY) ? BASE_SELECTED : BASE_NOT_SELECTED;
            graphics.fill((int) module.getX(),
                    (int) module.getY(),
                    (int) (module.getX() + module.getWidth()),
                    (int) (module.getY() + module.getHeight()),
                    color);
            module.render(graphics, graphics.guiWidth(), graphics.guiHeight(), true);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean bl)
    {
        if (event.button() == GLFW.GLFW_MOUSE_BUTTON_1)
        {
            for (final GUIModule module : guiManager.getModules())
            {
                if (module.isMouseIn(event.x(), event.y()))
                {
                    dragX = module.getX() - event.x();
                    dragY = module.getY() - event.y();
                    draggingElement = module;
                    break;
                }
            }
        }

        return super.mouseClicked(event, bl);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event)
    {
        if (event.button() == GLFW.GLFW_MOUSE_BUTTON_1 && draggingElement != null)
        {
            draggingElement = null;
        }
        return super.mouseReleased(event);
    }
}
