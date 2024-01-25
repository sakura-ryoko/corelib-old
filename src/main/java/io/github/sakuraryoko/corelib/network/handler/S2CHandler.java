package io.github.sakuraryoko.corelib.network.handler;

import io.github.sakuraryoko.corelib.util.CoreLog;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import io.github.sakuraryoko.corelib.network.payload.*;

public class S2CHandler {
    public static void receive(StringPayload payload, ServerPlayNetworking.Context context) {
        CoreLog.info("S2CHandler#receive() A: received S2CString Payload: "+ payload.toString());
    }
    public static void receive(DataPayload payload, ServerPlayNetworking.Context context) {
        CoreLog.info("S2CHandler#receive() B: received S2CData Payload (size in bytes): "+ payload.data().getSizeInBytes());
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeByteArray(payload.data().getByteArray(DataPayload.NBT));
        // --> To write a PacketByteBuf from NbtCompound
        //        String response = payload.data().getString(DataPayload.NBT);

        CoreLog.info("S2CHandler#receive() B: buf size in bytes: "+buf.readableBytes());
        String response = buf.readString();
        CoreLog.info("S2CHandler#receive() B: String: "+response);
    }
    public static void receive(StringPayload payload, ClientPlayNetworking.Context context) {
        CoreLog.info("S2CHandler#receive() A: received S2CString Payload: "+ payload.toString());
    }
    public static void receive(DataPayload payload, ClientPlayNetworking.Context context) {
        CoreLog.info("S2CHandler#receive() B: received S2CData Payload (size in bytes): "+ payload.data().getSizeInBytes());
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeByteArray(payload.data().getByteArray(DataPayload.NBT));
        // --> To write a PacketByteBuf from NbtCompound
        //        String response = payload.data().getString(DataPayload.NBT);
        CoreLog.info("S2CHandler#receive() B: buf size in bytes: "+buf.readableBytes());
        String response = buf.readString();
        CoreLog.info("S2CHandler#receive() B: String: "+response);

    }
    public static void send(ServerPlayerEntity player, StringPayload payload) {
        if (ServerPlayNetworking.canSend(player, payload.getId())) {
            ServerPlayNetworking.send(player, payload);
            CoreLog.debug("S2CHandler#send(): sending payload id: "+payload.getId());
        }
    }
    public static void send(StringPayload payload) {
        if (ClientPlayNetworking.canSend(payload.getId())) {
            ClientPlayNetworking.send(payload);
            CoreLog.debug("S2CHandler#send(): sending payload id: "+payload.getId());
        }
    }
    public static void send(ServerPlayerEntity player, DataPayload payload) {
        if (ServerPlayNetworking.canSend(player, payload.getId())) {
            ServerPlayNetworking.send(player, payload);
            CoreLog.debug("S2CHandler#send(): sending payload id: "+payload.getId());
        }
    }
    public static void send(DataPayload payload) {
        if (ClientPlayNetworking.canSend(payload.getId())) {
            ClientPlayNetworking.send(payload);
            CoreLog.debug("S2CHandler#send(): sending payload id: "+payload.getId());
        }
    }
}
