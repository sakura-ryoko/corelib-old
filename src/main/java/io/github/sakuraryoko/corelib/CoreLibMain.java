package io.github.sakuraryoko.corelib;

import io.github.sakuraryoko.corelib.commands.testCommand;
import io.github.sakuraryoko.corelib.config.ConfigManager;
import io.github.sakuraryoko.corelib.events.ServerEventManager;
import io.github.sakuraryoko.corelib.info.ModManager;
import io.github.sakuraryoko.corelib.network.PayloadTypes;
import io.github.sakuraryoko.corelib.network.test.DebugSuite;
import io.github.sakuraryoko.corelib.nodes.NodeManagerV2;
import io.github.sakuraryoko.corelib.util.CoreLog;

public class CoreLibMain {
    private static boolean CoreInit = false;
    public static void init() {
        if (CoreInit)
            return;
        CoreInit = true;
        CoreLog.initLogger();
        ModManager.init();
        if (!ConfigManager.loadConfig()) {
            CoreLog.fatal("Fatal error reading from config files.");
            return;
        }
        NodeManagerV2.registerNodes();
        PayloadTypes.registerDefaultTypes();
        DebugSuite.checkGlobalChannels();
        ServerEventManager.register();
        testCommand.register();
        CoreLog.debug("Successful initialization.");
        DebugSuite.checkGlobalChannels();
    }
}
