package io.github.sakuraryoko.corelib.mixin;

import io.github.sakuraryoko.corelib.util.CoreLog;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class MixinServerWorld
{
    @Shadow private int spawnChunkRadius;

    @Inject(method = "setSpawnPos", at = @At("TAIL"))
    private void checkSpawnPos(BlockPos pos, float angle, CallbackInfo ci)
    {
        // Decrement SPAWN_CHUNK_RADIUS by 1 here to get the real value.
        CoreLog.debug("MixinServerWorld#checkSpawnPos(): Spawn Position: "+pos.toShortString()+", SPAWN_CHUNK_RADIUS: "+ (this.spawnChunkRadius - 1));
    }
}
