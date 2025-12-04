/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.clickgui.elements;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import org.lwjgl.glfw.GLFW;
import rated.x.module.ModuleCategory;
import rated.x.module.clickgui.Component;
import rated.x.qol.BuildConfig;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class Window extends Component
{
    private static final int BACKGROUND_COLOR = new Color(25, 25, 25).getRGB();

    private final List<CategoryTab> tabList = new ArrayList<>();
    private CategoryTab selectedTab;
    private ModulePane modulePane;

    public Window(double x, double y, double width, double height)
    {
        super(x, y, width, height);
        for (final ModuleCategory category : ModuleCategory.values())
        {
            if (category.equals(ModuleCategory.DEFAULT))
            {
                continue;
            }
            final CategoryTab categoryTab = new CategoryTab(
                    category, 0, 0, 0, 0);
            if (selectedTab == null)
            {
                selectedTab = categoryTab;
                selectedTab.setSelected(true);
                modulePane = selectedTab.getModulePane();
            }
            tabList.add(categoryTab);
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY)
    {
        graphics.enableScissor((int) x, (int) y, (int) (x + width), (int) (y + height));

        graphics.fill((int) x, (int) y, (int) (x + width), (int) (y + height), BACKGROUND_COLOR);
        graphics.drawString(font, "Rated" + ChatFormatting.LIGHT_PURPLE + "X", (int) (x + 8), (int) (y + 10), -1);
        graphics.drawString(font, "v" + BuildConfig.VERSION + "_" + BuildConfig.BUILD + "-" + BuildConfig.BRANCH + "/" + BuildConfig.HASH, (int) (x + 4), (int) (y + height - 12), 0xFFBBBBBB);

        double widthPer = (width - 50) / tabList.size() - (tabList.size() * 2);
        double posX = x + 50;
        for (final CategoryTab categoryTab : tabList)
        {
            categoryTab.setX(posX);
            categoryTab.setY(y + 4);
            categoryTab.setWidth(widthPer);
            categoryTab.setHeight(20);
            categoryTab.render(graphics, mouseX, mouseY);
            posX += categoryTab.getWidth() + 4;
        }

        modulePane.setX(x + 4);
        modulePane.setY(y + 25);
        modulePane.setWidth(width - 8);
        modulePane.setHeight(height - 40);
        graphics.enableScissor((int) modulePane.getX(),
                (int) modulePane.getY(),
                (int) (modulePane.getX() + modulePane.getWidth()),
                (int) (modulePane.getY() + modulePane.getHeight()));
        modulePane.render(graphics, mouseX, mouseY);

        graphics.disableScissor();
    }

    @Override
    public void mouseClick(double x, double y, int button)
    {
        if (button == GLFW.GLFW_MOUSE_BUTTON_1)
        {
            for (final CategoryTab categoryTab : tabList)
            {
                if (categoryTab.isMouseIn(x, y))
                {
                    selectedTab.setSelected(false);
                    selectedTab = categoryTab;
                    selectedTab.setSelected(true);
                    modulePane = selectedTab.getModulePane();
                    return;
                }
            }
        }
        modulePane.mouseClick(x, y, button);
    }

    static final class CategoryTab extends Component
    {
        private static final int BACKGROUND_COLOR = new Color(35, 35, 35).getRGB();

        private final ModuleCategory category;
        private boolean selected;

        private final ModulePane modulePane;

        public CategoryTab(final ModuleCategory category, double x, double y, double width, double height)
        {
            super(x, y, width, height);
            this.category = category;
            modulePane = new ModulePane(category, 0, 0, 0, 0);
        }

        @Override
        public void render(GuiGraphics graphics, int mouseX, int mouseY)
        {
            graphics.fill((int) x, (int) y, (int) (x + width), (int) (y + height), BACKGROUND_COLOR);
            if (selected)
            {
                graphics.submitOutline((int) x, (int) y, (int) (width), (int) (height), 0xFFAAAAAA);
            }
            graphics.drawString(font, category.getName(), (int) x + 4, (int) y + 6, -1);
        }

        @Override
        public void mouseClick(double x, double y, int button)
        {

        }

        public void setSelected(boolean selected)
        {
            this.selected = selected;
        }

        public boolean isSelected()
        {
            return selected;
        }

        public ModulePane getModulePane()
        {
            return modulePane;
        }
    }
}
