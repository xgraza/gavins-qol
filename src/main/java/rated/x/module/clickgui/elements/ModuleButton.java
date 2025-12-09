/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.clickgui.elements;

import net.minecraft.client.gui.GuiGraphics;
import org.lwjgl.glfw.GLFW;
import rated.x.input.Input;
import rated.x.module.Module;
import rated.x.module.clickgui.Component;
import rated.x.util.InputUtil;

import java.awt.Color;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class ModuleButton extends Component
{
    private static final int BACKGROUND_COLOR = new Color(30, 30, 30).getRGB();
    private static final int KEY_BACKGROUND_COLOR = new Color(10, 10, 10).getRGB();
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

        renderInput(graphics);

        graphics.pose().pushMatrix();
        graphics.pose().translate((float) (x + 4), (float) (y + 18));
        graphics.pose().scale(0.75f, 0.75f);
        graphics.drawString(font, module.getManifest().description(), 0, 0, 0xFFAAAAAA);
        graphics.pose().scale(1, 1);
        graphics.pose().popMatrix();
    }

    private void renderInput(final GuiGraphics graphics)
    {
        final Input input = module.getInput();
        if (input.getKey() == GLFW.GLFW_KEY_UNKNOWN)
        {
            return;
        }

        final String keyName = InputUtil.getKeyName(input.getKey());
        int width = font.width(keyName) + 4;

        int x = (int) (this.x + this.width - width - 6);
        int y = (int) (this.y + (height / 2) - 5);

        graphics.fill(x, y, x + width, y + 10, KEY_BACKGROUND_COLOR);
        graphics.drawString(font, keyName, x + 2, y + 1, -1);
    }

    @Override
    public void mouseClick(double x, double y, int button)
    {
        if (isMouseIn(x, y) && button == GLFW.GLFW_MOUSE_BUTTON_1)
        {
            module.setEnabled(!module.isEnabled());
        }
    }

    @Override
    public void mouseScroll(double mouseX, double mouseY, double scroll)
    {
        // empty
    }
}