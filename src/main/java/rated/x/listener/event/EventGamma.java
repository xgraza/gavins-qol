/*
 * Copyright (c) xgraza 2025
 */

package rated.x.listener.event;

import rated.x.listener.bus.EventCancelable;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class EventGamma extends EventCancelable
{
    private float gamma;

    public EventGamma(float gamma)
    {
        this.gamma = gamma;
    }

    public float getGamma()
    {
        return gamma;
    }

    public void setGamma(float gamma)
    {
        this.gamma = gamma;
    }
}
