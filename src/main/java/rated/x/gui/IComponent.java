/*
 * Copyright (c) xgraza 2025
 */

package rated.x.gui;

/**
 * @author xgraza
 * @since 1.0.0
 */
public interface IComponent
{
    void setX(double x);

    double getX();

    void setY(double y);

    double getY();

    void setWidth(double width);

    double getWidth();

    void setHeight(double height);

    double getHeight();

    default boolean isMouseIn(final double x, final double y)
    {
        return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
    }
}
