package rated.x.input;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import rated.x.config.IConfig;

import java.io.File;

/**
 * @author xgraza
 * @since 1.0.0
 */
final class InputConfig implements IConfig
{
    private final InputManager inputManager;

    public InputConfig(final InputManager inputManager)
    {
        this.inputManager = inputManager;
    }

    @Override
    public File getLocation()
    {
        return inputManager.getKeybindsFileLocation();
    }

    @Override
    public JsonElement toJSON()
    {
        final JsonObject object = new JsonObject();
        for (final Input input : inputManager.getInputs())
        {
            final JsonObject inputObject = new JsonObject();
            inputObject.addProperty("key", input.getKey());
            inputObject.addProperty("keyboard", input.isKeyboard());
            object.add(input.getID(), inputObject);
        }
        return object;
    }

    @Override
    public void fromJSON(final JsonElement element)
    {
        if (!element.isJsonObject())
        {
            return;
        }
        final JsonObject object = element.getAsJsonObject();
        for (final Input input : inputManager.getInputs())
        {
            if (!object.has(input.getID()))
            {
                continue;
            }
            final JsonElement value = object.get(input.getID());
            if (!value.isJsonObject())
            {
                continue;
            }
            final JsonObject inputObject = value.getAsJsonObject();
            if (inputObject.has("key"))
            {
                input.setKey(inputObject.get("key").getAsInt());
            }
            if (inputObject.has("keyboard"))
            {
                input.setKeyboard(inputObject.get("keyboard").getAsBoolean());
            }
        }
    }
}
