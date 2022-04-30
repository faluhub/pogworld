package me.wurgo.pogworld.mixin.generation.end;

import me.wurgo.pogworld.PogWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndSpikeFeature.Spike.class)
public class EndSpikeFeatureSpikeMixin {
    @Mutable @Shadow @Final private int radius;
    @Mutable @Shadow @Final private int height;
    @Mutable @Shadow @Final private boolean guarded;
    @Mutable @Shadow @Final private Box boundingBox;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void pogworld_inject_s_const_tail(int centerX, int centerZ, int radius, int height, boolean bl, CallbackInfo ci) {
        if (PogWorld.lowTower) {
            this.height = 85;
            this.radius = 3;
        } else {
            this.height = 94;
            this.radius = 4;
        }
        this.guarded = false;
        this.boundingBox = new Box(centerX - radius, 0.0D, centerZ - radius, centerX + radius, 256.0D, centerZ + radius);

        PogWorld.lowTower = !PogWorld.lowTower;
    }
}
