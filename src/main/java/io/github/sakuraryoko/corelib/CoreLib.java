package io.github.sakuraryoko.corelib;

import net.fabricmc.api.ModInitializer;
import io.github.sakuraryoko.corelib.api.init.CoreInitHandler;
import io.github.sakuraryoko.corelib.impl.init.ModInitHandler;

public class CoreLib implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        ModInitHandler.getInstance().registerModInitHandler(new CoreInitHandler());
    }
}
