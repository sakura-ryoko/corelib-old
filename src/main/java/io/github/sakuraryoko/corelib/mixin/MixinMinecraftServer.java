package io.github.sakuraryoko.corelib.mixin;

import io.github.sakuraryoko.corelib.util.CoreLog;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer
{
    @Shadow public abstract GameRules getGameRules();

    @Shadow public abstract ServerWorld getOverworld();

    @Inject(method = "prepareStartRegion", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setSpawnPos(Lnet/minecraft/util/math/BlockPos;F)V", shift = At.Shift.AFTER))
    private void checkSpawnChunkRadius(WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo ci)
    {
        CoreLog.debug("MixinMinecraftServer#checkSpawnChunkRadius(): Spawn Position: "+this.getOverworld().getSpawnPos().toShortString()+" SPAWN_CHUNK_RADIUS: " + this.getGameRules().getInt(GameRules.SPAWN_CHUNK_RADIUS));
    }
}
