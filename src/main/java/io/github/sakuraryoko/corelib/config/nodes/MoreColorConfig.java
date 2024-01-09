package io.github.sakuraryoko.corelib.config.nodes;

import io.github.sakuraryoko.corelib.nodes.MoreColorNode;
import io.github.sakuraryoko.corelib.util.CoreLog;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreColorConfig {
    public static List<MoreColorNode> COLORS = new ArrayList<>();
    private static final Map<String, MoreColorNode> colorMap = new HashMap<>();
    public static boolean addColor(String name, String hexCode) {
        MoreColorNode color = new MoreColorNode(name, hexCode);
        if (color.getName().isEmpty()) {
            CoreLog.warn("MoreColorConfig.addColor("+name+") has failed!");
            return false;
        }
        if (colorMap.get(name) != null) {
            CoreLog.warn("MoreColorConfig.addColor("+name+") already exists!");
            return false;
        }
        colorMap.put(name, color);
        CoreLog.debug("MoreColorConfig.addColor("+name+") successfully added.");
        return true;
    }
    public static boolean addColor(String name, String hexCode, @Nullable List<String> alias) {
        MoreColorNode color = new MoreColorNode(name, hexCode, alias);
        if (color.getName().isEmpty()) {
            CoreLog.warn("MoreColorConfig.addColor(" + name + ") has failed!");
            return false;
        }
        if (colorMap.get(name) != null) {
            CoreLog.warn("MoreColorConfig.addColor("+name+") already exists!");
            return false;
        }
        colorMap.put(name, color);
        CoreLog.debug("MoreColorConfig.addColor("+name+") successfully added.");
        return true;
    }
    public MoreColorNode getColorByName(String name) { return colorMap.get(name); }
    public static void init() {
        // Adds default colors;
        addColor("brown", "#632C04");
        addColor("cyan","#2D7C9D");
        addColor("dark_brown","#421F05");
        addColor("dark_pink","#DE8BB4");
        addColor("light_blue","#82ACE7");
        addColor("light_brown","#7A4621");
        addColor("light_gray","#BABAC1", List.of("light_grey"));
        addColor("light_pink","#F7B4D6");
        addColor("lime","#76C610");
        addColor("magenta","#CB69C5");
        addColor("orange","#E69E34");
        addColor("pink","#EDA7CB");
        addColor("purple","#A453CE");
        addColor("salmon", "#FF91A4", List.of("pink_salmon"));
        CoreLog.debug("MoreColorConfig.initColors() done.");
    }
}
