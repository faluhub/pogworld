package me.wurgo.pogworld.mixin.generation;

import net.minecraft.structure.ShipwreckGenerator;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShipwreckGenerator.class)
public class ShipwreckGeneratorMixin {
    @Mutable @Shadow @Final private static Identifier[] REGULAR_TEMPLATES;
    @Mutable @Shadow @Final private static Identifier[] BEACHED_TEMPLATES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void pogworld_inject_sg_static_tail(CallbackInfo ci) {
        BEACHED_TEMPLATES = REGULAR_TEMPLATES = new Identifier[]{new Identifier("shipwreck/with_mast")};
    }
}
