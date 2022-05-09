package me.wurgo.pogworld.mixin.generation.bastion;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.structure.BastionData;
import net.minecraft.structure.BastionUnitsData;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BastionUnitsData.class)
public class BastionUnitsDataMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void pogworld_inject_bud_static_tail(CallbackInfo ci) {
        ImmutableList<StructureProcessor> immutableList = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_BLACKSTONE_BRICKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 1.0E-4F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), BastionData.PROCESSOR_RULE)));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("bastion/units/ramparts"), new Identifier("empty"), ImmutableList.of(Pair.of(new SinglePoolElement("bastion/units/ramparts/ramparts_0", immutableList), 1), Pair.of(new SinglePoolElement("bastion/units/ramparts/ramparts_1", immutableList), 0), Pair.of(new SinglePoolElement("bastion/units/ramparts/ramparts_2", immutableList), 0)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("bastion/units/large_ramparts"), new Identifier("empty"), ImmutableList.of(Pair.of(new SinglePoolElement("bastion/units/ramparts/ramparts_0", immutableList), 1)), StructurePool.Projection.RIGID));
    }
}