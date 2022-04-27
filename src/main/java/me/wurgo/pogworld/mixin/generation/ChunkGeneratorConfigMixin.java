package me.wurgo.pogworld.mixin.generation;

import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkGeneratorConfig.class)
public class ChunkGeneratorConfigMixin {
    @Shadow protected int villageDistance;
    @Mutable @Shadow @Final protected int villageSeparation;
    @Shadow protected int oceanMonumentSpacing;
    @Shadow protected int oceanMonumentSeparation;
    @Shadow protected int strongholdDistance;
    @Shadow protected int strongholdCount;
    @Shadow protected int strongholdSpread;
    @Shadow protected int templeDistance;
    @Mutable @Shadow @Final protected int templeSeparation;
    @Mutable @Shadow @Final protected int shipwreckSpacing;
    @Mutable @Shadow @Final protected int shipwreckSeparation;
    @Shadow protected int endCityDistance;
    @Mutable @Shadow @Final protected int endCitySeparation;
    @Shadow protected int mansionDistance;
    @Mutable @Shadow @Final protected int mansionSeparation;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void pogworld_inject_cgc_const_tail(CallbackInfo ci) {
        this.villageDistance /= 3; this.villageSeparation /= 3;
        this.oceanMonumentSpacing /= 3; this.oceanMonumentSeparation /= 2;
        this.templeDistance /= 3; this.templeSeparation /= 3;
        this.shipwreckSpacing /= 3; this.shipwreckSeparation /= 3;
        this.endCityDistance /= 3; this.endCitySeparation /= 3;
        this.mansionDistance /= 3; this.mansionSeparation /= 3;

        this.strongholdDistance = 25;
        this.strongholdCount = 320;
        this.strongholdSpread = 8;
    }

    @Inject(method = "getTempleSeparation", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_cgc_getTempleSeparation_return(CallbackInfoReturnable<Integer> cir)  {
        cir.setReturnValue(this.templeSeparation);
    }

    @Inject(method = "getShipwreckSpacing", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_cgc_getShipwreckSpacing_return(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.shipwreckSpacing);
    }

    @Inject(method = "getShipwreckSeparation", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_cgc_getShipwreckSeparation_return(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.shipwreckSeparation);
    }

    @Inject(method = "getEndCitySeparation", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_cgc_getEndCitySeparation_return(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.endCitySeparation);
    }

    @Inject(method = "getMansionSeparation", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_cgc_getMansionSeparation_return(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.mansionSeparation);
    }
}
