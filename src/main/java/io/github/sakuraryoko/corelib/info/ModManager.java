package io.github.sakuraryoko.corelib.info;

import io.github.sakuraryoko.corelib.util.CoreLog;
import net.minecraft.text.Text;

import java.util.*;

public class ModManager {
    final static String MOD_ID = "corelib";
    final static boolean MOD_DEBUG = true;
    final static ModInfo MOD_INFO = new ModInfo(MOD_ID);
    final static String MC_VERSION = MOD_INFO.getMCVersion();
    final static String MOD_VERSION = MOD_INFO.getModVersion();
    protected static List<String> getBasic(List<String> elements) {
        Map<String, String> infoBasic = MOD_INFO.getModBasicInfo();
        List<String> result = new ArrayList<>();
        for (String element : elements)
            result.add(infoBasic.get(element));
        return result;
    }
    protected static List<Text> getFormatted(List<String> elements) {
        Map<String, Text> infoFmt = MOD_INFO.getModFormattedInfo();
        List<Text> result = new ArrayList<>();
        for (String element : elements)
            result.add(infoFmt.get(element));
        return result;
    }
    public static String getID() { return MOD_ID; }
    public static boolean isDebug() { return MOD_DEBUG; }
    public static String getMcVersion() { return MC_VERSION; }
    public static String getModVersion() { return MOD_VERSION; }
    public static void init() {
        for (String s : getBasic(List.of("ver", "auth", "desc"))) CoreLog.info(s);
    }
}
