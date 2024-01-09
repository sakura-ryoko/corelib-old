package io.github.sakuraryoko.corelib.nodes;

import com.mojang.serialization.DataResult;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.minecraft.text.TextColor;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MoreColorNode {
    private final String name;
    private final String hexCode;
    private List<String> aliases = new ArrayList<>();
    private TextColor color;

    public MoreColorNode(String name, String hexCode) {
        DataResult<TextColor> dr;
        dr = TextColor.parse(hexCode);
        if (dr.error().isEmpty()) {
            this.name = name;
            this.hexCode = hexCode;
            this.color = dr.get().left().orElse(null);
        } else {
            CoreLog.warn("MoreColorNode("+ name +") is Invalid, error: "+dr.error().toString());
            this.name = "";
            this.hexCode = "";
        }
    }
    public MoreColorNode(String name, String hexCode, @Nullable List<String> aliases) {
        DataResult<TextColor> dr;
        dr = TextColor.parse(hexCode);
        if (dr.error().isEmpty()) {
            this.name = name;
            this.hexCode = hexCode;
            this.color = dr.get().left().orElse(null);
            this.aliases = aliases;
        } else {
            CoreLog.warn("MoreColorNode("+ name +") is Invalid, error: "+dr.error().toString());
            this.name = "";
            this.hexCode = "";
        }
    }

    public String getName() { return this.name; }
    public String getHexCode() { return this.hexCode; }
    @Nullable
    public List<String> getAliases() { return this.aliases; }
    public TextColor getColor() { return this.color; }
}
