/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module;

/**
 * @author xgraza
 * @since 1.0.0
 */
public enum ModuleCategory
{
    HUD("HUD"),
    PLAYER("Player"),
    // TWEAKS("Tweaks"),
    VISUAL("Visual"),

    DEFAULT("Default");

    private final String name;

    ModuleCategory(final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
