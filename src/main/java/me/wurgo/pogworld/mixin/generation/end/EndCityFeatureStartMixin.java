package me.wurgo.pogworld.mixin.generation.end;

import me.wurgo.pogworld.mixin.access.EndCityFeatureAccess;
import me.wurgo.pogworld.mixin.access.EndCityGeneratorAccess;
import net.minecraft.structure.EndCityGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
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
public abstract class EndCityFeatureStartMixin extends StructureStart<DefaultFeatureConfig> {
    public EndCityFeatureStartMixin(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
        super(feature, chunkX, chunkZ, box, references, seed);
    }

    @Inject(method = "init(Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;IILnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/DefaultFeatureConfig;)V", at = @At("TAIL"))
    private void pogworld_inject_ecfs_init_tail(ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, DefaultFeatureConfig defaultFeatureConfig, CallbackInfo ci) {
        int k = EndCityFeatureAccess.invokeGetGenerationHeight(i, j, chunkGenerator);
        BlockPos blockPos = new BlockPos(i * 16 + 8, k, j * 16 + 8);
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
