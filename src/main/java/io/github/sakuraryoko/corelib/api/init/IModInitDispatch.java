package io.github.sakuraryoko.corelib.api.init;

import java.util.List;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;

public interface IModInitDispatch
{
    ModInitData getModInit();
    String getModId();
    List<String> getBasic(List<String> elements);
    List<Text> getFormatted(List<String> elements);
    boolean isDebug();
    boolean isWrapID();
    void onModInit();

    default FabricLoader getModInstance() { return getModInit().getModInstance(); }
    default boolean isClient() { return getModInit().isClient(); }
    default boolean isServer() { return getModInit().isServer(); }
    default boolean isIntegratedServer() { return getModInit().isIntegratedServer(); }
    default boolean isDedicatedServer() { return getModInit().isDedicatedServer(); }
    default boolean isOpenToLan() { return getModInit().isOpenToLan(); }
    default void setIntegratedServer(boolean toggle) { getModInit().setIntegratedServer(toggle); }
    default void setDedicatedServer(boolean toggle) { getModInit().setDedicatedServer(toggle); }
    default void setOpenToLan(boolean toggle) { getModInit().setOpenToLan(toggle); }
    default void reset() { getModInit().reset(); }
    default String getMcVersion() { return getModInit().getMCVersion(); }
    default String getModVersion() { return getModInit().getModVersion(); }
    default String getModDesc() { return getModInit().getModDesc(); }
    default String getModAuthors() { return getModInit().getModAuthor$String(); }
    default String getModSources() { return getModInit().getModSources(); }
    default String getModHomepage() { return getModInit().getModHomepage(); }
}
