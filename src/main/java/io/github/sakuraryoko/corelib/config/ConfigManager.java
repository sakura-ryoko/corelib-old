package io.github.sakuraryoko.corelib.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import io.github.sakuraryoko.corelib.config.main.*;
import io.github.sakuraryoko.corelib.config.nodes.*;
import io.github.sakuraryoko.corelib.CoreInitHandler;
import io.github.sakuraryoko.corelib.util.CoreLog;

import java.nio.file.Files;

public class ConfigManager
{
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().setLenient()
            .create();
    private static MainConfig mainConfig;
    private static MoreColorConfig colorConfig;
    private static boolean mainLoad = false;
    private static boolean colorLoad = false;

    public static MainConfig getMainConfig() { return mainConfig; }
    public static MoreColorConfig getColorConfig() { return colorConfig; }

    public static boolean loadMainConfig()
    {
        mainLoad = false;
        mainConfig = null;
        try {
            var configDir = CoreInitHandler.getInstance().getModInstance().getConfigDir().resolve(CoreInitHandler.getInstance().getModId());
            MainConfig conf;
            if (!Files.isDirectory(configDir))
                Files.createDirectory(configDir);
            var configFile = configDir.resolve("main.json");
            if (Files.exists(configFile))
            {
                var getData = JsonParser.parseString(Files.readString(configFile));
                conf = GSON.fromJson(getData, MainConfig.class);
            }
            else
            {
                conf = new MainConfig();
                MainLoad.defaults(conf);
            }
            // Refresh Date / Mod Version #
            MainLoad.update(conf);
            Files.writeString(configFile, GSON.toJson(conf));
            mainConfig = conf;
            MainLoad.execute(conf);
            mainLoad = true;
        }
        catch (Exception e)
        {
            mainLoad = false;
            CoreLog.error("loadMainConfig() -> Error reading Main Config. "+e.getMessage());
        }
        return mainLoad;
    }

    public static boolean loadColorConfig()
    {
        colorLoad = false;
        colorConfig = null;
        try {
            var configDir = CoreInitHandler.getInstance().getModInstance().getConfigDir().resolve(CoreInitHandler.getInstance().getModId());
            MoreColorConfig conf;
            if (!Files.isDirectory(configDir))
                Files.createDirectory(configDir);
            var configFile = configDir.resolve("more-colors.json");
            if (Files.exists(configFile))
            {
                var getData = JsonParser.parseString(Files.readString(configFile));
                conf = GSON.fromJson(getData, MoreColorConfig.class);
            }
            else
            {
                conf = new MoreColorConfig();
                MoreColorLoad.defaults(conf);
            }
            MoreColorLoad.update(conf);
            Files.writeString(configFile, GSON.toJson(conf));
            colorConfig = conf;
            MoreColorLoad.execute(conf);
            colorLoad = true;
        }
        catch (Exception e)
        {
            colorLoad = false;
            CoreLog.error("loadColorConfig() -> Error reading More Colors Config. "+e.getMessage());
        }
        return colorLoad;
    }

    public static boolean isMainLoad() { return mainLoad; }

    public static boolean isColorLoad() { return colorLoad; }

    public static boolean loadConfig()
    {
        CoreLog.info("Loading all config files.");
        if (loadMainConfig())
            return loadColorConfig();
        return false;
    }
}
