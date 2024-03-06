package io.github.sakuraryoko.corelib;

import com.mojang.serialization.MapCodec;
import io.github.sakuraryoko.corelib.commands.testCommand;
import io.github.sakuraryoko.corelib.config.ConfigManager;
import io.github.sakuraryoko.corelib.events.ServerEventManager;
import io.github.sakuraryoko.corelib.info.ModManager;
import io.github.sakuraryoko.corelib.network.PayloadTypes;
import io.github.sakuraryoko.corelib.network.test.ClientDebugSuite;
import io.github.sakuraryoko.corelib.network.test.ServerDebugSuite;
import io.github.sakuraryoko.corelib.nodes.NodeManagerV2;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class CoreLibMain {
    private static boolean CoreInit = false;
    public static void init() {
        if (CoreInit)
            return;
        CoreInit = true;
        CoreLog.initLogger();
        ModManager.init();
        if (!ConfigManager.loadConfig()) {
            CoreLog.fatal("Fatal error reading from config files.");
            return;
        }
        NodeManagerV2.registerNodes();
        PayloadTypes.registerDefaultTypes();
        if (ModManager.isServer()) ServerDebugSuite.checkGlobalChannels();
        if (ModManager.isClient()) ClientDebugSuite.checkGlobalChannels();
        ServerEventManager.register();
        testCommand.register();
        CoreLog.debug("Successful initialization.");
        if (ModManager.isServer()) ServerDebugSuite.checkGlobalChannels();
        if (ModManager.isClient()) ClientDebugSuite.checkGlobalChannels();

        //listAllBlocks();
    }

    private static void listAllBlocks()
    {
        BlockState bs;
        Block block;
        Identifier id;
        String str = "";
        int air = 0;

        for (int i = 0; i < 4500; i++)
        {
            block = Registries.BLOCK.get(i);
            if (block == Blocks.AIR)
            {
                air++;
                if (air > 50)
                    return;
            }
            else
            {
                id = Registries.BLOCK.getId(block);
                bs = block.getDefaultState();
                NbtCompound nbt = NbtHelper.fromBlockState(bs);
                str = "block_state["+i+"] id: "+id.getNamespace()+":"+id.getPath()+" // "+nbt.toString();
                CoreLog.info(str);
            }
        }
    }
}
