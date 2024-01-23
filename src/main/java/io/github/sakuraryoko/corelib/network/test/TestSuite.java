package io.github.sakuraryoko.corelib.network.test;

import io.github.sakuraryoko.corelib.info.ModManager;
import io.github.sakuraryoko.corelib.network.handler.S2CHandler;
import io.github.sakuraryoko.corelib.network.handler.C2SHandler;
import io.github.sakuraryoko.corelib.network.payload.C2SDataPayload;
import io.github.sakuraryoko.corelib.network.payload.C2SStringPayload;
import io.github.sakuraryoko.corelib.network.payload.S2CDataPayload;
import io.github.sakuraryoko.corelib.network.payload.S2CStringPayload;
import io.github.sakuraryoko.corelib.util.CoreLog;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class TestSuite {
    public static void testS2C(ServerPlayerEntity player, String msg)
    {
        if (ModManager.isServer()) {
            S2CStringPayload S2CTest1 = new S2CStringPayload(msg);
            S2CHandler.send(player, S2CTest1);

            /*
            PacketByteBuf buf =  new PacketByteBuf(Unpooled.buffer());
            Identifier id = Identifier.of("testS2C", "payload");
            buf.writeIdentifier(id);
            buf.writeString(msg);
            S2CDataPayload S2CTest2 = new S2CDataPayload(id, buf);
            S2CHandler.send(player, S2CTest2);

             */
        }
        else
            CoreLog.info("testS2C() called from a Client Environment.");
    }
    public static void testC2S(String msg)
    {
        if (ModManager.isClient())
        {
            C2SStringPayload C2STest1 = new C2SStringPayload(msg);
            C2SHandler.send(C2STest1);
/*
            PacketByteBuf buf =  new PacketByteBuf(Unpooled.buffer());
            Identifier id = Identifier.of("testC2S", "payload");
            buf.writeIdentifier(id);
            buf.writeString(msg);
            C2SDataPayload S2CTest2 = new C2SDataPayload(id, buf);
            C2SHandler.send(S2CTest2);

 */
        }
        else
            CoreLog.info("testC2S() called from a Server Environment.");
    }
}
