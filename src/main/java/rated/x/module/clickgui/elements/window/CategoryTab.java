package rated.x.module.clickgui.elements.window;

import net.minecraft.client.gui.GuiGraphics;
import rated.x.module.ModuleCategory;
import rated.x.module.clickgui.Drawable;
import rated.x.module.clickgui.elements.ModulePane;

import java.awt.Color;

/**
 * @author xgraza
 * @since 1.0.0
 */
final class CategoryTab extends Drawable
{
    private static final int BACKGROUND_COLOR = new Color(35, 35, 35).getRGB();

    private final ModuleCategory category;
    private final ModulePane modulePane;

    private boolean selected;

    public CategoryTab(final ModuleCategory category)
    {
        this.category = category;
        modulePane = new ModulePane(category);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY)
    {
        graphics.fill((int) x, (int) y, (int) (x + width), (int) (y + height), BACKGROUND_COLOR);
        if (selected)
        {
            graphics.renderOutline((int) x, (int) y, (int) (width), (int) (height), 0xFFAAAAAA);
        }
        graphics.drawString(font, category.getName(), (int) x + 4, (int) y + 6, -1);
    }

    @Override
    public void mouseClick(double x, double y, int button)
    {
        // empty
    }

    @Override
    public void mouseScroll(double mouseX, double mouseY, double scroll)
    {
        // empty
    }

    public void setSelected(final boolean selected)
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