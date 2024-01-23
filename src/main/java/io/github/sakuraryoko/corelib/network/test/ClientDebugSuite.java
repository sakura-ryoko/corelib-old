package io.github.sakuraryoko.corelib.network.test;

import io.github.sakuraryoko.corelib.util.CoreLog;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

import java.util.Iterator;
import java.util.Set;

public class ClientDebugSuite {
    public static void checkGlobalChannels() {
        CoreLog.debug("DebugSuite#checkGlobalChannels(): Start.");
        Set<Identifier> channels = ClientPlayNetworking.getGlobalReceivers();
        Iterator<Identifier> iterator = channels.iterator();
        int i = 0;
        while (iterator.hasNext())
        {
            Identifier id = iterator.next();
            i++;
            CoreLog.debug("DebugSuite#checkGlobalChannels(): id("+i+") hash: "+id.hashCode()+" //name: "+id.getNamespace()+" path: "+id.getPath());
        }
        CoreLog.debug("DebugSuite#checkGlobalChannels(): END. Total Channels: "+i);
    }
}
