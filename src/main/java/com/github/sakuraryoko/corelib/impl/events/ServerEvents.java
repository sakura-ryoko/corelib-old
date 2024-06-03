package com.github.sakuraryoko.corelib.impl.events;

import com.github.sakuraryoko.corelib.api.config.ConfigHandler;
import com.github.sakuraryoko.corelib.util.CoreLog;

public class ServerEvents
{
    public static void starting()
    {
        CoreLog.debug("ServerEvents: server starting");

        ((ConfigHandler) ConfigHandler.getInstance()).loadAllConfigs();
    }

    public static void started()
    {
        CoreLog.debug("ServerEvents: server started");

        ((ConfigHandler) ConfigHandler.getInstance()).saveAllConfigs();
    }

    public static void integratedSetup()
    {
        CoreLog.debug("ServerEvents: integrated server setup");
    }

    public static void openToLan()
    {
        CoreLog.debug("ServerEvents: open to lan started");
    }

    public static void dedicatedSetup()
    {
        CoreLog.debug("ServerEvents: dedicated server setup");
    }

    public static void stopping()
    {
        CoreLog.debug("ServerEvents: server stopping");
    }

    public static void stopped()
    {
        CoreLog.debug("ServerEvents: server stopped");

        ((ConfigHandler) ConfigHandler.getInstance()).saveAllConfigs();
    }
}
