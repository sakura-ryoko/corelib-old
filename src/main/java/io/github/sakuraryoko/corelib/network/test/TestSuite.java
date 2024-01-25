package io.github.sakuraryoko.corelib.network.test;

import io.github.sakuraryoko.corelib.info.ModManager;
import io.github.sakuraryoko.corelib.network.handler.S2CHandler;
import io.github.sakuraryoko.corelib.network.handler.C2SHandler;
import io.github.sakuraryoko.corelib.network.payload.*;
import io.github.sakuraryoko.corelib.util.CoreLog;
import io.netty.buffer.Unpooled;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class TestSuite {
    public static void testS2C(ServerPlayerEntity player, String msg)
    {
        if (ModManager.isServer()) {
            StringPayload S2CTest1 = new StringPayload(msg);
            S2CHandler.send(player, S2CTest1);

            NbtCompound nbt = new NbtCompound();
            PacketByteBuf buf =  new PacketByteBuf(Unpooled.buffer());
            buf.writeString(msg);
            CoreLog.info("testS2C() buf size in bytes: "+buf.readableBytes());
            nbt.putByteArray(DataPayload.NBT, buf.readByteArray());
//            nbt.putString(DataPayload.NBT, msg);
            CoreLog.info("testS2C() nbt size in bytes: "+nbt.getSizeInBytes());
            DataPayload S2CTest2 = new DataPayload(nbt);
            S2CHandler.send(player, S2CTest2);
        }
        else
            CoreLog.info("testS2C() called from a Client Environment.");
    }
    public static void testC2S(String msg)
    {
        if (ModManager.isClient())
        {
            StringPayload C2STest1 = new StringPayload(msg);
            C2SHandler.send(C2STest1);

            NbtCompound nbt = new NbtCompound();
            PacketByteBuf buf =  new PacketByteBuf(Unpooled.buffer());
            buf.writeString(msg);
            CoreLog.info("testC2S() buf size in bytes: "+buf.readableBytes());
            nbt.putByteArray(DataPayload.NBT, buf.readByteArray());
            CoreLog.info("testC2S() buf size in bytes: "+nbt.getSizeInBytes());
            //nbt.putString(DataPayload.NBT, msg);
            DataPayload C2STest2 = new DataPayload(nbt);
            C2SHandler.send(C2STest2);
        }
        else
            CoreLog.info("testC2S() called from a Server Environment.");
    }
}
