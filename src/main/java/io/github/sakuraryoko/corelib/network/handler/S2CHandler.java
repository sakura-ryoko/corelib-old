package io.github.sakuraryoko.corelib.network.handler;

import io.github.sakuraryoko.corelib.network.payload.S2CDataPayload;
import io.github.sakuraryoko.corelib.network.payload.S2CStringPayload;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

public class S2CHandler {
    public static void receive(S2CStringPayload payload, ServerPlayNetworking.Context context) {
        CoreLog.info("S2CHandler#receive() A: received S2CString Payload: "+ payload.toString());
    }
    public static void receive(S2CDataPayload payload, ServerPlayNetworking.Context context) {
        CoreLog.info("S2CHandler#receive() B: received S2CData Payload (size in bytes): "+ payload.data().readableBytes());
        CoreLog.info("S2CHandler#receive() B: id: "+payload.data().readIdentifier());
        CoreLog.info("S2CHandler#receive() B: String: "+payload.data().readString());
    }
    public static void send(ServerPlayerEntity player, S2CStringPayload payload) {
        if (ServerPlayNetworking.canSend(player, payload.getId())) {
            ServerPlayNetworking.send(player, payload);
            CoreLog.debug("S2CHandler#send(): sending payload id: "+payload.getId());
        }
    }
    public static void send(ServerPlayerEntity player, S2CDataPayload payload) {
        if (ServerPlayNetworking.canSend(player, payload.getId())) {
            ServerPlayNetworking.send(player, payload);
            CoreLog.debug("S2CHandler#send(): sending payload id: "+payload.getId());
        }
    }
}
