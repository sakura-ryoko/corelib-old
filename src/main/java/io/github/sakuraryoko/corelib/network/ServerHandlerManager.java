package io.github.sakuraryoko.corelib.network;

import io.github.sakuraryoko.corelib.info.ModManager;
import io.github.sakuraryoko.corelib.network.handler.C2SHandler;
import io.github.sakuraryoko.corelib.network.payload.*;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ServerHandlerManager {
    static ServerPlayNetworking.PlayPayloadHandler<StringPayload> C2SStringHandler;
    //static ClientPlayNetworking.PlayPayloadHandler<S2CStringPayload> S2CStringHandler;
    static ServerPlayNetworking.PlayPayloadHandler<DataPayload> C2SDataHandler;
    //static ClientPlayNetworking.PlayPayloadHandler<S2CDataPayload> S2CDataHandler;

    public static void registerDefaultReceivers()
    {
        // Wait until world join
        if (ModManager.isServer())
        {
            CoreLog.debug("ServerHandlerManager#registerDefaultReceivers(): isServer() true.");
            CoreLog.debug("ServerHandlerManager#registerDefaultReceivers(): registerStringHandler()");

            ServerPlayNetworking.registerGlobalReceiver(StringPayload.TYPE, C2SStringHandler);
            //ClientPlayNetworking.registerGlobalReceiver(S2CStringPayload.TYPE, S2CStringHandler);

            CoreLog.debug("ServerHandlerManager#registerDefaultReceivers(): registerDataHandler()");
            ServerPlayNetworking.registerGlobalReceiver(DataPayload.TYPE, C2SDataHandler);
            //ClientPlayNetworking.registerGlobalReceiver(S2CDataPayload.TYPE, S2CDataHandler);

            CoreLog.debug("ServerHandlerManager#registerDefaultReceivers(): END.");
        }
    }

    public static void unregisterDefaultReceivers() {
        // Do when disconnecting from server
        if (ModManager.isServer())
        {
            CoreLog.debug("ServerHandlerManager#unregisterDefaultReceivers(): isServer() true.");
            CoreLog.debug("ServerHandlerManager#unregisterDefaultReceivers(): registerStringHandler()");

            ServerPlayNetworking.unregisterGlobalReceiver(StringPayload.TYPE.id());
            //ClientPlayNetworking.unregisterGlobalReceiver(S2CStringPayload.TYPE.id());

            CoreLog.debug("ServerHandlerManager#unregisterDefaultReceivers(): registerDataHandler()");
            ServerPlayNetworking.unregisterGlobalReceiver(DataPayload.TYPE.id());
            //ClientPlayNetworking.unregisterGlobalReceiver(S2CDataPayload.TYPE.id());

            CoreLog.debug("ServerHandlerManager#unregisterDefaultReceivers(): END.");
        }
    }
    static
    {
        C2SStringHandler = C2SHandler::receive;
        //S2CStringHandler = S2CHandler::receive;
        C2SDataHandler = C2SHandler::receive;
        //S2CDataHandler = S2CHandler::receive;
    }
}
