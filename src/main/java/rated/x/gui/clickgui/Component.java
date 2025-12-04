/*
 * Copyright (c) xgraza 2025
 */

package rated.x.gui.clickgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

/**
 * @author xgraza
 * @since 1.0.0
 */
public abstract class Component
{
    protected static final Minecraft MC = Minecraft.getInstance();

    protected double x, y, width, height;

    protected final Font font;

    public Component(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        font = MC.font;
    }

    public abstract void render(final GuiGraphics graphics, final int mouseX, final int mouseY);

    public abstract void mouseClick(final double x, final double y, final int button);

    public boolean isMouseIn(final double x, final double y)
    {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getWidth()
    {
        return width;
    }

    public void setWidth(double width)
    {
        this.width = width;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }
}
