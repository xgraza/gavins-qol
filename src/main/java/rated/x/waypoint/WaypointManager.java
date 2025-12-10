package rated.x.waypoint;

import rated.x.RatedX;
import rated.x.util.FileUtil;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class WaypointManager
{
    private final List<Waypoint> waypointList = new LinkedList<>();

    private File waypointFileLocation;
    private boolean renderEnabled = true;

    public void init()
    {
        waypointFileLocation = new File(FileUtil.ROOT, "waypoints.json");
        RatedX.INSTANCE.getConfigManager()
                .addConfig(new WaypointConfig(this));
    }

    public void setRenderEnabled(boolean renderEnabled)
    {
        this.renderEnabled = renderEnabled;
    }

    public boolean isRenderEnabled()
    {
        // don't bother rendering if we have no waypoints...
        if (waypointList.isEmpty())
        {
            return false;
        }
        return renderEnabled;
    }

    public void addWaypoint(final Waypoint waypoint)
    {
        waypointList.add(waypoint);
    }

    public List<Waypoint> getWaypoints()
    {
        return waypointList;
    }

    public File getWaypointFileLocation()
    {
        return waypointFileLocation;
    }
}
