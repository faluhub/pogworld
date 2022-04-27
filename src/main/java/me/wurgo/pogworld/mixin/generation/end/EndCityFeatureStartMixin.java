package me.wurgo.pogworld.mixin.generation.end;

import me.wurgo.pogworld.mixin.access.EndCityFeatureAccess;
import me.wurgo.pogworld.mixin.access.EndCityGeneratorAccess;
import net.minecraft.structure.EndCityGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.EndCityFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndCityFeature.Start.class)
public abstract class EndCityFeatureStartMixin extends StructureStart {
    public EndCityFeatureStartMixin(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
        super(feature, chunkX, chunkZ, box, references, seed);
    }

    @Inject(method = "initialize", at = @At("TAIL"))
    private void pogworld_inject_ecfs_init_tail(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z, Biome biome, CallbackInfo ci) {
        int k = EndCityFeatureAccess.invokeGetGenerationHeight(x, z, chunkGenerator);
        BlockPos blockPos = new BlockPos(x * 16 + 8, k, z * 16 + 8);
        EndCityGeneratorAccess.invokeCreatePart(
                structureManager,
                EndCityGeneratorAccess.getBridgePiece(),
                this.children.get(this.children.size() - 1).getBoundingBox().maxY,
                (EndCityGenerator.Piece) this.children.get(this.children.size() - 1),
                blockPos,
                this.children,
                random
        );
        this.setBoundingBoxFromChildren();
    }
}
