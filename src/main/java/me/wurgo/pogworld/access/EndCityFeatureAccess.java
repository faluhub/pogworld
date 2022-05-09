package me.wurgo.pogworld.access;

import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.EndCityFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EndCityFeature.class)
public interface EndCityFeatureAccess {
    @Invoker("getGenerationHeight")
    static int invokeGetGenerationHeight(int chunkX, int chunkZ, ChunkGenerator chunkGenerator) {
        return 0;
    }
}
