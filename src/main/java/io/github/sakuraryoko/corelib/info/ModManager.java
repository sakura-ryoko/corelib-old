package io.github.sakuraryoko.corelib.info;

import io.github.sakuraryoko.corelib.util.CoreLog;
import net.minecraft.text.Text;

public class ModManager {
    final static String MOD_ID = "corelib";
    final static ModInfo MOD_INFO = new ModInfo(MOD_ID);
    final static boolean MOD_DEBUG = true;
    protected static String getBasic(int level) { return MOD_INFO.getModBasicInfo(level); }
    protected static Text getFormatted(int level) { return MOD_INFO.getModFormattedInfo(level); }
    public static String getID() { return MOD_ID; }
    public static boolean isDebug() { return MOD_DEBUG; }
    public static ModInfo getINFO() { return MOD_INFO; }
    public static void init() {
        CoreLog.info(getBasic(4));
    }
}
