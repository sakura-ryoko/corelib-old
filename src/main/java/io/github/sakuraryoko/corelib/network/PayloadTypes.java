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
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class PayloadTypes {
    public static final int MAX_TOTAL_PER_PACKET_S2C = 1048576;
    public static final int MAX_TOTAL_PER_PACKET_C2S = 32767;
    public static final String NAMESPACE_DEFAULT = ModManager.getID();
    private static String NAMESPACE = NAMESPACE_DEFAULT;
    // Since "name" aka mod_id is assumed to be known, we search by "path" as the key value

    @Nullable
    public static Identifier getId(String name, String path) {
        CoreLog.debug("PayloadTypes#getId(): name: " + name + " path: " + path);
        if (checkName(name)) {
            return new Identifier(name, path);
        } else return null;
    }

    private static boolean checkName(String name) {
        if (NAMESPACE.isEmpty()) {
            // Allow it to be initialized for this instance
            NAMESPACE = name;
            return true;
        } else return NAMESPACE.equals(name);
    }

    protected static void setNamespace(String name) {
        NAMESPACE = name;
    }

    public static String getNamespace() {
        return NAMESPACE;
    }

    public static void registerDefaultReceivers() {
        // Wait until server starts / world join
        if (ModManager.isClient()) {
            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): isClient() true.");
            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): registerC2SStringHandler()");
            // Client -> Server
            ClientPlayNetworking.PlayPayloadHandler<C2SStringPayload> C2SStringHandler = C2SHandler::receive;
            ClientPlayNetworking.registerReceiver(C2SStringPayload.TYPE, C2SStringHandler);
//            ClientPlayNetworking.PlayPayloadHandler<S2CStringPayload> S2CStringHandler = S2CHandler::receive;
//            ClientPlayNetworking.registerReceiver(S2CStringPayload.TYPE, S2CStringHandler);

            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): END.");
            //ServerPlayNetworking.PlayPayloadHandler<C2SDataPayload> C2SDataHandler = C2SHandler::receive;
            //ServerPlayNetworking.registerGlobalReceiver(C2SDataPayload.TYPE, C2SDataHandler);
        } else {
            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): isClient() false.");
            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): registerS2CStringHandler()");
            // Setup Receivers
            // Server -> Client
            ServerPlayNetworking.PlayPayloadHandler<S2CStringPayload> S2CStringHandler = S2CHandler::receive;
            ServerPlayNetworking.registerGlobalReceiver(S2CStringPayload.TYPE, S2CStringHandler);
//            ServerPlayNetworking.PlayPayloadHandler<C2SStringPayload> C2SStringHandler = C2SHandler::receive;
//            ServerPlayNetworking.registerReceiver(C2SStringPayload.TYPE, C2SStringHandler);

            CoreLog.debug("PayloadTypes#registerDefaultReceivers(): END.");
            //ClientPlayNetworking.PlayPayloadHandler<S2CDataPayload> S2CDataHandler = S2CHandler::receive;
            //ClientPlayNetworking.registerGlobalReceiver(S2CDataPayload.TYPE, S2CDataHandler);
        }
    }

    public static void unregisterDefaultReceivers() {
        // Wait until server starts / world join
        if (ModManager.isClient())
        {
        }
        else
        {
        }
    }

    public static void registerDefaultTypes()
    {
        CoreLog.debug("PayloadTypes#registerDefaultTypes(): Namespace: "+getNamespace());
        setNamespace(ModManager.getID());

        if (ModManager.isClient()) {
            CoreLog.debug("PayloadTypes#registerDefaultTypes(): isClient() true.");
            CoreLog.debug("PayloadTypes#registerDefaultTypes(): registerType()/Codec()");
            // Register Payloads (PLAY Channel)
            PayloadTypeRegistry.playC2S().register(C2SStringPayload.TYPE, C2SStringPayload.CODEC);
            PayloadTypeRegistry.playS2C().register(S2CStringPayload.TYPE, S2CStringPayload.CODEC);
            PayloadTypeRegistry.playC2S().register(C2SDataPayload.TYPE, C2SDataPayload.CODEC);
            PayloadTypeRegistry.playS2C().register(S2CDataPayload.TYPE, S2CDataPayload.CODEC);
        } else {
            CoreLog.debug("PayloadTypes#registerDefaultTypes(): isClient() false.");
            CoreLog.debug("PayloadTypes#registerDefaultTypes(): registerType()/Codec()");
            // Register Payloads (PLAY Channel)
            PayloadTypeRegistry.playC2S().register(C2SStringPayload.TYPE, C2SStringPayload.CODEC);
            PayloadTypeRegistry.playS2C().register(S2CStringPayload.TYPE, S2CStringPayload.CODEC);
            PayloadTypeRegistry.playC2S().register(C2SDataPayload.TYPE, C2SDataPayload.CODEC);
            PayloadTypeRegistry.playS2C().register(S2CDataPayload.TYPE, S2CDataPayload.CODEC);
        }
        CoreLog.debug("PayloadTypes#registerDefaultTypes(): Done.");
    }
}
