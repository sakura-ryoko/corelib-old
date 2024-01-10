package io.github.sakuraryoko.corelib.config.nodes;

import io.github.sakuraryoko.corelib.nodes.MoreColorNode;
import io.github.sakuraryoko.corelib.nodes.NodeManagerV2;
import io.github.sakuraryoko.corelib.util.CoreLog;

import java.util.List;
import java.util.Map;

public class MoreColorLoad {
    public static void defaults(MoreColorConfig cfg) {
        // Check for default values
        cfg.COLORS.putIfAbsent("brown", "#632C04");
        cfg.COLORS.putIfAbsent("cyan","#2D7C9D");
        cfg.COLORS.putIfAbsent("dark_brown","#421F05");
        cfg.COLORS.putIfAbsent("dark_pink","#DE8BB4");
        cfg.COLORS.putIfAbsent("light_blue","#82ACE7");
        cfg.COLORS.putIfAbsent("light_brown","#7A4621");
        cfg.COLORS.putIfAbsent("light_gray","#BABAC1");
        cfg.ALIASES.putIfAbsent("light_gray", List.of("light_grey"));
        cfg.COLORS.putIfAbsent("light_pink","#F7B4D6");
        cfg.COLORS.putIfAbsent("lime","#76C610");
        cfg.COLORS.putIfAbsent("magenta","#CB69C5");
        cfg.COLORS.putIfAbsent("orange","#E69E34");
        cfg.COLORS.putIfAbsent("pink","#EDA7CB");
        cfg.COLORS.putIfAbsent("purple","#A453CE");
        cfg.COLORS.putIfAbsent("salmon", "#FF91A4");
        cfg.ALIASES.putIfAbsent("salmon", List.of("pink_salmon"));
        CoreLog.debug("MoreColorLoad.defaults() initialized.");
    }
    public static void update(MoreColorConfig cfg) {
        // Refresh existing data values into save file
        CoreLog.debug("MoreColorLoad.refresh() has been called.");
        for (MoreColorNode iColor : MoreColorNode.COLORS) {
            cfg.COLORS.putIfAbsent(iColor.getName(), iColor.getHexCode());
            if (iColor.getAliases() != null) {
                cfg.ALIASES.putIfAbsent(iColor.getName(), iColor.getAliases());
            }
        }
    }
    public static void execute(MoreColorConfig cfg) {
        // Do this when the Colors are reloaded.
        boolean added = false;
        CoreLog.debug("MoreColorLoad.execute() has been called.");
        for (Map.Entry<String,String> stringEntry : cfg.COLORS.entrySet()) {
            String colorName = stringEntry.getKey();
            MoreColorNode iColor = MoreColorNode.getColorByName(colorName);
            if (iColor == null) {
                List<String> alias = cfg.ALIASES.get(colorName);
                if (alias == null)
                    MoreColorNode.addColor(colorName, stringEntry.getValue());
                else {
                    MoreColorNode.addColor(colorName, stringEntry.getValue(), alias);
                    CoreLog.debug("Adding aliases to color " + colorName + " named: " + alias);
                }
                added = true;
            } else
                CoreLog.debug("Can't add color: "+colorName+" because it already exists.");
        }
        if (added) {
            CoreLog.debug("Attempting to register any new color nodes.");
            NodeManagerV2.registerColors();
        }
    }
}
