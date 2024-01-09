package io.github.sakuraryoko.corelib;

import io.github.sakuraryoko.corelib.info.ModManager;
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
        NodeManagerV2.initNodes();
        NodeManagerV2.registerNodes();
    }
}
