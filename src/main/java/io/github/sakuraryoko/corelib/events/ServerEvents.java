package io.github.sakuraryoko.corelib.events;

import io.github.sakuraryoko.corelib.util.CoreLog;

public class ServerEvents
{
    public static void starting()
    {
        CoreLog.debug("ServerEvents: server starting");
    }

    public static void started()
    {
        CoreLog.debug("ServerEvents: server started");
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
    }
}
