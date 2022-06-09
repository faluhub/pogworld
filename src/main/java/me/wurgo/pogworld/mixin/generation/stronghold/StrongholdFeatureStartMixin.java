package me.wurgo.pogworld.mixin.generation.stronghold;

import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StrongholdFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(StrongholdFeature.Start.class)
public abstract class StrongholdFeatureStartMixin extends StructureStart<DefaultFeatureConfig> {
    @Shadow @Final private long field_24559;

    public StrongholdFeatureStartMixin(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
        super(feature, chunkX, chunkZ, box, references, seed);
    }

    @Inject(method = "init(Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;IILnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/DefaultFeatureConfig;)V", at = @At("TAIL"))
    private void pogworld_inject_sfs_init_tail(ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, DefaultFeatureConfig defaultFeatureConfig, CallbackInfo ci) {
        int var7 = 0;

        StrongholdGenerator.Start start;
        do {
            this.children.clear();
            this.boundingBox = BlockBox.empty();
            this.random.setCarverSeed(this.field_24559 + (long)(var7++), i, j);
            StrongholdGenerator.init();
            start = new StrongholdGenerator.Start(this.random, (i << 4) + 2, (j << 4) + 2);
            this.children.add(start);
            start.placeJigsaw(start, this.children, this.random);
            List list = start.field_15282;

            while(!list.isEmpty()) {
                int l = this.random.nextInt(list.size());
                StructurePiece structurePiece = (StructurePiece)list.remove(l);
                structurePiece.placeJigsaw(start, this.children, this.random);
            }

            this.setBoundingBoxFromChildren();
            this.setStrongholdHeight(chunkGenerator.getSeaLevel(), this.random);
        } while(this.children.isEmpty() || start.field_15283 == null);
    }

    protected void setStrongholdHeight(int i, Random random) {
        int randomInt = random.nextInt(25);

        for (StructurePiece structurePiece : this.children) {
            structurePiece.translate(0, -(i / 2 + randomInt) + 10, 0);
        }
    }
}