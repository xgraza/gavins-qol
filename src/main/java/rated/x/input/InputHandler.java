/*
 * Copyright (c) xgraza 2025
 */

package rated.x.input;

/**
 * @author xgraza
 * @since 1.0.0
 */
public interface InputHandler
{
    void onEnable();

    void onDisable();

    void setEnabled(boolean state);

    boolean isEnabled();

    Input getInput();
}
