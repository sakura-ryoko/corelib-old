package com.github.sakuraryoko.corelib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import com.github.sakuraryoko.corelib.api.events.PlayerEvents;

@Mixin(PlayerManager.class)
public abstract class MixinPlayerManager
{
    public MixinPlayerManager() { super(); }

    @Inject(method = "onPlayerConnect", at = @At("TAIL"))
    private void eventOnPlayerJoin(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData clientData, CallbackInfo ci)
    {
        PlayerEvents.onPlayerJoin(player);
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void eventOnPlayerLeave(ServerPlayerEntity player, CallbackInfo ci)
    {
        PlayerEvents.onPlayerLeave(player);
    }
}
