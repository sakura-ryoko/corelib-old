package io.github.sakuraryoko.corelib.api.config;

import com.google.gson.annotations.SerializedName;
import io.github.sakuraryoko.corelib.impl.config.IConfigData;

public class MainConfigData implements IConfigData
{
    @SerializedName("__comment")
    public String comment = "Sakura's Core Library API";

    @SerializedName("config_date")
    public String config_date;
}
