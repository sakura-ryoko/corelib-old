package io.github.sakuraryoko.corelib.api.config;

import java.util.List;
import java.util.Map;
import io.github.sakuraryoko.corelib.api.nodes.MoreColorNode;
import io.github.sakuraryoko.corelib.api.nodes.NodeManagerV2;
import io.github.sakuraryoko.corelib.impl.config.IConfigData;
import io.github.sakuraryoko.corelib.impl.config.IConfigDispatch;
import io.github.sakuraryoko.corelib.util.CoreLog;

public class MoreColorConfigHandler implements IConfigDispatch
{
    private static final MoreColorConfigHandler INSTANCE = new MoreColorConfigHandler();
    public static MoreColorConfigHandler getInstance() { return INSTANCE; }
    private static MoreColorConfigData CONFIG = new MoreColorConfigData();
    private boolean loaded = false;
    
    @Override
    public MoreColorConfigData newConfig()
    {
        return new MoreColorConfigData();
    }

    @Override
    public MoreColorConfigData getConfig()
    {
        return CONFIG;
    }

    @Override
    public boolean isLoaded()
    {
        return this.loaded;
    }

    @Override
    public void onPreLoadConfig()
    {
        this.loaded = false;
    }

    @Override
    public void onPostLoadConfig()
    {
        this.loaded = true;
    }

    @Override
    public void onPreSaveConfig()
    {
        this.loaded = false;
    }

    @Override
    public void onPostSaveConfig()
    {
        this.loaded = true;
    }

    @Override
    public void defaults(IConfigData data)
    {
        // Check for default values
        CONFIG.COLORS.putIfAbsent("bluetiful","#3C69E7");
        CONFIG.ALIASES.putIfAbsent("bluetiful", List.of("blue2"));
        CONFIG.COLORS.putIfAbsent("brown","#632C04");
        CONFIG.COLORS.putIfAbsent("burnt_orange","#FF7034");
        CONFIG.ALIASES.putIfAbsent("burnt_orange", List.of("orange2"));
        CONFIG.COLORS.putIfAbsent("canary", "#FFFF99");
        CONFIG.ALIASES.putIfAbsent("canary", List.of("yellow2"));
        CONFIG.COLORS.putIfAbsent("cool_mint", "#DDEBEC");
        CONFIG.COLORS.putIfAbsent("copper", "#DA8A67");
        CONFIG.COLORS.putIfAbsent("cyan","#2D7C9D");
        CONFIG.COLORS.putIfAbsent("dark_brown","#421F05");
        CONFIG.COLORS.putIfAbsent("dark_pink","#DE8BB4");
        CONFIG.COLORS.putIfAbsent("light_blue","#82ACE7");
        CONFIG.COLORS.putIfAbsent("light_brown","#7A4621");
        CONFIG.COLORS.putIfAbsent("light_gray","#BABAC1");
        CONFIG.ALIASES.putIfAbsent("light_gray", List.of("light_grey"));
        CONFIG.COLORS.putIfAbsent("light_pink","#F7B4D6");
        CONFIG.COLORS.putIfAbsent("lime","#76C610");
        CONFIG.COLORS.putIfAbsent("magenta","#CB69C5");
        CONFIG.COLORS.putIfAbsent("orange","#E69E34");
        CONFIG.COLORS.putIfAbsent("pink","#EDA7CB");
        CONFIG.COLORS.putIfAbsent("powder_blue", "#C0D5F0");
        CONFIG.COLORS.putIfAbsent("purple","#A453CE");
        CONFIG.COLORS.putIfAbsent("royal_purple", "#6B3FA0");
        CONFIG.COLORS.putIfAbsent("salmon","#FF91A4");
        CONFIG.ALIASES.putIfAbsent("salmon", List.of("pink_salmon"));
        CONFIG.COLORS.putIfAbsent("shamrock","#33CC99");
        CONFIG.COLORS.putIfAbsent("tickle_me_pink", "#FC80A5");
        CONFIG.COLORS.putIfAbsent("ultramarine_blue", "#3F26BF");
        CONFIG.ALIASES.putIfAbsent("ultramarine_blue", List.of("ultramarine"));
        CoreLog.debug("MoreColorConfigHandler.defaults() initialized.");
    }

    @Override
    public void update(IConfigData data)
    {
        // Refresh existing data values into save file
        CoreLog.debug("MoreColorConfigHandler.refresh() has been called.");

        for (MoreColorNode iColor : MoreColorNode.COLORS)
        {
            CONFIG.COLORS.putIfAbsent(iColor.getName(), iColor.getHexCode());
            if (iColor.getAliases() != null)
            {
                CONFIG.ALIASES.putIfAbsent(iColor.getName(), iColor.getAliases());
            }
        }
    }

    @Override
    public void execute(IConfigData data)
    {
        // Do this when the Colors are reloaded.
        boolean added = false;
        CoreLog.debug("MoreColorConfigHandler.execute() has been called.");

        for (Map.Entry<String,String> stringEntry : CONFIG.COLORS.entrySet())
        {
            String colorName = stringEntry.getKey();
            MoreColorNode iColor = MoreColorNode.getColorByName(colorName);
            if (iColor == null)
            {
                List<String> alias = CONFIG.ALIASES.get(colorName);
                if (alias == null)
                    MoreColorNode.addColor(colorName, stringEntry.getValue());
                else {
                    MoreColorNode.addColor(colorName, stringEntry.getValue(), alias);
                    CoreLog.debug("Adding aliases to color " + colorName + " named: " + alias);
                }
                added = true;
            }
            else
                CoreLog.debug("Can't add color: "+colorName+" because it already exists.");
        }
        if (added)
        {
            CoreLog.debug("Attempting to register any new color nodes.");
            NodeManagerV2.registerColors();
        }
    }
}
