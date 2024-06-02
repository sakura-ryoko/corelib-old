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
    IConfigData defaults();
    IConfigData update(IConfigData data);
    void execute();
}
