package io.github.sakuraryoko.corelib.network;

import io.github.sakuraryoko.corelib.info.ModManager;
import io.github.sakuraryoko.corelib.network.handler.C2SHandler;
import io.github.sakuraryoko.corelib.network.handler.S2CHandler;
import io.github.sakuraryoko.corelib.network.payload.C2SDataPayload;
import io.github.sakuraryoko.corelib.network.payload.C2SStringPayload;
import io.github.sakuraryoko.corelib.network.payload.S2CDataPayload;
import io.github.sakuraryoko.corelib.network.payload.S2CStringPayload;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class PayloadTypes {
    public static final int MAX_TOTAL_PER_PACKET_S2C = 1048576;
    public static final int MAX_TOTAL_PER_PACKET_C2S = 32767;
    public static final String NAMESPACE_DEFAULT = ModManager.getID();
    private static String NAMESPACE = NAMESPACE_DEFAULT;
    // Since "name" aka mod_id is assumed to be known, we search by "path" as the key value
    private static final Map<String, PayloadType> TYPES = new HashMap<>();
    @Nullable
    protected Identifier putIfAbsent(String name, String path, String reg)
    {
        if (checkName(name))
        {
            if (TYPES.containsKey(path))
                return TYPES.get(path).getId();
            else
            {
                PayloadType type = new PayloadType(name, path, checkRegister(reg));
                TYPES.put(path, type);
                return type.getId();
            }
        }
        else return null;
    }
    @Nullable
    protected Identifier putOrElseNull(String name, String path, String reg)
    {
        if (checkName(name))
        {
            PayloadType type = new PayloadType(name, path, checkRegister(reg));
            TYPES.put(path, type);
            return type.getId();
        }
        else return null;
    }
    @Nullable
    public static PayloadType getType(String name, String path)
    {
        if (checkName(name))
        {
            return TYPES.getOrDefault(path, null);
        }
        else return null;

    }
    @Nullable
    public static Identifier getId(String name, String path)
    {
        if (checkName(name))
        {
            if (TYPES.containsKey(path))
            {
                return TYPES.get(path).getId();
            }
            else return null;
        }
        else return null;
    }
    private static boolean checkName(String name)
    {
        if (NAMESPACE.isEmpty()) {
            // Allow it to be initialized for this instance
            NAMESPACE = name;
            return true;
        }
        else return NAMESPACE.equals(name);
    }
    private static String checkRegister(String reg)
    {
        return switch (reg) {
            case "PLAY_S2C" -> "PLAY_S2C";
            case "CONFIG_C2S" -> "CONFIG_C2S";
            case "CONFIG_S2C" -> "CONFIG_S2C";
            default -> "PLAY_C2S";
        };
    }
    protected static void setNamespace(String name) { NAMESPACE = name; }
    public static String getNamespace() { return NAMESPACE; }
    public static void registerDefaultTypes() {
        CoreLog.debug("PayloadTypes#registerDefaultTypes(): Namespace: "+getNamespace());
        setNamespace(ModManager.getID());
        CoreLog.debug("PayloadTypes#registerDefaultTypes(): Namespace: "+getNamespace());

        if (ModManager.isClient()) {
            CoreLog.debug("PayloadTypes#registerDefaultTypes(): isClient() true.");
            // Setup Channel Registers
            TYPES.put(NAMESPACE, new PayloadType(NAMESPACE, "c2s-data", checkRegister("PLAY_C2S")));
            TYPES.put(NAMESPACE, new PayloadType(NAMESPACE, "c2s-string", checkRegister("PLAY_C2S")));

            CoreLog.debug("PayloadTypes#registerDefaultTypes(): registerType()/Codec()");
            // Register Payloads (PLAY Channel)
            PayloadTypeRegistry.playC2S().register(C2SStringPayload.TYPE, C2SStringPayload.CODEC);
            //PayloadTypeRegistry.playC2S().register(C2SDataPayload.TYPE, C2SDataPayload.CODEC);

            CoreLog.debug("PayloadTypes#registerDefaultTypes(): registerC2SStringHandler()");
            // Client -> Server
            ClientPlayNetworking.PlayPayloadHandler<C2SStringPayload> C2SStringHandler = C2SHandler::receive;
            ClientPlayNetworking.registerReceiver(C2SStringPayload.TYPE, C2SStringHandler);

            CoreLog.debug("PayloadTypes#registerDefaultTypes(): END.");
            //ServerPlayNetworking.PlayPayloadHandler<C2SDataPayload> C2SDataHandler = C2SHandler::receive;
            //ServerPlayNetworking.registerGlobalReceiver(C2SDataPayload.TYPE, C2SDataHandler);
        } else {
            CoreLog.debug("PayloadTypes#registerDefaultTypes(): isClient() false.");
            // Setup Channel Registers
            TYPES.put(NAMESPACE, new PayloadType(NAMESPACE, "s2c-data", checkRegister("PLAY_S2C")));
            TYPES.put(NAMESPACE, new PayloadType(NAMESPACE, "s2c-string", checkRegister("PLAY_S2C")));

            CoreLog.debug("PayloadTypes#registerDefaultTypes(): registerType()/Codec()");
            // Register Payloads (PLAY Channel)
            //PayloadTypeRegistry.playS2C().register(S2CDataPayload.TYPE, S2CDataPayload.CODEC);
            PayloadTypeRegistry.playS2C().register(S2CStringPayload.TYPE, S2CStringPayload.CODEC);

            CoreLog.debug("PayloadTypes#registerDefaultTypes(): registerS2CStringHandler()");
            // Setup Receivers
            // Server -> Client
            ServerPlayNetworking.PlayPayloadHandler<S2CStringPayload> S2CStringHandler = S2CHandler::receive;
            ServerPlayNetworking.registerGlobalReceiver(S2CStringPayload.TYPE, S2CStringHandler);

            CoreLog.debug("PayloadTypes#registerDefaultTypes(): END.");
            //ClientPlayNetworking.PlayPayloadHandler<S2CDataPayload> S2CDataHandler = S2CHandler::receive;
            //ClientPlayNetworking.registerGlobalReceiver(S2CDataPayload.TYPE, S2CDataHandler);
        }
    }
}
