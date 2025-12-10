/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.clickgui.elements;

import net.minecraft.client.gui.GuiGraphics;
import rated.x.RatedX;
import rated.x.module.Module;
import rated.x.module.ModuleCategory;
import rated.x.module.clickgui.Drawable;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class ModulePane extends Drawable
{
    private static final int BACKGROUND_COLOR = new Color(45, 45, 45).getRGB();

    private static final int SCROLL_AMOUNT = 10;
    private static final int ELEMENT_SPACING = 1;
    private static final double PADDING = 2.0;
    private static final double MODULE_BUTTON_HEIGHT = 30.0;

    private final List<ModuleButton> moduleButtonList = new LinkedList<>();
    private double scrollOffset;

    public ModulePane(final ModuleCategory category)
    {
        final List<Module> moduleList = RatedX.INSTANCE.getModuleManager().getModules()
                .stream()
                .filter((module) -> module.getManifest().category().equals(category))
                .toList();
        for (final Module module : moduleList)
        {
            moduleButtonList.add(new ModuleButton(module));
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY)
    {
        graphics.fill((int) x, (int) y, (int) (x + width), (int) (y + height), BACKGROUND_COLOR);

        double posY = y + PADDING + scrollOffset;
        for (final ModuleButton moduleButton : moduleButtonList)
        {
            moduleButton.setX(x + (PADDING * 2));
            moduleButton.setY(posY);
            moduleButton.setWidth(width - (PADDING * 4));
            moduleButton.setHeight(MODULE_BUTTON_HEIGHT);
            moduleButton.render(graphics, mouseX, mouseY);

            posY += moduleButton.getHeight() + ELEMENT_SPACING;
        }
    }

    @Override
    public void mouseClick(double x, double y, int button)
    {
        for (final ModuleButton moduleButton : moduleButtonList)
        {
            moduleButton.mouseClick(x, y, button);
        }
    }

    @Override
    public void mouseScroll(double mouseX, double mouseY, double scroll)
    {
        if (scroll > 0)
        {
            final double startHeight = y + PADDING;
            if (startHeight + scrollOffset < startHeight)
            {
                scrollOffset += SCROLL_AMOUNT;
            }
        } else if (scroll < 0)
        {
            if (height - getTotalComponentHeight() - scrollOffset <= -SCROLL_AMOUNT)
            {
                scrollOffset -= SCROLL_AMOUNT;
            }
        }
    }

    private double getTotalComponentHeight()
    {
        double height = PADDING * 2;
        for (final ModuleButton moduleButton : moduleButtonList)
        {
            height += moduleButton.getHeight() + ELEMENT_SPACING;
        }
        return height;
    }
}
