package me.wurgo.pogworld.mixin.generation.stronghold;

import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StrongholdFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(StrongholdFeature.Start.class)
public abstract class StrongholdFeatureStartMixin extends StructureStart {
    public StrongholdFeatureStartMixin(StructureFeature<?> feature, int chunkX, int chunkZ, BlockBox box, int references, long l) {
        super(feature, chunkX, chunkZ, box, references, l);
    }

    @Inject(method = "initialize", at = @At("TAIL"))
    private void pogworld_inject_sfs_initialise_tail(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z, Biome biome, CallbackInfo ci) {
        int i = 0;
        long l = chunkGenerator.getSeed();

        StrongholdGenerator.Start start;
        do {
            this.children.clear();
            this.boundingBox = BlockBox.empty();
            this.random.setStructureSeed(l + (long)(i++), x, z);
            StrongholdGenerator.method_14855();
            start = new StrongholdGenerator.Start(this.random, (x << 4) + 2, (z << 4) + 2);
            this.children.add(start);
            start.method_14918(start, this.children, this.random);
            List list = start.field_15282;

            while(!list.isEmpty()) {
                int j = this.random.nextInt(list.size());
                StructurePiece structurePiece = (StructurePiece)list.remove(j);
                structurePiece.method_14918(start, this.children, this.random);
            }

            this.setBoundingBoxFromChildren();
            this.setStrongholdHeight(chunkGenerator.getSeaLevel(), this.random, chunkGenerator);
        } while(this.children.isEmpty() || start.field_15283 == null);
    }

    protected void setStrongholdHeight(int i, Random random, ChunkGenerator chunkGenerator) {
        int k = i - 10;
        int l = this.boundingBox.getBlockCountY() + 1;
        if (l < k) {
            l += random.nextInt(k - l);
        }

        int m = l - this.boundingBox.maxY;
        this.boundingBox.offset(0, m, 0);

        int randomInt = this.random.nextInt(15);

        for (StructurePiece structurePiece : this.children) {
            structurePiece.translate(0, -(chunkGenerator.getSeaLevel() / 2 + randomInt), 0);
        }
    }
}
