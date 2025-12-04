/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.property;

/**
 * @param <T> the type this property is
 * @author xgraza
 * @since 1.0.0
 */
public class Property<T>
{
    private final String name;
    private T value;

    public Property(String name, T value)
    {
        this.name = name;
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }
}
