package me.wurgo.pogworld.mixin.biomes;

import net.minecraft.world.biome.BeachBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.BuriedTreasureFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeachBiome.class)
public abstract class BeachBiomeMixin extends Biome {
    protected BeachBiomeMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void pogworld_inject_bb_const_tail(CallbackInfo ci) {
        this.addStructureFeature(Feature.BURIED_TREASURE.configure(new BuriedTreasureFeatureConfig(.07F)));
    }
}
