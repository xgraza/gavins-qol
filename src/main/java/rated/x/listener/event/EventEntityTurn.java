package rated.x.listener.event;

import net.minecraft.world.entity.Entity;
import rated.x.listener.bus.EventCancelable;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class EventEntityTurn extends EventCancelable
{
    private final Entity entity;
    private final double x, y;

    public EventEntityTurn(final Entity entity, final double x, final double y)
    {
        this.entity = entity;
        this.x = x;
        this.y = y;
    }

    public Entity getEntity()
    {
        return entity;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }
}
