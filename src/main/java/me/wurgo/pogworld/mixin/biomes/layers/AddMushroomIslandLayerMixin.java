package me.wurgo.pogworld.mixin.biomes.layers;

import me.wurgo.pogworld.BiomeUtils;
import net.minecraft.world.biome.layer.AddMushroomIslandLayer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AddMushroomIslandLayer.class)
public class AddMushroomIslandLayerMixin {
    @Shadow @Final private static int MUSHROOM_FIELDS_ID;

    @Inject(method = "sample", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_amil_sample_return(LayerRandomnessSource context, int sw, int se, int ne, int nw, int center, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(BiomeUtils.isOcean(center) && context.nextInt(20) == 0 ? MUSHROOM_FIELDS_ID : center);
    }
}
