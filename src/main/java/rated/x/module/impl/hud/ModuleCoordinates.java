package rated.x.module.impl.hud;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.phys.Vec3;
import rated.x.gui.GUIModule;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "Coordinates",
        description = "Displays your current coordinates",
        category = ModuleCategory.HUD)
public final class ModuleCoordinates extends GUIModule
{
    @Override
    public void render(final GuiGraphics graphics,
                       final int screenWidth,
                       final int screenHeight,
                       final boolean preview)
    {
        super.render(graphics, screenWidth, screenHeight, preview);

        setHeight((MC.font.lineHeight + 1) * 3);

        final Vec3 position = MC.player.position();
        final String[] text = new String[]
                {
                        String.format("X: %s%.1f%s", ChatFormatting.GRAY, position.x, ChatFormatting.RESET),
                        String.format("Y: %s%.1f%s", ChatFormatting.GRAY, position.y, ChatFormatting.RESET),
                        String.format("Z: %s%.1f%s", ChatFormatting.GRAY, position.z, ChatFormatting.RESET)
                };

        final ResourceLocation dimension = MC.level.dimension().location();
        if (BuiltinDimensionTypes.NETHER.location().equals(dimension))
        {
            text[0] += ChatFormatting.BLUE + " " + String.format("%.1f", position.x * 8.0);
            text[2] += ChatFormatting.BLUE + " " + String.format("%.1f", position.z * 8.0);
        } else if (BuiltinDimensionTypes.OVERWORLD.location().equals(dimension))
        {
            text[0] += ChatFormatting.RED + " " + String.format("%.1f", position.x / 8.0);
            text[2] += ChatFormatting.RED + " " + String.format("%.1f", position.z / 8.0);
        }

        for (int i = 0; i < 3; ++i)
        {
            final String textLine = text[i];

            int width = MC.font.width(textLine) + 4;
            if (width > getWidth())
            {
                setWidth(width);
            }

            graphics.drawString(MC.font, textLine,
                    (int) x + 2, (int) (y + (i * MC.font.lineHeight) + 2), -1);
        }
    }
}
