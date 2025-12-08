/*
 * Copyright (c) xgraza 2025
 */

package rated.x.input;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class Input
{
    private final InputHandler inputHandler;
    private int key, modifiers;
    private boolean keyboard = true, persistent = true;

    public Input(final InputHandler handler, int key, int modifiers, boolean keyboard, boolean persistent)
    {
        this.inputHandler = handler;
        this.key = key;
        this.modifiers = modifiers;
        this.keyboard = keyboard;
        this.persistent = persistent;
    }

    public InputHandler getInputHandler()
    {
        return inputHandler;
    }

    public int getKey()
    {
        return key;
    }

    public void setKey(int key)
    {
        this.key = key;
    }

    public int getModifiers()
    {
        return modifiers;
    }

    public void setModifiers(int modifiers)
    {
        this.modifiers = modifiers;
    }

    public boolean isKeyboard()
    {
        return keyboard;
    }

    public void setKeyboard(boolean keyboard)
    {
        this.keyboard = keyboard;
    }

    public boolean isPersistent()
    {
        return persistent;
    }

    public void setPersistent(boolean persistent)
    {
        this.persistent = persistent;
    }
}
