package io.github.sakuraryoko.corelib.mixin;

import io.github.sakuraryoko.corelib.events.ClientEvents;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {
    @Shadow @Nullable public abstract IntegratedServer getServer();

    @Inject(method ="joinWorld", at = @At("HEAD"))
    private void onJoinWorldPre(ClientWorld world, CallbackInfo ci)
    {
        ClientEvents.joining();
    }
    @Inject(method ="joinWorld", at = @At("TAIL"))
    private void onJoinWorldPost(ClientWorld world, CallbackInfo ci)
    {
        try {
            CoreLog.debug("MixinMinecraftClient#onJoinWorldPost(): Spawn Position: " + Objects.requireNonNull(this.getServer()).getOverworld().getSpawnPos().toShortString() + ", SPAWN_CHUNK_RADIUS: " + world.getGameRules().getInt(GameRules.SPAWN_CHUNK_RADIUS));
        } catch (Exception ignored) {
            CoreLog.debug("MixinMinecraftClient#onJoinWorldPost(): (Null Exception caught attempting to get Spawn Position), SPAWN_CHUNK_RADIUS: " + world.getGameRules().getInt(GameRules.SPAWN_CHUNK_RADIUS));
        }
        ClientEvents.joined();
    }
    @Inject(method = "disconnect()V", at = @At("HEAD"))
    private void onDisconnectPre(CallbackInfo ci)
    {
        try {
            CoreLog.debug("MixinMinecraftClient#onJoinWorldPost(): SPAWN_CHUNK_RADIUS: " + Objects.requireNonNull(this.getServer()).getGameRules().getInt(GameRules.SPAWN_CHUNK_RADIUS));
        }
        catch (Exception ignored) {
            CoreLog.debug("MixinMinecraftClient#onJoinWorldPost(): Null Exception caught attempting to get SPAWN_CHUNK_RADIUS.");
        }
        ClientEvents.disconnecting();
    }
    @Inject(method = "onDisconnected", at = @At("HEAD"))
    private void onDisconnectPost(CallbackInfo ci)
    {
        ClientEvents.disconnected();
    }
}
