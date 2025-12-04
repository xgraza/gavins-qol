/*
 * Copyright (c) xgraza 2025
 */

package rated.x.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.gui.GuiGraphics;
import rated.x.module.Module;

/**
 * @author xgraza
 * @since 1.0.0
 */
public class GUIModule extends Module implements IComponent
{
    protected double x, y, width, height;

    public void tick()
    {

    }

    public void render(final GuiGraphics graphics,
                       final int screenWidth,
                       final int screenHeight,
                       final boolean preview)
    {
        doBoundsCheck(screenWidth, screenHeight);
    }

    void doBoundsCheck(final int screenWidth, final int screenHeight)
    {
        if (x + width > screenWidth)
        {
            setX(screenWidth - width);
        }
        if (y + height > screenHeight)
        {
            setY(screenHeight - height);
        }
        if (0.0 > x)
        {
            setX(0.0);
        }
        if (0.0 > y)
        {
            setY(0.0);
        }
    }

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

    @Override
    public JsonElement toJSON()
    {
        final JsonObject object = (JsonObject) super.toJSON();
        final JsonObject positionObject = new JsonObject();
        positionObject.addProperty("posX", x);
        positionObject.addProperty("posY", y);
        object.add("position", positionObject);
        return object;
    }

    @Override
    public void fromJSON(final JsonElement element)
    {
        super.fromJSON(element);
        if (element.isJsonObject())
        {
            final JsonObject object = element.getAsJsonObject();
            final JsonObject positionObject = object.getAsJsonObject("position");
            setX(positionObject.get("posX").getAsDouble());
            setY(positionObject.get("posY").getAsDouble());
        }
    }
}
