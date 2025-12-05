package rated.x.module.impl.visual;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import rated.x.gui.GUIModule;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

import java.util.UUID;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "Ping",
        description = "Displays your latency to the server (or 0ms in singleplayer)",
        category = ModuleCategory.VISUAL)
public final class ModulePing extends GUIModule
{
    @Override
    public void render(final GuiGraphics graphics,
                       final int screenWidth,
                       final int screenHeight,
                       final boolean preview)
    {
        super.render(graphics, screenWidth, screenHeight, preview);

        final String text = String.format("Ping: %s%sms",
                ChatFormatting.GRAY, getLatency());

        setWidth(MC.font.width(text) + 4);
        setHeight(MC.font.lineHeight + 2);

        graphics.drawString(MC.font,
                text,
                (int) (x + 2), (int) (y + 2), -1);
    }

    private int getLatency()
    {
        if (MC.isSingleplayer())
        {
            return 0;
        }
        final UUID uuid = MC.player.getUUID();
        return Math.max(0, MC.player.connection.getPlayerInfo(uuid).getLatency());
    }
}
