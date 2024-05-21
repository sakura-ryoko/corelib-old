package io.github.sakuraryoko.corelib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import io.github.sakuraryoko.corelib.events.ServerEvents;
import io.github.sakuraryoko.corelib.init.ModInitHandler;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer
{
    //@Shadow public abstract GameRules getGameRules();

    //@Shadow public abstract ServerWorld getOverworld();

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setupServer()Z"), method = "runServer")
    private void corelib$onServerStarting(CallbackInfo ci)
    {
        ServerEvents.starting();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;createMetadata()Lnet/minecraft/server/ServerMetadata;", ordinal = 0), method = "runServer")
    private void corelib$onServerStarted(CallbackInfo ci)
    {
        ServerEvents.started();
    }

    @Inject(at = @At("HEAD"), method = "shutdown")
    private void corelib$onServerStopping(CallbackInfo info)
    {
        ServerEvents.stopping();
    }

    @Inject(at = @At("TAIL"), method = "shutdown")
    private void corelib$onServerStopped(CallbackInfo info)
    {
        ServerEvents.stopped();
        ((ModInitHandler) ModInitHandler.getInstance()).reset();
    }

    @Inject(method = "prepareStartRegion", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setSpawnPos(Lnet/minecraft/util/math/BlockPos;F)V", shift = At.Shift.AFTER))
    private void corelib$prepareStartRegion(WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo ci)
    {
        //CoreLog.debug("MixinMinecraftServer#checkSpawnChunkRadius(): Spawn Position: "+this.getOverworld().getSpawnPos().toShortString()+" SPAWN_CHUNK_RADIUS: " + this.getGameRules().getInt(GameRules.SPAWN_CHUNK_RADIUS));
    }
}
