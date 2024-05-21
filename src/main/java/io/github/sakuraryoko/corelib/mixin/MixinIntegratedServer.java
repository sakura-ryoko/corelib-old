package io.github.sakuraryoko.corelib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.world.GameMode;
import io.github.sakuraryoko.corelib.events.ServerEvents;
import io.github.sakuraryoko.corelib.init.ModInitHandler;

@Mixin(IntegratedServer.class)
public class MixinIntegratedServer
{
    @Inject(method = "setupServer", at = @At("RETURN"))
    private void corelib$setupServer(CallbackInfoReturnable<Boolean> cir)
    {
        if (cir.getReturnValue())
        {
            ((ModInitHandler) ModInitHandler.getInstance()).setIntegratedServer(true);
            ServerEvents.integratedSetup();
        }
    }

    @Inject(method = "openToLan", at = @At("RETURN"))
    private void corelib$checkOpenToLan(GameMode gameMode, boolean cheatsAllowed, int port, CallbackInfoReturnable<Boolean> cir)
    {
        if (cir.getReturnValue())
        {
            ((ModInitHandler) ModInitHandler.getInstance()).setOpenToLan(true);
            ServerEvents.openToLan();
        }
    }
}
