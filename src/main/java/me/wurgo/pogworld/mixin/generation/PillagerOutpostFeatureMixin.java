package me.wurgo.pogworld.mixin.generation;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.PillagerOutpostFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PillagerOutpostFeature.class)
public class PillagerOutpostFeatureMixin {
    @Inject(method = "shouldStartAt(Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/world/biome/source/BiomeSource;JLnet/minecraft/world/gen/ChunkRandom;IILnet/minecraft/world/biome/Biome;Lnet/minecraft/util/math/ChunkPos;Lnet/minecraft/world/gen/feature/DefaultFeatureConfig;)Z", at = @At("HEAD"), cancellable = true)
    private void pogworld_inject_pof_shouldStartAt_head(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long l, ChunkRandom chunkRandom, int i, int j, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig, CallbackInfoReturnable<Boolean> cir) {
        cir.cancel();

        int k = i >> 4;
        int m = j >> 4;

        chunkRandom.setSeed((long)(k ^ m << 4) ^ l);
        chunkRandom.nextInt();

        cir.setReturnValue(chunkRandom.nextInt(7) == 0);
    }
}
