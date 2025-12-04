/*
 * Copyright (c) xgraza 2025
 */

package rated.x.module.clickgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import rated.x.gui.IComponent;

/**
 * @author xgraza
 * @since 1.0.0
 */
public abstract class Component implements IComponent
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

    @Override
    public double getX()
    {
        return x;
    }

    @Override
    public void setX(double x)
    {
        this.x = x;
    }

    @Override
    public double getY()
    {
        return y;
    }

    @Override
    public void setY(double y)
    {
        this.y = y;
    }

    @Override
    public double getWidth()
    {
        return width;
    }

    @Override
    public void setWidth(double width)
    {
        this.width = width;
    }

    @Override
    public double getHeight()
    {
        return height;
    }

    @Override
    public void setHeight(double height)
    {
        this.height = height;
    }
}
