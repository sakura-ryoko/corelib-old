package io.github.sakuraryoko.corelib.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.sakuraryoko.corelib.config.main.MainConfig;
import io.github.sakuraryoko.corelib.config.nodes.MoreColorConfig;
import io.github.sakuraryoko.corelib.util.CoreLog;

import java.util.List;

public class CoreConfig {
    private static final Gson CoreConfig = new GsonBuilder().create();
    public static void init() {
        // Loads default config
        MainConfig.init();
        CoreConfig.toJson(MainConfig.modName);
        MoreColorConfig.init();
        CoreConfig.toJson(MoreColorConfig.COLORS, List.class);
        CoreLog.info("GSON:\n"+ CoreConfig.toString());
    }
    public static void load() {

    }
}
