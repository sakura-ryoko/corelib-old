package io.github.sakuraryoko.corelib.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.sakuraryoko.corelib.config.main.MainConfig;
import io.github.sakuraryoko.corelib.config.nodes.MoreColorConfig;
import io.github.sakuraryoko.corelib.util.CoreLog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CoreConfig {
    private static final List<String> CoreJson = new ArrayList<>();
    public static void init() {
        final Gson Core = new Gson();
        // Loads default config
        MainConfig.init();
        CoreJson.add(Core.toJson(MainConfig.modName));
        MoreColorConfig.init();
        CoreJson.add(String.valueOf(Core.toJsonTree(MoreColorConfig.COLORS.toArray())));
        CoreLog.info("GSON (0):\n"+ CoreJson.get(0));
        CoreLog.info("GSON (1):\n"+ CoreJson.get(1));
    }
    public static void load() {

    }
}
