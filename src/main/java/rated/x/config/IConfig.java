/*
 * Copyright (c) xgraza 2025
 */

package rated.x.config;

import java.io.File;

/**
 * @author xgraza
 * @since 1.0.0
 */
public interface IConfig extends IJSONSerializable
{
    File getLocation();
}
