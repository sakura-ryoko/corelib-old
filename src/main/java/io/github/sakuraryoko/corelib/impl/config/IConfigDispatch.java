package io.github.sakuraryoko.corelib.impl.config;

public interface IConfigDispatch
{
    IConfigData newConfig();
    IConfigData getConfig();
    boolean isLoaded();
    void onPreLoadConfig();
    void onPostLoadConfig();
    void onPreSaveConfig();
    void onPostSaveConfig();
    void defaults(IConfigData data);
    void update(IConfigData data);
    void execute(IConfigData data);
}
