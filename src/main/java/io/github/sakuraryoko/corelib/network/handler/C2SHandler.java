package io.github.sakuraryoko.corelib.network.handler;

import io.github.sakuraryoko.corelib.network.payload.C2SDataPayload;
import io.github.sakuraryoko.corelib.network.payload.C2SStringPayload;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class C2SHandler {
    public static void receive(C2SStringPayload payload, ClientPlayNetworking.Context context) {
        CoreLog.info("C2SHandler#receive() A: received C2SString Payload: "+ payload.toString());
    }
    public static void receive(C2SDataPayload payload, ClientPlayNetworking.Context context) {
        CoreLog.info("C2SHandler#receive() B: received C2SData Payload (size in bytes): "+ payload.data().readableBytes());
        CoreLog.info("C2SHandler#receive() B: id: "+payload.data().readIdentifier());
        CoreLog.info("C2SHandler#receive() B: String: "+payload.data().readString());
    }
    public static void send(C2SStringPayload payload) {
        if (ClientPlayNetworking.canSend(payload.getId())) {
            ClientPlayNetworking.send(payload);
            CoreLog.debug("C2SHandler#send(): sending payload id: "+payload.getId());
        }
    }
    public static void send(C2SDataPayload payload) {
        if (ClientPlayNetworking.canSend(payload.getId())) {
            ClientPlayNetworking.send(payload);
            CoreLog.debug("C2SHandler#send(): sending payload id: "+payload.getId());
        }
    }
}
