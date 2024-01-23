package io.github.sakuraryoko.corelib.events;

import io.github.sakuraryoko.corelib.util.CoreLog;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerEvents {
    public static void onPlayerJoin(ServerPlayerEntity player) {
        CoreLog.debug("Player "+player.getName().getLiteralString()+" has joined.");
    }
    public static void onPlayerLeave(ServerPlayerEntity player) {
        CoreLog.debug("Player "+player.getName().getLiteralString()+" has left.");
    }
}
