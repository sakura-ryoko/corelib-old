package com.github.sakuraryoko.corelib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import com.github.sakuraryoko.corelib.api.events.ClientEvents;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler
{
    @Inject(method = "onGameJoin", at = @At("HEAD"))
    private void corelib$onGameJoinPre(GameJoinS2CPacket packet, CallbackInfo ci)
    {
        ClientEvents.onGameJoinPre();
    }

    @Inject(method = "onGameJoin", at = @At("TAIL"))
    private void corelib$onGameJoinPost(GameJoinS2CPacket packet, CallbackInfo ci)
    {
        ClientEvents.onGameJoinPost();
    }
}
