package io.github.sakuraryoko.corelib.network;

import io.github.sakuraryoko.corelib.info.ModManager;
import io.github.sakuraryoko.corelib.network.payload.*;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class PayloadTypes {
    public static final int MAX_TOTAL_PER_PACKET_S2C = 1048576;
    public static final int MAX_TOTAL_PER_PACKET_C2S = 32767;
    public static final String NAMESPACE_DEFAULT = ModManager.getID();
    private static String NAMESPACE = "malilib";
    // Since "name" aka mod_id is assumed to be known, we search by "path" as the key value

    @Nullable
    public static Identifier getPayloadId(String name, String path) {
        CoreLog.debug("PayloadTypes#getPayloadId(): name: " + name + " path: " + path);
        if (checkName(name)) {
            final Identifier id = new Identifier(name, path);
            CoreLog.debug("PayloadTypes#getPayloadId(): id namespace: " + id.getNamespace() + " path: " + id.getPath());
            return id;
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

    public static void registerDefaultTypes()
    {
        CoreLog.debug("PayloadTypes#registerDefaultTypes(): Namespace: "+getNamespace());
        //setNamespace(ModManager.getID());

        CoreLog.debug("PayloadTypes#registerDefaultTypes(): registerType()/Codec()");
        // Register Payloads (PLAY Channel)
        PayloadTypeRegistry.playC2S().register(StringPayload.TYPE, StringPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(DataPayload.TYPE, DataPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(StringPayload.TYPE, StringPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(DataPayload.TYPE, DataPayload.CODEC);
        CoreLog.debug("PayloadTypes#registerDefaultTypes(): Done.");
    }
}
