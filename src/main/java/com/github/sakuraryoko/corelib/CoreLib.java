package com.github.sakuraryoko.corelib;

import net.fabricmc.api.ModInitializer;
import com.github.sakuraryoko.corelib.impl.init.CoreInitHandler;
import com.github.sakuraryoko.corelib.api.init.ModInitHandler;

public class CoreLib implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        ModInitHandler.getInstance().registerModInitHandler(new CoreInitHandler());
    }
}
