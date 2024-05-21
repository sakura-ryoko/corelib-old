package io.github.sakuraryoko.corelib.init;

import java.util.ArrayList;
import java.util.List;

public class ModInitHandler implements IModInitManager
{
    private static final ModInitHandler INSTANCE = new ModInitHandler();
    private final List<IModInitDispatch> handlers = new ArrayList<>();
    public static IModInitManager getInstance() { return INSTANCE; }

    @Override
    public void registerModInitHandler(IModInitDispatch handler)
    {
        if (!this.handlers.contains(handler))
        {
            this.handlers.add(handler);
        }
    }

    // Do Not use
    public void onModInit()
    {
        if (!this.handlers.isEmpty())
        {
            for (IModInitDispatch handler : this.handlers)
            {
                handler.onModInit();
            }
        }
    }

    public void setIntegratedServer(boolean toggle)
    {
        if (!this.handlers.isEmpty())
        {
            for (IModInitDispatch handler : this.handlers)
            {
                handler.setIntegratedServer(toggle);
            }
        }
    }

    public void setDedicatedServer(boolean toggle)
    {
        if (!this.handlers.isEmpty())
        {
            for (IModInitDispatch handler : this.handlers)
            {
                handler.setDedicatedServer(toggle);
            }
        }
    }

    public void setOpenToLan(boolean toggle)
    {
        if (!this.handlers.isEmpty())
        {
            for (IModInitDispatch handler : this.handlers)
            {
                handler.setOpenToLan(toggle);
            }
        }
    }

    public void reset()
    {
        if (!this.handlers.isEmpty())
        {
            for (IModInitDispatch handler : this.handlers)
            {
                handler.reset();
            }
        }
    }
}
