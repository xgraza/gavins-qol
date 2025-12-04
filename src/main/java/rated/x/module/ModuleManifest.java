/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author xgraza
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleManifest
{
    String name();

    ModuleCategory category() default ModuleCategory.DEFAULT;

    String description() default Module.NO_DESCRIPTION;
}
