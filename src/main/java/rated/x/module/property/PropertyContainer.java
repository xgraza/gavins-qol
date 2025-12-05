/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.property;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author xgraza
 * @since 1.0.0
 */
public class PropertyContainer
{
    private final Map<String, Property<?>> propertyNameMap = new LinkedHashMap<>();
    private final List<Property<?>> propertyList = new LinkedList<>();

    public void loadProperties() throws IllegalAccessException
    {
        final Field[] fields = getClass().getDeclaredFields();
        for (final Field field : fields)
        {
            if (!Property.class.isAssignableFrom(field.getType()) || !field.trySetAccessible())
            {
                continue;
            }
            final Property<?> property = (Property<?>) field.get(this);
            propertyNameMap.put(property.getName(), property);
            propertyList.add(property);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> Property<T> getProperty(final String name)
    {
        return (Property<T>) propertyNameMap.get(name);
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(final String name)
    {
        return (T) Objects.requireNonNull(propertyNameMap.get(name)).getValue();
    }

    public List<Property<?>> getProperties()
    {
        return propertyList;
    }
}
