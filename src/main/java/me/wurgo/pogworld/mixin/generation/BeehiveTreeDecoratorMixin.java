package me.wurgo.pogworld.mixin.generation;

import net.minecraft.world.gen.decorator.BeehiveTreeDecorator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeehiveTreeDecorator.class)
public class BeehiveTreeDecoratorMixin {
    @Mutable @Shadow @Final private float chance;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void pogworld_inject_btd_const_tail(float f, CallbackInfo ci) {
        this.chance *= 10;
    }
}
