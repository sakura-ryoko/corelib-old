package io.github.sakuraryoko.corelib.util;

import io.github.sakuraryoko.corelib.info.ModManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoreLog {
    private static Logger LOGGER;
    private static boolean enabled;
    private static final String CORE_ID = ModManager.getID();
    private static final boolean CORE_DEBUG = ModManager.isDebug();

    public static void initLogger() {
        LOGGER = LogManager.getLogger(CORE_ID);
        enabled = true;
        LOGGER.debug("[{}] Logger initialized.", CORE_ID);
    }

    public static void debug(String msg) {
        if (enabled) {
            if (CORE_DEBUG)
                LOGGER.info("[{}:DEBUG] " + msg, CORE_ID);
            else
                LOGGER.debug("[{}] " + msg, CORE_ID);
        }
    }

    public static void info(String msg) {
        if (enabled)
            LOGGER.info("[{}] " + msg, CORE_ID);
    }

    public static void warn(String msg) {
        if (enabled)
            LOGGER.warn("[{}] " + msg, CORE_ID);
    }

    public static void error(String msg) {
        if (enabled)
            LOGGER.error("[{}] " + msg, CORE_ID);
    }

    public static void fatal(String msg) {
        if (enabled)
            LOGGER.fatal("[{}] " + msg, CORE_ID);
    }
}
