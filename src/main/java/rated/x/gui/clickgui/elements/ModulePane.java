/*
 * Copyright (c) xgraza 2025
 */

package rated.x.gui.clickgui.elements;

import net.minecraft.client.gui.GuiGraphics;
import rated.x.RatedX;
import rated.x.gui.clickgui.Component;
import rated.x.module.Module;
import rated.x.module.ModuleCategory;

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
        //graphics.drawString(font, "this is different " + hashCode(), (int) x, (int) y, -1);

        double posY = y + 2;
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

    }

    static final class ModuleButton extends Component
    {
        private static final int BACKGROUND_COLOR = new Color(30, 30, 30).getRGB();
        private static final int ENABLED_COLOR = new Color(155, 128, 175).getRGB();

        private final Module module;

        public ModuleButton(final Module module, double x, double y, double width, double height)
        {
            super(x, y, width, height);
            this.module = module;
        }

        @Override
        public void render(GuiGraphics graphics, int mouseX, int mouseY)
        {
            graphics.fill((int) x, (int) y, (int) (x + width), (int) (y + height), new Color(30, 30, 30).getRGB());
            graphics.drawString(font, module.getManifest().name(), (int) x + 4, (int) y + 6, -1);

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

        }
    }
}
