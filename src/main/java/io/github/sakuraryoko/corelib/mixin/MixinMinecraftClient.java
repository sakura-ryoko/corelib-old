package io.github.sakuraryoko.corelib.mixin;

import io.github.sakuraryoko.corelib.events.ClientEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method ="joinWorld", at = @At("HEAD"))
    private void onJoinWorldPre(ClientWorld world, CallbackInfo ci)
    {
        ClientEvents.joining();
    }
    @Inject(method ="joinWorld", at = @At("TAIL"))
    private void onJoinWorldPost(ClientWorld world, CallbackInfo ci)
    {
        ClientEvents.joined();
    }
    @Inject(method = "disconnect()V", at = @At("HEAD"))
    private void onDisconnectPre(CallbackInfo ci)
    {
        ClientEvents.disconnecting();
    }
    @Inject(method = "onDisconnected", at = @At("HEAD"))
    private void onDisconnectPost(CallbackInfo ci)
    {
        ClientEvents.disconnected();
    }
}
