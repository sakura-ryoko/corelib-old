package io.github.sakuraryoko.corelib.network.payload;

import io.github.sakuraryoko.corelib.network.PayloadTypes;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record C2SDataPayload(Identifier id, PacketByteBuf data) implements CustomPayload {
    public static final Id<C2SDataPayload> TYPE = new Id<>(PayloadTypes.getId(PayloadTypes.getNamespace(), "c2s-data"));
    public static final PacketCodec<PacketByteBuf, C2SDataPayload> CODEC = CustomPayload.codecOf(C2SDataPayload::write, C2SDataPayload::new);

    private C2SDataPayload(PacketByteBuf buf)
    {
        this(buf.readIdentifier(), (PacketByteBuf) buf.readNullable((bufx) -> bufx.readBytes(PayloadTypes.MAX_TOTAL_PER_PACKET_C2S)));
    }
    public C2SDataPayload(Identifier id, PacketByteBuf data)
    {
        this.id = id;
        this.data = data;
    }
    private void write(PacketByteBuf buf)
    {
        buf.writeIdentifier(this.id);
        buf.writeBytes(this.data);
    }
    @Override
    public Id<? extends CustomPayload> getId() { return TYPE; }
}
