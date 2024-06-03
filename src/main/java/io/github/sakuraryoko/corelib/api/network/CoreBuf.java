package io.github.sakuraryoko.corelib.api.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketByteBuf;

/**
 * This can be used as a replacement for your "PacketByteBuf" type of CustomPayloads,
 * And can be used to try to (re)-implement some of your old IPluginChannelHandler based
 * protocols, if needed; but generally using an NbtCompound Payload is simple enough.
 */
public class CoreBuf extends PacketByteBuf
{
    public CoreBuf(ByteBuf parent)
    {
        super(parent);
    }
}
