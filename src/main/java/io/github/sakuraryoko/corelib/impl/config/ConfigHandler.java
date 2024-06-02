package io.github.sakuraryoko.corelib.impl.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.ApiStatus;
import io.github.sakuraryoko.corelib.api.init.CoreInitHandler;
import io.github.sakuraryoko.corelib.util.CoreLog;

public class ConfigHandler implements IConfigManager
{
    private static final ConfigHandler INSTANCE = new ConfigHandler();
    private final Map<String, ConfigHandlerObject> handlers = new HashMap<>();
    private final Map<String, Boolean> isLoaded = new HashMap<>();
    public static IConfigManager getInstance() { return INSTANCE; }
    private final Path CONFIG_DIR = CoreInitHandler.getInstance().getModInstance().getConfigDir();
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().setLenient()
            .create();

    @Override
    public void registerModConfigHandler(ConfigHandlerObject object)
    {
        if (!this.handlers.containsKey(object.getModid()))
        {
            this.handlers.put(object.getModid(), object);
            this.isLoaded.put(object.getModid(), false);
        }
    }

    @ApiStatus.Internal
    public void loadAllConfigs()
    {
        CoreLog.debug("loadAllConfigs()");

        if (!this.handlers.isEmpty())
        {
            this.handlers.forEach((modid, object) ->
            {
                if (!object.getHandler().isLoaded())
                {
                    object.getHandler().onPreLoadConfig();
                    this.loadEach(object);
                    object.getHandler().onPostLoadConfig();
                }
            });
        }
    }

    @ApiStatus.Internal
    public void saveAllConfigs()
    {
        CoreLog.debug("saveAllConfigs()");

        if (!this.handlers.isEmpty())
        {
            this.handlers.forEach((modid, object) ->
            {
                if (object.getHandler().isLoaded())
                {
                    object.getHandler().onPreSaveConfig();
                    this.saveEach(object);
                    object.getHandler().onPostSaveConfig();
                }
                else
                {
                    object.getHandler().onPreLoadConfig();
                    this.loadEach(object);
                    object.getHandler().onPostLoadConfig();
                }
            });
        }
    }

    @ApiStatus.Internal
    public void defaultsAll()
    {
        CoreLog.debug("defaultsAll()");

        if (!this.handlers.isEmpty())
        {
            this.handlers.forEach((modid, object) ->
            {
                IConfigData conf = object.getHandler().getConfig();
                object.getHandler().defaults(conf);
            });
        }
    }

    @ApiStatus.Internal
    public void updateAll()
    {
        CoreLog.debug("updateAll()");

        if (!this.handlers.isEmpty())
        {
            this.handlers.forEach((modid, object) ->
            {
                IConfigData conf = object.getHandler().getConfig();
                object.getHandler().update(conf);
            });
        }
    }

    @ApiStatus.Internal
    public void executeAll()
    {
        CoreLog.debug("executeAll()");

        if (!this.handlers.isEmpty())
        {
            this.handlers.forEach((modid, object) ->
            {
                IConfigData conf = object.getHandler().getConfig();
                object.getHandler().execute(conf);
            });
        }
    }

    @ApiStatus.Internal
    @SuppressWarnings("unchecked")
    private <T extends IConfigData> void loadEach(ConfigHandlerObject object)
    {
        TypeToken<T> typeToken = new TypeToken<>(object.getDataType().getClass()) {};
        Class<T> typeObject = (Class<T>) typeToken.getRawType();
        IConfigData conf = object.getHandler().newConfig();

        this.isLoaded.put(object.getModid(), false);
        CoreLog.debug("loadEach() for modid [{}]", object.getModid());

        try
        {
            Path configDir;

            if (object.getRoorDir())
            {
                configDir = CONFIG_DIR;
            }
            else
            {
                configDir = CONFIG_DIR.resolve(object.getModid());
            }

            if (!Files.isDirectory(configDir))
            {
                Files.createDirectory(configDir);
            }

            var configFile = configDir.resolve(object.getConfigFile() + ".json");

            if (Files.exists(configFile))
            {
                var getData = JsonParser.parseString(Files.readString(configFile));
                conf = GSON.fromJson(getData, conf.getClass());
            }
            else
            {
                object.getHandler().defaults(conf);
            }

            object.getHandler().update(conf);
            Files.writeString(configFile, GSON.toJson(conf));
            object.getHandler().execute(conf);

            this.isLoaded.put(object.getModid(), true);
        }
        catch (Exception e)
        {
            this.isLoaded.put(object.getModid(), false);
            CoreLog.error("Error reading config file for mod [{}] // {}", object.getModid(), e.getMessage());
        }
    }

    @ApiStatus.Internal
    @SuppressWarnings("unchecked")
    private <T extends IConfigData> void saveEach(ConfigHandlerObject object)
    {
        TypeToken<T> typeToken = new TypeToken<>(object.getDataType().getClass()) {};
        Class<T> typeObject = (Class<T>) typeToken.getRawType();
        IConfigData conf;

        this.isLoaded.put(object.getModid(), false);
        CoreLog.debug("saveEach() for modid [{}]", object.getModid());

        try
        {
            Path configDir;

            if (object.getRoorDir())
            {
                configDir = CONFIG_DIR;
            }
            else
            {
                configDir = CONFIG_DIR.resolve(object.getModid());
            }

            if (!Files.isDirectory(configDir))
            {
                Files.createDirectory(configDir);
            }

            var configFile = configDir.resolve(object.getConfigFile() + ".json");

            if (Files.exists(configFile))
            {
                Files.delete(configFile);
            }

            conf = object.getHandler().getConfig();

            if (conf != null)
            {
                Files.writeString(configFile, GSON.toJson(conf));
                this.isLoaded.put(object.getModid(), true);
            }
            else
            {
                this.isLoaded.put(object.getModid(), false);
                CoreLog.error("Error saving config file for mod [{}] // config object is empty", object.getModid());
            }
        }
        catch (Exception e)
        {
            this.isLoaded.put(object.getModid(), false);
            CoreLog.error("Error saving config file for mod [{}] // {}", object.getModid(), e.getMessage());
        }
    }
}
