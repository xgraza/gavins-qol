/*
 * Copyright (c) xgraza 2025
 */

package rated.x.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rated.x.util.FileUtil;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class ConfigManager
{
    private static final Logger LOGGER = LoggerFactory.getLogger("ConfigManager");
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    private final List<IConfig> configList = new LinkedList<>();

    public void init()
    {
        Runtime.getRuntime().addShutdownHook(new Thread(this::save, "RatedX-Config-Saver"));
    }

    public void save()
    {
        int saved = 0;
        for (final IConfig config : configList)
        {
            final File file = config.getLocation();
            if (!file.exists())
            {
                FileUtil.createFile(file);
            }
            try
            {
                final String content = GSON.toJson(config.toJSON());
                FileUtil.writeToFile(file, content);
                ++saved;
            } catch (final Exception e)
            {
                LOGGER.error("Failed to save", e);
            }
        }
        LOGGER.error("Saved {}/{}", saved, configList.size());
    }

    public void load()
    {
        int loaded = 0;
        for (final IConfig config : configList)
        {
            final File file = config.getLocation();
            if (!file.exists())
            {
                FileUtil.createFile(file);
                continue;
            }
            try
            {
                final String content = FileUtil.readFile(file);
                final JsonElement element = JsonParser.parseString(content);
                if (element != null)
                {
                    config.fromJSON(element);
                }
                ++loaded;
            } catch (final Exception e)
            {
                LOGGER.error("Failed to load", e);
            }
        }
        LOGGER.info("Loaded {}/{} configs", loaded, configList.size());
    }

    public void addConfig(final IConfig config)
    {
        configList.add(config);
    }
}
