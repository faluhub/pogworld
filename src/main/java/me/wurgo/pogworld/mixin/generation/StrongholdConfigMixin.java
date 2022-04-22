package me.wurgo.pogworld.mixin.generation;

import net.minecraft.world.gen.chunk.StrongholdConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StrongholdConfig.class)
public class StrongholdConfigMixin {
    // this kinda works? I guess?
    @Mutable @Shadow @Final private int distance;
    @Mutable @Shadow @Final private int spread;
    @Mutable @Shadow @Final private int count;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void pogworld_inject_sc_const_tail(int distance, int spread, int count, CallbackInfo ci) {
        this.distance = 16;
        this.spread = 3;
        this.count = 128;
    }

    @Inject(method = "getDistance", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_sc_getDistance_return(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(16);
    }

    @Inject(method = "getSpread", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_sc_getSpread_return(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(3);
    }

    @Inject(method = "getCount", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_sc_getCount_return(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(128);
    }
}
