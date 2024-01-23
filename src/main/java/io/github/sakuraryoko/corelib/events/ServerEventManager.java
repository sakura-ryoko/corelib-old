package io.github.sakuraryoko.corelib.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class ServerEventManager {
    public static void register() {
        ServerLifecycleEvents.SERVER_STARTING.register((id) -> ServerEvents.starting());
        ServerLifecycleEvents.SERVER_STARTED.register((id) -> ServerEvents.started());
        ServerLifecycleEvents.SERVER_STOPPING.register((id) -> ServerEvents.stopping());
        ServerLifecycleEvents.SERVER_STOPPED.register((id) -> ServerEvents.stopped());
    }
}
