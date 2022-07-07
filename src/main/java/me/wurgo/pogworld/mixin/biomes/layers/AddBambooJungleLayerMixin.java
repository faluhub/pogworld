package me.wurgo.pogworld.mixin.biomes.layers;

import net.minecraft.world.biome.layer.AddBambooJungleLayer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AddBambooJungleLayer.class)
public class AddBambooJungleLayerMixin {
    @Shadow @Final private static int JUNGLE_ID;
    @Shadow @Final private static int BAMBOO_JUNGLE_ID;

    @Inject(method = "sample", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_abjl_sample_return(LayerRandomnessSource context, int se, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(context.nextInt(5) == 0 && se == JUNGLE_ID ? BAMBOO_JUNGLE_ID : se);
    }
}
