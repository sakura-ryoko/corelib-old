package io.github.sakuraryoko.corelib.config.main;

import io.github.sakuraryoko.corelib.info.ModManager;

public class MainConfig {
    public static String modName;
    public static String getModName() { return modName; }
    public static void init() {
        modName = ModManager.getID();
    }
}
