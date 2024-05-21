package io.github.sakuraryoko.corelib.config.main;

import net.minecraft.util.Util;
import io.github.sakuraryoko.corelib.util.CoreLog;

public class MainLoad
{
    public static void defaults(MainConfig cfg)
    {
        // Initialize default settings
        CoreLog.debug("MainLoad.defaults() has been called.");
    }

    public static void update(MainConfig cfg)
    {
        // Refresh Date / Mod Version.
        cfg.config_date = Util.getFormattedCurrentTime();
        CoreLog.debug("MainLoad.refresh() has been called.");
    }

    public static void execute(MainConfig cfg)
    {
        // Do this when the Main Config gets reloaded.
        CoreLog.debug("MainLoad.execute() has been called.");
    }
}
