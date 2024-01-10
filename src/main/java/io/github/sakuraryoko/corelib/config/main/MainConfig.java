package io.github.sakuraryoko.corelib.config.main;

import com.google.gson.annotations.SerializedName;
import io.github.sakuraryoko.corelib.info.ModManager;
import net.minecraft.util.Util;

public class MainConfig {
    @SerializedName("mod_name")
    public String modName = ModManager.getID();
    @SerializedName("mc_version")
    public String mcVersion = ModManager.getMcVersion();
    @SerializedName("mod_version")
    public String modVersion = ModManager.getModVersion();
    @SerializedName("mod_authors")
    public String modAuthors = ModManager.getModAuthors();
    @SerializedName("mod_homepage")
    public String modHomepage = ModManager.getModHomepage();
    @SerializedName("mod_sources")
    public String modSources = ModManager.getModSources();
    @SerializedName("__comment")
    public String comment = "Sakura's Core Library API";
    @SerializedName("config_date")
    public String config_date = Util.getFormattedCurrentTime();
}
