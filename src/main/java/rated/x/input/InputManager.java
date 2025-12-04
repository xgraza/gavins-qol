/*
 * Copyright (c) xgraza 2025
 */

package rated.x.input;

import rated.x.RatedX;
import rated.x.listener.bus.Listener;
import rated.x.listener.event.EventKeyPress;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class InputManager
{
    private final List<Input> inputList = new ArrayList<>();

    public void init()
    {
        RatedX.EVENT_BUS.subscribe(this);
    }

    @Listener
    public void onKeyPress(final EventKeyPress event)
    {
        for (final Input input : inputList)
        {
            if (!input.isKeyboard() || input.getKey() != event.keyCode())
            {
                continue;
            }
            if (input.getModifiers() != -1 && event.modifiers() != input.getModifiers())
            {
                continue;
            }
            final InputHandler handler = input.getInputHandler();
            handler.setEnabled(!handler.isEnabled());
        }
    }

    public void registerInput(final Input input)
    {
        inputList.add(input);
    }
}
