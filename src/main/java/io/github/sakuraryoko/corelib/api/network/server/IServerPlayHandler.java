package io.github.sakuraryoko.corelib.api.network.server;

import net.minecraft.network.packet.CustomPayload;

public interface IServerPlayHandler
{
    <P extends CustomPayload> void registerServerPlayHandler(IPluginServerPlayHandler<P> handler);
    <P extends CustomPayload> void unregisterServerPlayHandler(IPluginServerPlayHandler<P> handler);
}
