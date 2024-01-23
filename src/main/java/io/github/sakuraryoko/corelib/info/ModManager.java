package io.github.sakuraryoko.corelib.info;

import io.github.sakuraryoko.corelib.util.CoreLog;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;

import java.util.*;

public class ModManager {
    final static String MOD_ID = "corelib";
    final static boolean MOD_DEBUG = true;
    final static ModInfo MOD_INFO = new ModInfo(MOD_ID);
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
    public static FabricLoader getModInstance() { return MOD_INFO.getModInstance(); }
    public static String getID() { return MOD_ID; }
    public static boolean isDebug() { return MOD_DEBUG; }
    public static boolean isClient() { return MOD_INFO.isClient(); }
    public static boolean isServer() { return MOD_INFO.isServer(); }
    public static String getMcVersion() { return MOD_INFO.getMCVersion(); }
    public static String getModVersion() { return MOD_INFO.getModVersion(); }
    public static String getModDesc() { return MOD_INFO.getModDesc(); }
    public static String getModAuthors() { return MOD_INFO.getModAuthor$String(); }
    public static String getModSources() { return MOD_INFO.getModSources(); }
    public static String getModHomepage() { return MOD_INFO.getModHomepage(); }
    public static void init() {
        for (String s : getBasic(List.of("ver", "auth", "desc"))) CoreLog.info(s);
    }
}
