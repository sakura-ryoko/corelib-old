package io.github.sakuraryoko.corelib.events;

import io.github.sakuraryoko.corelib.network.ClientHandlerManager;
import io.github.sakuraryoko.corelib.network.test.ClientDebugSuite;

public class ClientEvents
{
    public static void joining()
    {
        ClientDebugSuite.checkGlobalChannels();
    }
    public static void joined()
    {
        ClientHandlerManager.registerDefaultReceivers();
        ClientDebugSuite.checkGlobalChannels();
    }

    public static void disconnecting()
    {
        ClientHandlerManager.unregisterDefaultReceivers();
        ClientDebugSuite.checkGlobalChannels();
    }
    public static void disconnected()
    {
        ClientDebugSuite.checkGlobalChannels();
    }
}
