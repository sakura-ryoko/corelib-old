package io.github.sakuraryoko.corelib;

import net.fabricmc.api.ModInitializer;
import io.github.sakuraryoko.corelib.init.ModInitHandler;

public class CoreLib implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        ModInitHandler.getInstance().registerModInitHandler(new CoreInitHandler());
    }
}
