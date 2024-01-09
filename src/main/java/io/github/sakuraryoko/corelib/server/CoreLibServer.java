package io.github.sakuraryoko.corelib.server;

import io.github.sakuraryoko.corelib.CoreLibMain;
import net.fabricmc.api.DedicatedServerModInitializer;

public class CoreLibServer implements DedicatedServerModInitializer {
    private static void start() { CoreLibMain.init(); }

    @Override
    public void onInitializeServer() { start(); }
}
