package me.wurgo.pogworld.mixin.generation;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import me.wurgo.pogworld.PogWorld;
import net.minecraft.block.Blocks;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.*;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import net.minecraft.world.gen.feature.VillageFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillageFeature.Start.class)
public abstract class VillageFeatureStartMixin extends VillageStructureStart<StructurePoolFeatureConfig> {
    public VillageFeatureStartMixin(StructureFeature<StructurePoolFeatureConfig> structureFeature, int i, int j, BlockBox blockBox, int k, long l) {
        super(structureFeature, i, j, blockBox, k, l);
    }

    @Inject(method = "init(Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;IILnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/StructurePoolFeatureConfig;)V", at = @At("TAIL"))
    private void pogworld_inject_vfs_init_tail(ChunkGenerator generator, StructureManager manager, int i, int j, Biome biome, StructurePoolFeatureConfig config, CallbackInfo ci) {
        BlockPos blockPos = new BlockPos(i * 16, 0, j * 16);
        String houseType = "plains";
        String blacksmith_name = "_weaponsmith_1";
        if (biome == Biomes.SNOWY_TUNDRA) {
            houseType = "snowy";
            blacksmith_name = "_weapon_smith_1";
        }
        else if (biome == Biomes.SAVANNA) {
            houseType = "savanna";
            blacksmith_name = "_weaponsmith_2";
        }
        else if (biome == Biomes.DESERT) { houseType = "desert"; }
        else if (biome == Biomes.TAIGA) { houseType = "taiga"; }

        ImmutableList<StructureProcessor> immutableList2 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))));
        StructurePool blacksmithPool = new StructurePool(
                new Identifier("village/" + houseType + "/blacksmith"),
                new Identifier("village/" + houseType + "/terminators"),
                ImmutableList.of(new Pair<>(new LegacySinglePoolElement("village/" + houseType + "/houses/" + houseType + blacksmith_name, immutableList2), 2)),
                StructurePool.Projection.RIGID
        );

        int amt = random.nextInt(4);
        if (amt == 0) { amt++; }

        StructurePoolBasedGenerator.REGISTRY.add(blacksmithPool);
        StructurePoolBasedGenerator.addPieces(blacksmithPool.getId(), amt, VillageGenerator.Piece::new, generator, manager, blockPos, this.children, this.random, true, true);
        this.setBoundingBoxFromChildren();
    }
}
