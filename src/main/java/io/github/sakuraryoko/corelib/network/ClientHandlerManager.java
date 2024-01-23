package io.github.sakuraryoko.corelib.network;

import io.github.sakuraryoko.corelib.info.ModManager;
import io.github.sakuraryoko.corelib.network.handler.C2SHandler;
import io.github.sakuraryoko.corelib.network.handler.S2CHandler;
import io.github.sakuraryoko.corelib.network.payload.C2SDataPayload;
import io.github.sakuraryoko.corelib.network.payload.C2SStringPayload;
import io.github.sakuraryoko.corelib.network.payload.S2CDataPayload;
import io.github.sakuraryoko.corelib.network.payload.S2CStringPayload;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientHandlerManager
{
    //static ClientPlayNetworking.PlayPayloadHandler<C2SStringPayload> C2SStringHandler;
    static ClientPlayNetworking.PlayPayloadHandler<S2CStringPayload> S2CStringHandler;
    //static ClientPlayNetworking.PlayPayloadHandler<C2SDataPayload> C2SDataHandler;
    static ClientPlayNetworking.PlayPayloadHandler<S2CDataPayload> S2CDataHandler;

    public static void registerDefaultReceivers()
    {
        // Wait until world join
        if (ModManager.isClient())
        {
            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): isClient() true.");
            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): registerStringHandler()");

            //ClientPlayNetworking.registerGlobalReceiver(C2SStringPayload.TYPE, C2SStringHandler);
            ClientPlayNetworking.registerGlobalReceiver(S2CStringPayload.TYPE, S2CStringHandler);

            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): registerDataHandler()");
            //ClientPlayNetworking.registerGlobalReceiver(C2SDataPayload.TYPE, C2SDataHandler);
            ClientPlayNetworking.registerGlobalReceiver(S2CDataPayload.TYPE, S2CDataHandler);

            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): END.");
        }
    }

    public static void unregisterDefaultReceivers() {
        // Do when disconnecting from server
        if (ModManager.isClient())
        {
            CoreLog.debug("PayloadTypes#unregisterDefaultReceivers(): isClient() true.");
            CoreLog.debug("PayloadTypes#unregisterDefaultReceivers(): registerStringHandler()");

            //ClientPlayNetworking.unregisterGlobalReceiver(C2SStringPayload.TYPE.id());
            ClientPlayNetworking.unregisterGlobalReceiver(S2CStringPayload.TYPE.id());

            CoreLog.debug("PayloadTypes#unregisterDefaultReceivers(): registerDataHandler()");
            //ClientPlayNetworking.unregisterGlobalReceiver(C2SDataPayload.TYPE.id());
            ClientPlayNetworking.unregisterGlobalReceiver(S2CDataPayload.TYPE.id());

            CoreLog.debug("PayloadTypes#unregisterDefaultReceivers(): END.");
        }
    }
    static
    {
        //C2SStringHandler = C2SHandler::receive;
        S2CStringHandler = S2CHandler::receive;
        //C2SDataHandler = C2SHandler::receive;
        S2CDataHandler = S2CHandler::receive;
    }
}
