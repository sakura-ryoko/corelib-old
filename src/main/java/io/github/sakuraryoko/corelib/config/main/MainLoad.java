package io.github.sakuraryoko.corelib.config.main;

import io.github.sakuraryoko.corelib.info.ModManager;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.minecraft.util.Util;

public class MainLoad {
    public static void defaults(MainConfig cfg) {
        // Initialize default settings
        CoreLog.debug("MainLoad.defaults() has been called.");
    }
    public static void update(MainConfig cfg) {
        // Refresh Date / Mod Version.
        cfg.mcVersion = ModManager.getMcVersion();
        cfg.modVersion = ModManager.getModVersion();
        cfg.modAuthors = ModManager.getModAuthors();
        cfg.modHomepage = ModManager.getModHomepage();
        cfg.modSources = ModManager.getModSources();
        cfg.comment = ModManager.getModDesc();
        cfg.config_date = Util.getFormattedCurrentTime();
        CoreLog.debug("MainLoad.refresh() has been called.");
    }
    public static void execute(MainConfig cfg) {
        // Do this when the Main Config gets reloaded.
        CoreLog.debug("MainLoad.execute() has been called.");
    }
}
