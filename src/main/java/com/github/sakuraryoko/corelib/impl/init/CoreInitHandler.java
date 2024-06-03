package com.github.sakuraryoko.corelib.impl.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.text.Text;
import com.github.sakuraryoko.corelib.impl.config.MainConfigHandler;
import com.github.sakuraryoko.corelib.api.config.ConfigHandler;
import com.github.sakuraryoko.corelib.api.config.ConfigHandlerObject;
import com.github.sakuraryoko.corelib.api.init.IModInitDispatch;
import com.github.sakuraryoko.corelib.api.init.ModInitData;
import com.github.sakuraryoko.corelib.impl.config.MoreColorConfigHandler;
import com.github.sakuraryoko.corelib.util.CoreLog;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
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

        MainConfigHandler mainConfigHandler = new MainConfigHandler();
        ConfigHandlerObject mainConfigObject = new ConfigHandlerObject(mainConfigHandler, mainConfigHandler.getConfig(), this.MOD_ID, true);
        ConfigHandler.getInstance().registerModConfigHandler(mainConfigObject);

        MoreColorConfigHandler moreColorConfigHandler = new MoreColorConfigHandler();
        ConfigHandlerObject moreColorConfigObject = new ConfigHandlerObject(moreColorConfigHandler, moreColorConfigHandler.getConfig(), this.MOD_ID, "more-colors", false);
        ConfigHandler.getInstance().registerModConfigHandler(moreColorConfigObject);

        //NodeManagerV1.registerNodes();
        //testCommand.register();
        CoreLog.debug("Successful initialization.");
    }
}
