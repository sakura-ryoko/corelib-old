package io.github.sakuraryoko.corelib.network.payload;

import io.github.sakuraryoko.corelib.network.PayloadTypes;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record S2CStringPayload(String data) implements CustomPayload {
    public final static Id<S2CStringPayload> TYPE = new Id<>(PayloadTypes.getId(PayloadTypes.getNamespace(), "s2c_string"));
    public final static PacketCodec<PacketByteBuf, S2CStringPayload> CODEC = PacketCodecs.STRING.xmap(S2CStringPayload::new, S2CStringPayload::data).cast();
    @Override
    public Id<S2CStringPayload> getId() { return TYPE; }
}
