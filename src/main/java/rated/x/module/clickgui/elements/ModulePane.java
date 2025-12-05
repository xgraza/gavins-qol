/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.clickgui.elements;

import net.minecraft.client.gui.GuiGraphics;
import rated.x.RatedX;
import rated.x.module.Module;
import rated.x.module.ModuleCategory;
import rated.x.module.clickgui.Component;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class ModulePane extends Component
{
    private static final int BACKGROUND_COLOR = new Color(45, 45, 45).getRGB();

    private final List<ModuleButton> moduleButtonList = new LinkedList<>();
    private double scrollOffset;

    public ModulePane(final ModuleCategory category, double x, double y, double width, double height)
    {
        super(x, y, width, height);

        final List<Module> moduleList = RatedX.INSTANCE.getModuleManager().getModules()
                .stream()
                .filter((module) -> module.getManifest().category().equals(category))
                .toList();
        for (final Module module : moduleList)
        {
            moduleButtonList.add(new ModuleButton(module, 0, 0, 0, 0));
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY)
    {
        graphics.fill((int) x, (int) y, (int) (x + width), (int) (y + height), BACKGROUND_COLOR);

        double posY = y + 2 + scrollOffset;
        for (final ModuleButton moduleButton : moduleButtonList)
        {
            moduleButton.setX(x + 4);
            moduleButton.setY(posY);
            moduleButton.setWidth(width - 8);
            moduleButton.setHeight(30);
            moduleButton.render(graphics, mouseX, mouseY);

            posY += moduleButton.getHeight() + 1;
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
            final double startHeight = y + 2;
            if (startHeight + scrollOffset < startHeight)
            {
                scrollOffset += 10;
            }
        } else if (scroll < 0)
        {
            if (height - getTotalComponentHeight() - scrollOffset <= -10)
            {
                scrollOffset -= 10;
            }
        }
    }

    private double getTotalComponentHeight()
    {
        double height = 4;
        for (final ModuleButton moduleButton : moduleButtonList)
        {
            height += moduleButton.getHeight() + 1;
        }
        return height;
    }
}
