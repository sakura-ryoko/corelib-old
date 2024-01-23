package io.github.sakuraryoko.corelib.events;

import io.github.sakuraryoko.corelib.network.ClientHandlerManager;

public class ClientEvents
{
    public static void joining()
    {
    }
    public static void joined()
    {
        ClientHandlerManager.registerDefaultReceivers();
    }

    public static void disconnecting()
    {
        ClientHandlerManager.unregisterDefaultReceivers();
    }
    public static void disconnected()
    {
    }
}
