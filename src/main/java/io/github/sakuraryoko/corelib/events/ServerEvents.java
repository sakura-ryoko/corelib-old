package io.github.sakuraryoko.corelib.events;

import io.github.sakuraryoko.corelib.network.ClientHandlerManager;
import io.github.sakuraryoko.corelib.network.test.DebugSuite;

public class ServerEvents
{
    public static void starting()
    {
        DebugSuite.checkGlobalChannels();

    }
    public static void started()
    {
        //ClientHandlerManager.registerDefaultReceivers();
        DebugSuite.checkGlobalChannels();
    }

    public static void stopping()
    {
        //ClientHandlerManager.unregisterDefaultReceivers();
        DebugSuite.checkGlobalChannels();
    }
    public static void stopped()
    {
        DebugSuite.checkGlobalChannels();

    }
}
