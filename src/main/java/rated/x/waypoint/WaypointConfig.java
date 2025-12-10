package rated.x.waypoint;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.world.phys.Vec3;
import rated.x.config.IConfig;

import java.io.File;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class WaypointConfig implements IConfig
{
    private final WaypointManager waypointManager;

    public WaypointConfig(final WaypointManager waypointManager)
    {
        this.waypointManager = waypointManager;
    }

    @Override
    public File getLocation()
    {
        return waypointManager.getWaypointFileLocation();
    }

    @Override
    public JsonElement toJSON()
    {
        final JsonArray array = new JsonArray();
        for (final Waypoint waypoint : waypointManager.getWaypoints())
        {
            final JsonObject object = new JsonObject();
            {
                object.addProperty("name", waypoint.name());
                object.addProperty("x", waypoint.position().x());
                object.addProperty("y", waypoint.position().y());
                object.addProperty("z", waypoint.position().z());
                object.addProperty("color", waypoint.color());
            }
            array.add(object);
        }
        return array;
    }

    @Override
    public void fromJSON(final JsonElement element)
    {
        if (!element.isJsonArray())
        {
            return;
        }
        final JsonArray array = element.getAsJsonArray();
        for (final JsonElement e : array)
        {
            if (!e.isJsonObject())
            {
                continue;
            }
            final JsonObject object = e.getAsJsonObject();

            final String name = object.get("name").getAsString();
            final int color = object.get("color").getAsInt();
            final double x = object.get("x").getAsDouble();
            final double y = object.get("y").getAsDouble();
            final double z = object.get("z").getAsDouble();
            waypointManager.addWaypoint(new Waypoint(name, new Vec3(x, y, z), color));
        }
    }
}
