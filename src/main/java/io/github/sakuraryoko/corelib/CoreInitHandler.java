package io.github.sakuraryoko.corelib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.text.Text;
import io.github.sakuraryoko.corelib.commands.testCommand;
import io.github.sakuraryoko.corelib.config.ConfigManager;
import io.github.sakuraryoko.corelib.init.IModInitDispatch;
import io.github.sakuraryoko.corelib.init.ModInitData;
import io.github.sakuraryoko.corelib.nodes.NodeManagerV2;
import io.github.sakuraryoko.corelib.util.CoreLog;

public class CoreInitHandler implements IModInitDispatch
{
    private final static CoreInitHandler INSTANCE = new CoreInitHandler();
    public static CoreInitHandler getInstance() { return INSTANCE; }

    private final String MOD_ID = "corelib";
    private final ModInitData MOD_INFO = new ModInitData(MOD_ID);

    @Override
    public ModInitData getModInit()
    {
        return this.MOD_INFO;
    }

    @Override
    public String getModId()
    {
        return this.MOD_ID;
    }

    @Override
    public List<String> getBasic(List<String> elements)
    {
        Map<String, String> infoBasic = MOD_INFO.getModBasicInfo();
        List<String> result = new ArrayList<>();

        for (String element : elements)
        {
            result.add(infoBasic.get(element));
        }

        return result;
    }

    @Override
    public List<Text> getFormatted(List<String> elements)
    {
        Map<String, Text> infoFmt = MOD_INFO.getModFormattedInfo();
        List<Text> result = new ArrayList<>();

        for (String element : elements)
        {
            result.add(infoFmt.get(element));
        }

        return result;
    }

    @Override
    public boolean isDebug()
    {
        return true;
    }

    @Override
    public boolean isWrapID()
    {
        return true;
    }

    @Override
    public void onModInit()
    {
        for (String s : getBasic(List.of("ver", "auth", "desc"))) CoreLog.info(s);

        if (!ConfigManager.loadConfig())
        {
            CoreLog.fatal("Fatal error reading from config files.");
            return;
        }
        NodeManagerV2.registerNodes();
        testCommand.register();
        CoreLog.debug("Successful initialization.");
    }
}
