package io.github.sakuraryoko.corelib.impl.events;

import io.github.sakuraryoko.corelib.impl.config.ConfigHandler;
import io.github.sakuraryoko.corelib.util.CoreLog;

public class ClientEvents
{
    public static void worldChangePre()
    {
        CoreLog.debug("ClientEvents: World Change Pre");
    }

    public static void worldChangePost()
    {
        CoreLog.debug("ClientEvents: World Change Post");

    }

    public static void joining()
    {
        CoreLog.debug("ClientEvents: joining");

        ((ConfigHandler) ConfigHandler.getInstance()).loadAllConfigs();
    }

    public static void openConnection()
    {
        CoreLog.debug("ClientEvents: opening ClientConnection");
    }

    public static void joined()
    {
        CoreLog.debug("ClientEvents: joined");

        ((ConfigHandler) ConfigHandler.getInstance()).saveAllConfigs();
    }

    public static void onGameJoinPre()
    {
        CoreLog.debug("ClientEvents: onGameJoin Pre");
    }

    public static void onGameJoinPost()
    {
        CoreLog.debug("ClientEvents: onGameJoin Post");
    }

    public static void dimensionChangePre()
    {
        CoreLog.debug("ClientEvents: dimension change pre");

        ((ConfigHandler) ConfigHandler.getInstance()).saveAllConfigs();
    }

    public static void dimensionChangePost()
    {
        CoreLog.debug("ClientEvents: dimension change post");
    }

    public static void disconnecting()
    {
        CoreLog.debug("ClientEvents: disconnecting");
    }

    public static void closeConnection()
    {
        CoreLog.debug("ClientEvents: closing ClientConnection");
    }

    public static void disconnected()
    {
        CoreLog.debug("ClientEvents: disconnected");

        ((ConfigHandler) ConfigHandler.getInstance()).saveAllConfigs();
    }
}
