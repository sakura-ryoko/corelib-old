package com.github.sakuraryoko.corelib.api.events;

import net.minecraft.server.network.ServerPlayerEntity;
import com.github.sakuraryoko.corelib.util.CoreLog;

public class PlayerEvents
{
    public static void onPlayerJoin(ServerPlayerEntity player)
    {
        CoreLog.debug("PlayerEvents: "+player.getName().getLiteralString()+" has joined.");
    }

    public static void onPlayerLeave(ServerPlayerEntity player)
    {
        CoreLog.debug("PlayerEvents: "+player.getName().getLiteralString()+" has left.");
    }
}
