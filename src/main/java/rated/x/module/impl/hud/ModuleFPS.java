package rated.x.module.impl.hud;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import rated.x.gui.GUIModule;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "FPS",
        description = "Displays the Frames Per Second (FPS) counter",
        category = ModuleCategory.HUD)
public final class ModuleFPS extends GUIModule
{
    @Override
    public void render(final GuiGraphics graphics,
                       final int screenWidth,
                       final int screenHeight,
                       final boolean preview)
    {
        super.render(graphics, screenWidth, screenHeight, preview);

        final String text = String.format("FPS: %s%s",
                ChatFormatting.GRAY, MC.getFps());

        setWidth(MC.font.width(text) + 4);
        setHeight(MC.font.lineHeight + 2);

        graphics.drawString(MC.font,
                text,
                (int) (x + 2), (int) (y + 2), -1);
    }
}
