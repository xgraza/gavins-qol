/*
 * Copyright (c) xgraza 2025
 */

package rated.x.util;

import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * @author xgraza
 * @since 1.0.0
 */
public final class FileUtil
{
    public static final File ROOT;

    static
    {
        ROOT = new File(Minecraft.getInstance().gameDirectory, "RatedXMod");
        if (!ROOT.exists())
        {
            if (!ROOT.mkdir())
            {
                throw new RuntimeException("Could not create " + ROOT);
            }
        }
    }

    public static String readFile(final File file)
    {
        final StringBuilder builder = new StringBuilder();
        try (final InputStream stream = Files.newInputStream(file.toPath()))
        {
            int b;
            while ((b = stream.read()) != -1)
            {
                builder.append((char) b);
            }
        } catch (final IOException e)
        {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

    public static void writeToFile(final File file, final String content)
    {
        try (final OutputStream stream = Files.newOutputStream(file.toPath()))
        {
            final byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            stream.write(bytes, 0, bytes.length);
        } catch (final IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void createFile(final File file)
    {
        try
        {
            file.createNewFile();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean createFile(final String... path)
    {
        final File file = ROOT.toPath().resolve(Arrays.toString(path)).toFile();
        if (!file.getParentFile().mkdirs())
        {
            return false;
        }
        try
        {
            file.createNewFile();
            return true;
        } catch (final IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean createNewDirectory(final File file, final String subfolderName)
    {
        return new File(file, subfolderName).mkdir();
    }

    public static File createNewRootDirectory(final String subfolderName)
    {
        final File file = new File(ROOT, subfolderName);
        file.mkdir();
        return file;
    }

    public static File resolveFromRoot(final String... paths)
    {
        return ROOT.toPath().resolve(Arrays.toString(paths)).toFile();
    }

    private FileUtil()
    {

    }
}
