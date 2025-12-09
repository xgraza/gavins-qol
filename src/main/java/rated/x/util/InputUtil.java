package rated.x.util;

import static org.lwjgl.glfw.GLFW.*;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class InputUtil
{
    public static String getKeyName(final int keyCode)
    {
        return switch (keyCode)
        {
            case GLFW_KEY_SPACE -> "Space";
            case GLFW_KEY_APOSTROPHE -> "'";
            case GLFW_KEY_COMMA -> ",";
            case GLFW_KEY_MINUS -> "-";
            case GLFW_KEY_PERIOD -> ".";
            case GLFW_KEY_SLASH -> "/";
            case GLFW_KEY_SEMICOLON -> ";";
            case GLFW_KEY_EQUAL -> "=";
            case GLFW_KEY_LEFT_BRACKET -> "[";
            case GLFW_KEY_BACKSLASH -> "\\";
            case GLFW_KEY_RIGHT_BRACKET -> "]";
            case GLFW_KEY_GRAVE_ACCENT -> "`";
            case GLFW_KEY_ESCAPE -> "Escape";
            case GLFW_KEY_ENTER -> "Enter";
            case GLFW_KEY_TAB -> "Tab";
            case GLFW_KEY_BACKSPACE -> "Backspace";
            case GLFW_KEY_INSERT -> "Insert";
            case GLFW_KEY_DELETE -> "Delete";
            case GLFW_KEY_RIGHT -> "Right";
            case GLFW_KEY_LEFT -> "Left";
            case GLFW_KEY_DOWN -> "Down";
            case GLFW_KEY_UP -> "Up";
            case GLFW_KEY_PAGE_UP -> "Pg Up";
            case GLFW_KEY_PAGE_DOWN -> "Pg Down";
            case GLFW_KEY_HOME -> "Home";
            case GLFW_KEY_END -> "End";
            case GLFW_KEY_CAPS_LOCK -> "Caps Lock";
            case GLFW_KEY_SCROLL_LOCK -> "Scroll Lock";
            case GLFW_KEY_NUM_LOCK -> "Num Lock";
            case GLFW_KEY_PRINT_SCREEN -> "Print Screen";
            case GLFW_KEY_PAUSE -> "Pause";
            case GLFW_KEY_KP_DECIMAL -> "Keypad .";
            case GLFW_KEY_KP_DIVIDE -> "Keypad /";
            case GLFW_KEY_KP_MULTIPLY -> "Keypad *";
            case GLFW_KEY_KP_SUBTRACT -> "Keypad -";
            case GLFW_KEY_KP_ADD -> "Keypad +";
            case GLFW_KEY_KP_ENTER -> "Keypad Enter";
            case GLFW_KEY_KP_EQUAL -> "Keypad =";
            case GLFW_KEY_LEFT_SHIFT -> "Left Shift";
            case GLFW_KEY_LEFT_CONTROL -> "Left Control";
            case GLFW_KEY_LEFT_ALT -> "Left Alt";
            case GLFW_KEY_LEFT_SUPER -> "Left Super";
            case GLFW_KEY_RIGHT_SHIFT -> "Right Shift";
            case GLFW_KEY_RIGHT_CONTROL -> "Right Control";
            case GLFW_KEY_RIGHT_ALT -> "Right Alt";
            case GLFW_KEY_RIGHT_SUPER -> "Right Super";
            case GLFW_KEY_MENU -> "Menu";
            case GLFW_KEY_UNKNOWN -> "None";
            default ->
            {
                if (keyCode >= GLFW_KEY_F1 && keyCode <= GLFW_KEY_F25)
                {
                    yield "F" + ((keyCode - GLFW_KEY_F1) + 1);
                }

                String key = glfwGetKeyName(keyCode, glfwGetKeyScancode(keyCode));
                if (key == null)
                {
                    key = "Unknown";
                } else
                {
                    key = key.toUpperCase();
                }
                yield key;
            }
        };
    }

    private InputUtil()
    {

    }
}
