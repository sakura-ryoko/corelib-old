package io.github.sakuraryoko.corelib.network;

import io.github.sakuraryoko.corelib.info.ModManager;
import io.github.sakuraryoko.corelib.network.handler.S2CHandler;
import io.github.sakuraryoko.corelib.network.payload.*;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientHandlerManager
{
    //static ClientPlayNetworking.PlayPayloadHandler<C2SStringPayload> C2SStringHandler;
    static ClientPlayNetworking.PlayPayloadHandler<StringPayload> S2CStringHandler;
    //static ClientPlayNetworking.PlayPayloadHandler<C2SDataPayload> C2SDataHandler;
    static ClientPlayNetworking.PlayPayloadHandler<DataPayload> S2CDataHandler;

    public static void registerDefaultReceivers()
    {
        // Wait until world join
        if (ModManager.isClient())
        {
            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): isClient() true.");
            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): registerStringHandler()");

            //ClientPlayNetworking.registerGlobalReceiver(C2SStringPayload.TYPE, C2SStringHandler);
            ClientPlayNetworking.registerGlobalReceiver(StringPayload.TYPE, S2CStringHandler);

            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): registerDataHandler()");
            //ClientPlayNetworking.registerGlobalReceiver(C2SDataPayload.TYPE, C2SDataHandler);
            ClientPlayNetworking.registerGlobalReceiver(DataPayload.TYPE, S2CDataHandler);

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
            ClientPlayNetworking.unregisterGlobalReceiver(StringPayload.TYPE.id());

            CoreLog.debug("PayloadTypes#unregisterDefaultReceivers(): registerDataHandler()");
            //ClientPlayNetworking.unregisterGlobalReceiver(C2SDataPayload.TYPE.id());
            ClientPlayNetworking.unregisterGlobalReceiver(DataPayload.TYPE.id());

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
