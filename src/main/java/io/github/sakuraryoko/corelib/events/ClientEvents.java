package io.github.sakuraryoko.corelib.events;

import io.github.sakuraryoko.corelib.network.PayloadTypes;

public class ClientEvents {
    public static void starting() {
    }
    public static void started() {
        PayloadTypes.registerDefaultReceivers();
    }

    public static void stopping() {
    }
    public static void stopped() {
    }
}
