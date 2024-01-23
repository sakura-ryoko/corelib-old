package io.github.sakuraryoko.corelib.network.payload;

import io.github.sakuraryoko.corelib.network.PayloadTypes;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record C2SStringPayload(String data) implements CustomPayload {
    public static final Id<C2SStringPayload> TYPE;
    public static final PacketCodec<RegistryByteBuf, C2SStringPayload> CODEC;
    public C2SStringPayload(PacketByteBuf buf) { this(buf.readString()); }
    public void write(PacketByteBuf buf) { buf.writeString(this.data); }
    @Override
    public Id<? extends CustomPayload> getId() { return TYPE; }
    static {
        TYPE = new Id<>(PayloadTypes.getPayloadId(PayloadTypes.getNamespace(), "c2s-string"));
        CODEC = CustomPayload.codecOf(C2SStringPayload::write, C2SStringPayload::new);
    }
}
