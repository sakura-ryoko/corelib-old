package io.github.sakuraryoko.corelib.config.nodes;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class MoreColorConfig
{
    @SerializedName("__comment1")
    public String comment1 = "Use colors {} entry list with \"name\": \"#FFFFFF\" to add color nodes.";

    @SerializedName("__comment2")
    public String comment2 = "Use aliases {} entry list to map an existing node to one or more nodes.";

    @SerializedName("__comment3")
    public String comment3 = "- List the existing colors {} \"name\" and then create a new entry list of it's alias tag names.";

    @SerializedName("colors")
    public Map<String, String> COLORS = new HashMap<>();

    @SerializedName("aliases")
    public Map<String, List<String>> ALIASES = new HashMap<>();
}
