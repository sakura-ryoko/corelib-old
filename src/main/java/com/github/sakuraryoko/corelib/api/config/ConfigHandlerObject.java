package com.github.sakuraryoko.corelib.api.config;

public class ConfigHandlerObject
{
    private final IConfigDispatch handler;
    private final IConfigData type;
    private final String modid;
    private final String configFile;
    private final boolean rootDir;

    public ConfigHandlerObject(IConfigDispatch handler, IConfigData data, String modid)
    {
        this.handler = handler;
        this.modid = modid;
        this.configFile = modid;
        this.rootDir = false;
        this.type = data;
    }

    public ConfigHandlerObject(IConfigDispatch handler, IConfigData data, String modid, boolean rootDir)
    {
        this.handler = handler;
        this.modid = modid;
        this.configFile = modid;
        this.rootDir = rootDir;
        this.type = data;
    }

    public ConfigHandlerObject(IConfigDispatch handler, IConfigData data, String modid, String file)
    {
        this.handler = handler;
        this.modid = modid;
        this.configFile = file;
        this.rootDir = false;
        this.type = data;
    }

    public ConfigHandlerObject(IConfigDispatch handler, IConfigData data, String modid, String file, boolean rootDir)
    {
        this.handler = handler;
        this.modid = modid;
        this.configFile = file;
        this.rootDir = rootDir;
        this.type = data;
    }

    public IConfigDispatch getHandler() { return this.handler; }

    public IConfigData getDataType() { return this.type; }

    public String getModid() { return this.modid; }

    public String getConfigFile() { return this.configFile; }

    public boolean getRoorDir() { return this.rootDir; }
}
