/*
 * Copyright (c) xgraza 2025
 */

package rated.x.gui.clickgui.elements;

import net.minecraft.client.gui.GuiGraphics;
import org.lwjgl.glfw.GLFW;
import rated.x.gui.clickgui.Component;
import rated.x.module.Module;

import java.awt.Color;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class ModuleButton extends Component
{
    private static final int BACKGROUND_COLOR = new Color(30, 30, 30).getRGB();
    private static final int ENABLED_COLOR = new Color(155, 128, 175).getRGB();

    private final rated.x.module.Module module;

    public ModuleButton(final Module module, double x, double y, double width, double height)
    {
        super(x, y, width, height);
        this.module = module;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY)
    {
        graphics.fill((int) x, (int) y, (int) (x + width), (int) (y + height), BACKGROUND_COLOR);
        graphics.drawString(font, module.getManifest().name(), (int) x + 4, (int) y + 6, module.isEnabled() ? ENABLED_COLOR : -1);

        graphics.pose().pushMatrix();
        graphics.pose().translate((float) (x + 4), (float) (y + 18));
        graphics.pose().scale(0.75f, 0.75f);
        graphics.drawString(font, module.getManifest().description(), 0, 0, 0xFFAAAAAA);
        graphics.pose().scale(1, 1);
        graphics.pose().popMatrix();
    }

    @Override
    public void mouseClick(double x, double y, int button)
    {
        if (isMouseIn(x, y) && button == GLFW.GLFW_MOUSE_BUTTON_1)
        {
            module.setEnabled(!module.isEnabled());
        }
    }
}