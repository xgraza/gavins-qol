package rated.x.module.impl.hud;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import rated.x.gui.GUIModule;
import rated.x.module.ModuleCategory;
import rated.x.module.ModuleManifest;
import rated.x.module.property.Property;

/**
 * @author xgraza
 * @since 1.0.0
 */
@ModuleManifest(name = "Compass",
        description = "Shows a compass for navigation on screen",
        category = ModuleCategory.HUD)
public final class ModuleCompass extends GUIModule
{
    private static final double RADIUS = 25.0;
    private static final String[] DIRECTIONS_SIMPLE = {
            "W", "S", "E", ChatFormatting.RED + "N" };
    private static final String[] DIRECTIONS_ALL = {
            "W", "SW", "S", "SE", "E", "NE", ChatFormatting.RED + "N", "NW" };

    private final Property<Boolean> allDirectionsProperty = new Property<Boolean>("All Directions")
            .setValue(false)
            .setDescription("If to show the directions between the cardinal directions");

    @Override
    public void render(final GuiGraphics graphics,
                       final int screenWidth,
                       final int screenHeight,
                       final boolean preview)
    {
        super.render(graphics, screenWidth, screenHeight, preview);

        setWidth(65);
        setHeight(65);

        final String[] directions = allDirectionsProperty.getValue() ? DIRECTIONS_ALL : DIRECTIONS_SIMPLE;
        final int degreeChange = 360 / directions.length; // 90/4, 45/8

        float yaw = MC.player.getYRot();
        for (int i = 0; i < directions.length; ++i)
        {
            final double angle = Math.toRadians(yaw + (i * degreeChange));
            graphics.drawString(MC.font, directions[i],
                    (int) ((x + (width / 2) - 2) + Math.cos(angle) * RADIUS),
                    (int) ((y + (height / 2) - 4) - Math.sin(angle) * RADIUS), -1);
        }
    }
}
