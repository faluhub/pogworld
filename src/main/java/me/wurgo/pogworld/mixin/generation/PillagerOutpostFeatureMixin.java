package me.wurgo.pogworld.mixin.generation;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.PillagerOutpostFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;
import java.util.function.Function;

@Mixin(PillagerOutpostFeature.class)
public abstract class PillagerOutpostFeatureMixin extends AbstractTempleFeature<DefaultFeatureConfig> {
    public PillagerOutpostFeatureMixin(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configFactory) {
        super(configFactory);
    }

    @Inject(method = "shouldStartAt", at = @At("HEAD"), cancellable = true)
    private void pogworld_inject_pof_shouldStartAt_head(BiomeAccess biomeAccess, ChunkGenerator<?> chunkGenerator, Random random, int chunkZ, int i, Biome biome, CallbackInfoReturnable<Boolean> cir) {
        cir.cancel();

        boolean value = true;

        ChunkPos chunkPos = this.getStart(chunkGenerator, random, chunkZ, i, 0, 0);
        if (chunkZ == chunkPos.x && i == chunkPos.z) {
            int j = chunkZ >> 4;
            int k = i >> 4;

            random.setSeed((long)(j ^ k << 4) ^ chunkGenerator.getSeed());
            random.nextInt();

            if (random.nextInt(7) != 0 || !chunkGenerator.hasStructure(biome, this)) {
                value = false;
            }
        } else {
            value = false;
        }

        cir.setReturnValue(value);
    }
}
