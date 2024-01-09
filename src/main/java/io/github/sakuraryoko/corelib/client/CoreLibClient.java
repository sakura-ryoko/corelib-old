package io.github.sakuraryoko.corelib.client;

import io.github.sakuraryoko.corelib.CoreLibMain;

import net.fabricmc.api.ClientModInitializer;

public class CoreLibClient implements ClientModInitializer {
    private static void start() {
        CoreLibMain.init();
    }

    @Override
    public void onInitializeClient() { start(); }
}
