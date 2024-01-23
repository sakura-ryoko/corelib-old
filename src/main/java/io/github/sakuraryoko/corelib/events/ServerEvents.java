package io.github.sakuraryoko.corelib.events;

import io.github.sakuraryoko.corelib.network.ServerHandlerManager;
import io.github.sakuraryoko.corelib.network.test.ServerDebugSuite;

public class ServerEvents
{
    public static void starting()
    {
        ServerDebugSuite.checkGlobalChannels();
    }
    public static void started()
    {
        ServerHandlerManager.registerDefaultReceivers();
        ServerDebugSuite.checkGlobalChannels();
    }

    public static void stopping()
    {
        ServerHandlerManager.unregisterDefaultReceivers();
        ServerDebugSuite.checkGlobalChannels();
    }
    public static void stopped()
    {
        ServerDebugSuite.checkGlobalChannels();
    }
}
