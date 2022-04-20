package me.wurgo.pogworld.mixin;

import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BiomeSource.class)
public class BiomeSourceMixin {
    @Mutable
    @Shadow @Final private static List<Biome> SPAWN_BIOMES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void pogworld_inject_bs_static_tail(CallbackInfo ci) {
        SPAWN_BIOMES = Lists.newArrayList(Biomes.PLAINS, Biomes.TAIGA, Biomes.DESERT, Biomes.BEACH);
    }
}
