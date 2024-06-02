package io.github.sakuraryoko.corelib.api.config;

import java.util.List;
import java.util.Map;
import io.github.sakuraryoko.corelib.api.nodes.MoreColorNode;
import io.github.sakuraryoko.corelib.api.nodes.NodeManagerV1;
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
    public MoreColorConfigData defaults()
    {
        MoreColorConfigData newConf = this.newConfig();

        // Check for default values
        newConf.COLORS.putIfAbsent("bluetiful","#3C69E7");
        newConf.ALIASES.putIfAbsent("bluetiful", List.of("blue2"));
        newConf.COLORS.putIfAbsent("brown","#632C04");
        newConf.COLORS.putIfAbsent("burnt_orange","#FF7034");
        newConf.ALIASES.putIfAbsent("burnt_orange", List.of("orange2"));
        newConf.COLORS.putIfAbsent("canary", "#FFFF99");
        newConf.ALIASES.putIfAbsent("canary", List.of("yellow2"));
        newConf.COLORS.putIfAbsent("cool_mint", "#DDEBEC");
        newConf.COLORS.putIfAbsent("copper", "#DA8A67");
        newConf.COLORS.putIfAbsent("cyan","#2D7C9D");
        newConf.COLORS.putIfAbsent("dark_brown","#421F05");
        newConf.COLORS.putIfAbsent("dark_pink","#DE8BB4");
        newConf.COLORS.putIfAbsent("light_blue","#82ACE7");
        newConf.COLORS.putIfAbsent("light_brown","#7A4621");
        newConf.COLORS.putIfAbsent("light_gray","#BABAC1");
        newConf.ALIASES.putIfAbsent("light_gray", List.of("light_grey"));
        newConf.COLORS.putIfAbsent("light_pink","#F7B4D6");
        newConf.COLORS.putIfAbsent("lime","#76C610");
        newConf.COLORS.putIfAbsent("magenta","#CB69C5");
        newConf.COLORS.putIfAbsent("orange","#E69E34");
        newConf.COLORS.putIfAbsent("pink","#EDA7CB");
        newConf.COLORS.putIfAbsent("powder_blue", "#C0D5F0");
        newConf.COLORS.putIfAbsent("purple","#A453CE");
        newConf.COLORS.putIfAbsent("royal_purple", "#6B3FA0");
        newConf.COLORS.putIfAbsent("salmon","#FF91A4");
        newConf.ALIASES.putIfAbsent("salmon", List.of("pink_salmon"));
        newConf.COLORS.putIfAbsent("shamrock","#33CC99");
        newConf.COLORS.putIfAbsent("tickle_me_pink", "#FC80A5");
        newConf.COLORS.putIfAbsent("ultramarine_blue", "#3F26BF");
        newConf.ALIASES.putIfAbsent("ultramarine_blue", List.of("ultramarine"));
        CoreLog.debug("MoreColorConfigHandler.defaults() initialized.");

        return newConf;
    }

    @Override
    public MoreColorConfigData update(IConfigData newData)
    {
        MoreColorConfigData newConf = (MoreColorConfigData) newData;
        CoreLog.debug("MoreColorConfigHandler.refresh() has been called.");

        // Refresh existing data values into save file
/*
        for (MoreColorNode iColor : MoreColorNode.COLORS)
        {
            CONFIG.COLORS.putIfAbsent(iColor.getName(), iColor.getHexCode());
            if (iColor.getAliases() != null)
            {
                CONFIG.ALIASES.putIfAbsent(iColor.getName(), iColor.getAliases());
            }
        }
 */

        return CONFIG;
    }

    @Override
    public void execute()
    {
        boolean added = false;
        CoreLog.debug("MoreColorConfigHandler.execute() has been called.");

        // Do this when the Colors are reloaded.
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
            NodeManagerV1.registerColors();
        }
    }
}
