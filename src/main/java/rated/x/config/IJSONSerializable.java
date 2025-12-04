/*
 * Copyright (c) xgraza 2025
 */

package rated.x.config;

import com.google.gson.JsonElement;

/**
 * @author xgraza
 * @since 1.0.0
 */
public interface IJSONSerializable
{
    JsonElement toJSON();

    void fromJSON(final JsonElement element);
}
