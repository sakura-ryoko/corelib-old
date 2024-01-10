package io.github.sakuraryoko.corelib.config;

import io.github.sakuraryoko.corelib.config.main.MainConfig;
import io.github.sakuraryoko.corelib.config.nodes.MoreColorConfig;

public class CoreConfig {
    public MainConfig mainCONFIG = new MainConfig();
    public MoreColorConfig colorCONFIG = new MoreColorConfig();

    public CoreConfig(MainConfig main, MoreColorConfig colors) {
        this.mainCONFIG = main;
        this.colorCONFIG = colors;
    }
    public CoreConfig(MainConfig cfg) {
        this.mainCONFIG = cfg;
    }
    public CoreConfig(MoreColorConfig cfg) {
        this.colorCONFIG = cfg;
    }
}
