package me.wurgo.pogworld.mixin.biomes;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
    @Shadow @Final private static BlockState LAVA;
    @Shadow @Final private static BlockState WATER;
    @Shadow @Final private static BlockState GRAVEL;

    @Inject(method = "addOceanCarvers", at = @At("TAIL"))
    private static void pogworld_inject_dbf_addOceanCarvers_tail(Biome biome, CallbackInfo ci) {
        biome.addCarver(GenerationStep.Carver.LIQUID, Biome.configureCarver(Carver.UNDERWATER_CANYON, new ProbabilityConfig(.07F)));
    }

    @Inject(method = "addMineables", at = @At("TAIL"))
    private static void pogworld_inject_dbf_addMineables_tail(Biome biome, CallbackInfo ci) {
        biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, GRAVEL, 55)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(8, 0, 0, 256))));
    }

    @Inject(method = "addDefaultLakes", at = @At("HEAD"), cancellable = true)
    private static void pogworld_inject_dbf_addDefaultLakes_head(Biome biome, CallbackInfo ci) {
        ci.cancel();

        biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Feature.LAKE.configure(new SingleStateFeatureConfig(WATER)).createDecoratedFeature(Decorator.WATER_LAKE.configure(new ChanceDecoratorConfig(35))));
        biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Feature.LAKE.configure(new SingleStateFeatureConfig(LAVA)).createDecoratedFeature(Decorator.LAVA_LAKE.configure(new ChanceDecoratorConfig(20))));
    }
}
