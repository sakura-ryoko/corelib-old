package io.github.sakuraryoko.corelib.events;

import io.github.sakuraryoko.corelib.network.PayloadTypes;
import io.github.sakuraryoko.corelib.network.test.DebugSuite;

public class ServerEvents
{
    public static void starting()
    {
        DebugSuite.checkGlobalChannels();
    }
    public static void started()
    {
        PayloadTypes.registerDefaultReceivers();
        DebugSuite.checkGlobalChannels();
    }

    public static void stopping()
    {
        PayloadTypes.unregisterDefaultReceivers();
        DebugSuite.checkGlobalChannels();
    }
    public static void stopped()
    {
        DebugSuite.checkGlobalChannels();

    }
}
