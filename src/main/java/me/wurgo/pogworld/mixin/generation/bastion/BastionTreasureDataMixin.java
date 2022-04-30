package me.wurgo.pogworld.mixin.generation.bastion;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.structure.BastionData;
import net.minecraft.structure.BastionTreasureData;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.AxisAlignedLinearPosRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BastionTreasureData.class)
public class BastionTreasureDataMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void pogworld_inject_btd_static_tail(CallbackInfo ci) {
        ImmutableList<StructureProcessor> immutableList2 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.35F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CHISELED_POLISHED_BLACKSTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()), BastionData.PROCESSOR_RULE)));
        ImmutableList<StructureProcessor> immutableList4 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GOLD_BLOCK, 0.0F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()), new StructureProcessorRule(AlwaysTrueRuleTest.INSTANCE, AlwaysTrueRuleTest.INSTANCE, new AxisAlignedLinearPosRuleTest(0.0F, 0.05F, 0, 100, Direction.Axis.Y), Blocks.AIR.getDefaultState()))));
        ImmutableList<StructureProcessor> immutableList5 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MAGMA_BLOCK, 0.75F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.POLISHED_BLACKSTONE_BRICKS.getDefaultState()), BastionData.PROCESSOR_RULE)));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("bastion/treasure/bases/centers"), new Identifier("empty"), ImmutableList.of(Pair.of(new SinglePoolElement("bastion/treasure/bases/centers/center_0", immutableList2), 0), Pair.of(new SinglePoolElement("bastion/treasure/bases/centers/center_1", immutableList2), 1), Pair.of(new SinglePoolElement("bastion/treasure/bases/centers/center_2", immutableList2), 0), Pair.of(new SinglePoolElement("bastion/treasure/bases/centers/center_3", immutableList2), 0)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("bastion/treasure/ramparts"), new Identifier("empty"), ImmutableList.of(Pair.of(new SinglePoolElement("bastion/treasure/ramparts/mid_wall_main", immutableList2), 1), Pair.of(new SinglePoolElement("bastion/treasure/ramparts/mid_wall_side", immutableList2), 1), Pair.of(new SinglePoolElement("bastion/treasure/ramparts/bottom_wall_0", immutableList5), 1), Pair.of(new SinglePoolElement("bastion/treasure/ramparts/top_wall", immutableList4), 1), Pair.of(new SinglePoolElement("bastion/treasure/ramparts/lava_basin_side", immutableList2), 1), Pair.of(new SinglePoolElement("bastion/treasure/ramparts/lava_basin_main", immutableList2), 1)), StructurePool.Projection.RIGID));
    }
}
