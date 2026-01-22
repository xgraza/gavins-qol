/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.property;

import java.util.function.Supplier;

/**
 * @param <T> the type this property is
 * @author xgraza
 * @since 1.0.0
 */
public class Property<T>
{
    private static final String DEFAULT_DESCRIPTION
            = "No description provided for this property";

    private final String name;
    private T value;

    private String format,
            description = DEFAULT_DESCRIPTION;
    private Supplier<Boolean> visibility = () -> true;

    public Property(String name)
    {
        this.name = name;
        format = name;
    }

    public String getName()
    {
        return name;
    }

    public Property<T> setValue(T value)
    {
        this.value = value;
        return this;
    }

    public T getValue()
    {
        return value;
    }

    public Property<T> setDescription(final String description)
    {
        this.description = description;
        return this;
    }

    public String getDescription()
    {
        return description;
    }

    public Property<T> setVisibility(final Supplier<Boolean> visibility)
    {
        this.visibility = visibility;
        return this;
    }

    public boolean isVisible()
    {
        return visibility == null || visibility.get();
    }

    public Property<T> setFormat(final String format)
    {
        if (format == null)
        {
            this.format = name;
            return this;
        }
        this.format = format
                .replaceAll("\\{name}", name);
        return this;
    }

    public String getFormat()
    {
        return format;
    }
}
