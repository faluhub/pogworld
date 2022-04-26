package me.wurgo.pogworld.mixin.generation;

import net.minecraft.world.gen.chunk.StrongholdConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StrongholdConfig.class)
public class StrongholdConfigMixin {
    @Mutable @Shadow @Final private int count;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void pogworld_inject_sc_const_tail(int distance, int spread, int count, CallbackInfo ci) {
        this.count = 320;
    }
}
