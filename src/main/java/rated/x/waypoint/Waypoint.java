package rated.x.waypoint;

import net.minecraft.world.phys.Vec3;

/**
 * @param name
 * @param position
 * @param color
 * @author xgraza
 * @since 1.0.0
 */
public record Waypoint(String name, Vec3 position, int color)
{
}
