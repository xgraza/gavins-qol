package rated.x.module.clickgui.elements.window;

import net.minecraft.client.gui.GuiGraphics;
import rated.x.RatedX;
import rated.x.gui.GUIEditorScreen;
import rated.x.module.clickgui.Drawable;

import java.awt.Color;

/**
 * @author xgraza
 * @since 1.0.0
 */
final class GuiEditorButton extends Drawable
{
    private static final int BACKGROUND_COLOR = new Color(35, 35, 35).getRGB();

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY)
    {
        graphics.fill((int) x, (int) y, (int) (x + width), (int) (y + height), BACKGROUND_COLOR);
        graphics.renderOutline((int) x, (int) y, (int) (width), (int) (height), 0xFFAAAAAA);
        graphics.drawString(font, "GUI Editor", (int) x + 6, (int) y + 2, -1);
    }

    @Override
    public void mouseClick(double x, double y, int button)
    {
        MC.setScreen(new GUIEditorScreen(RatedX.INSTANCE.getGUIManager()));
    }

    @Override
    public void mouseScroll(double mouseX, double mouseY, double scroll)
    {
        // empty
    }
}
