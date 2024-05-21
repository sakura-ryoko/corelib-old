package io.github.sakuraryoko.corelib.config.main;

import com.google.gson.annotations.SerializedName;
import net.minecraft.util.Util;

public class MainConfig
{
    @SerializedName("__comment")
    public String comment = "Sakura's Core Library API";

    @SerializedName("config_date")
    public String config_date = Util.getFormattedCurrentTime();
}
