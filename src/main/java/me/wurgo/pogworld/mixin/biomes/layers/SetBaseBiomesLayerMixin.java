package me.wurgo.pogworld.mixin.biomes.layers;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.layer.SetBaseBiomesLayer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(SetBaseBiomesLayer.class)
public class SetBaseBiomesLayerMixin {
    @Shadow @Final private static int[] COOL_BIOMES;
    @Shadow @Final private static int GIANT_TREE_TAIGA_ID;
    @Shadow @Final private static int DESERT_ID;

    @Inject(method = "sample", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_sbbl_sample_return(LayerRandomnessSource context, int value, CallbackInfoReturnable<Integer> cir) {
        if (Arrays.stream(this.COOL_BIOMES).anyMatch(i -> i == cir.getReturnValue())) {
            if (context.nextInt(5) == 0) {
                cir.setReturnValue(GIANT_TREE_TAIGA_ID);
            }
        } else if (cir.getReturnValue() == DESERT_ID) {
            if (context.nextInt(7) == 0) {
                if (context.nextInt(1) == 0) {
                    cir.setReturnValue(Registry.BIOME.getRawId(Biomes.BADLANDS));
                } else {
                    if (context.nextInt(1) == 0) {
                        cir.setReturnValue(Registry.BIOME.getRawId(Biomes.BADLANDS_PLATEAU));
                    } else {
                        cir.setReturnValue(Registry.BIOME.getRawId(Biomes.WOODED_BADLANDS_PLATEAU));
                    }
                }
            }
        }
    }
}
