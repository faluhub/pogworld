package me.wurgo.pogworld.mixin.generation.village;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.structure.SnowyVillageData;
import net.minecraft.structure.pool.*;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowyVillageData.class)
public class SnowyVillageDataMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void pogworld_inject_svd_static_tail(CallbackInfo ci) {
        ImmutableList<StructureProcessor> immutableList = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), new StructureProcessorRule(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), new StructureProcessorRule(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), new StructureProcessorRule(new BlockMatchRuleTest(Blocks.LANTERN), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SPRUCE_PLANKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SPRUCE_SLAB, 0.4F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STRIPPED_SPRUCE_LOG, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STRIPPED_SPRUCE_WOOD, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new StructureProcessorRule(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)), new StructureProcessorRule(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()))));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/snowy/town_centers"), new Identifier("empty"), ImmutableList.of(new Pair(new LegacySinglePoolElement("village/snowy/town_centers/snowy_meeting_point_1"), 100), new Pair(new LegacySinglePoolElement("village/snowy/town_centers/snowy_meeting_point_2"), 50), new Pair(new LegacySinglePoolElement("village/snowy/town_centers/snowy_meeting_point_3"), 150), new Pair(new LegacySinglePoolElement("village/snowy/zombie/town_centers/snowy_meeting_point_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/zombie/town_centers/snowy_meeting_point_2"), 1), new Pair(new LegacySinglePoolElement("village/snowy/zombie/town_centers/snowy_meeting_point_3"), 3)), StructurePool.Projection.RIGID));
        ImmutableList<StructureProcessor> immutableList2 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRASS_PATH), new BlockMatchRuleTest(Blocks.WATER), Blocks.SPRUCE_PLANKS.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()), new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()), new StructureProcessorRule(new BlockMatchRuleTest(Blocks.DIRT), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()))));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/snowy/streets"), new Identifier("village/snowy/terminators"), ImmutableList.of(new Pair(new LegacySinglePoolElement("village/snowy/streets/corner_01", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/corner_02", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/corner_03", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/square_01", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_01", immutableList2), 4), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_02", immutableList2), 4), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_03", immutableList2), 4), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_04", immutableList2), 7), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_06", immutableList2), 4), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_08", immutableList2), 4), new Pair(new LegacySinglePoolElement("village/snowy/streets/crossroad_02", immutableList2), 1), new Pair(new LegacySinglePoolElement("village/snowy/streets/crossroad_03", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/crossroad_04", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/crossroad_05", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/crossroad_06", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/turn_01", immutableList2), 3)), StructurePool.Projection.TERRAIN_MATCHING));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/snowy/zombie/streets"), new Identifier("village/snowy/terminators"), ImmutableList.of(new Pair(new LegacySinglePoolElement("village/snowy/streets/corner_01", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/corner_02", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/corner_03", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/square_01", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_01", immutableList2), 4), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_02", immutableList2), 4), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_03", immutableList2), 4), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_04", immutableList2), 7), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_06", immutableList2), 4), new Pair(new LegacySinglePoolElement("village/snowy/streets/straight_08", immutableList2), 4), new Pair(new LegacySinglePoolElement("village/snowy/streets/crossroad_02", immutableList2), 1), new Pair(new LegacySinglePoolElement("village/snowy/streets/crossroad_03", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/crossroad_04", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/crossroad_05", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/crossroad_06", immutableList2), 2), new Pair(new LegacySinglePoolElement("village/snowy/streets/turn_01", immutableList2), 3)), StructurePool.Projection.TERRAIN_MATCHING));
        ImmutableList<StructureProcessor> immutableList3 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()))));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/snowy/houses"), new Identifier("village/snowy/terminators"), ImmutableList.of(new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_2"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_3"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_4"), 3), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_5"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_6"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_7"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_8"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_medium_house_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_medium_house_2"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_medium_house_3"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_butchers_shop_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_butchers_shop_2"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_tool_smith_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_fletcher_house_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_shepherds_house_1"), 3), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_armorer_house_1"), 1), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_armorer_house_2"), 1), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_fisher_cottage"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_tannery_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_cartographer_house_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_library_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_masons_house_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_masons_house_2"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_weapon_smith_1"), 500), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_temple_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_farm_1", immutableList3), 3), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_farm_2", immutableList3), 3), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_animal_pen_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_animal_pen_2"), 2), Pair.of(EmptyPoolElement.INSTANCE, 6)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/snowy/zombie/houses"), new Identifier("village/snowy/terminators"), ImmutableList.of(new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_2"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_3"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_4"), 3), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_5"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_6"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_7"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_small_house_8"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_medium_house_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_medium_house_2"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_medium_house_3"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_butchers_shop_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_butchers_shop_2"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_tool_smith_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_fletcher_house_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_shepherds_house_1"), 3), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_armorer_house_1"), 1), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_armorer_house_2"), 1), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_fisher_cottage"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_tannery_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_cartographer_house_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_library_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_masons_house_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_masons_house_2"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_weapon_smith_1"), 500), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_temple_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_farm_1", immutableList3), 3), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_farm_2", immutableList3), 3), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_animal_pen_1"), 2), new Pair(new LegacySinglePoolElement("village/snowy/houses/snowy_animal_pen_2"), 2), Pair.of(EmptyPoolElement.INSTANCE, 6)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/snowy/terminators"), new Identifier("empty"), ImmutableList.of(new Pair(new LegacySinglePoolElement("village/plains/terminators/terminator_01", immutableList2), 1), new Pair(new LegacySinglePoolElement("village/plains/terminators/terminator_02", immutableList2), 1), new Pair(new LegacySinglePoolElement("village/plains/terminators/terminator_03", immutableList2), 1), new Pair(new LegacySinglePoolElement("village/plains/terminators/terminator_04", immutableList2), 1)), StructurePool.Projection.TERRAIN_MATCHING));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/snowy/trees"), new Identifier("empty"), ImmutableList.of(new Pair(new FeaturePoolElement(Feature.TREE.configure(DefaultBiomeFeatures.SPRUCE_TREE_CONFIG)), 1)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/snowy/decor"), new Identifier("empty"), ImmutableList.of(new Pair(new LegacySinglePoolElement("village/snowy/snowy_lamp_post_01"), 4), new Pair(new LegacySinglePoolElement("village/snowy/snowy_lamp_post_02"), 4), new Pair(new LegacySinglePoolElement("village/snowy/snowy_lamp_post_03"), 1), new Pair(new FeaturePoolElement(Feature.TREE.configure(DefaultBiomeFeatures.SPRUCE_TREE_CONFIG)), 4), new Pair(new FeaturePoolElement(Feature.BLOCK_PILE.configure(DefaultBiomeFeatures.SNOW_PILE_CONFIG)), 4), new Pair(new FeaturePoolElement(Feature.BLOCK_PILE.configure(DefaultBiomeFeatures.BLUE_ICE_PILE_CONFIG)), 1), Pair.of(EmptyPoolElement.INSTANCE, 9)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/snowy/zombie/decor"), new Identifier("empty"), ImmutableList.of(new Pair(new LegacySinglePoolElement("village/snowy/snowy_lamp_post_01"), 4), new Pair(new LegacySinglePoolElement("village/snowy/snowy_lamp_post_02"), 4), new Pair(new LegacySinglePoolElement("village/snowy/snowy_lamp_post_03"), 1), new Pair(new FeaturePoolElement(Feature.TREE.configure(DefaultBiomeFeatures.SPRUCE_TREE_CONFIG)), 4), new Pair(new FeaturePoolElement(Feature.BLOCK_PILE.configure(DefaultBiomeFeatures.SNOW_PILE_CONFIG)), 4), new Pair(new FeaturePoolElement(Feature.BLOCK_PILE.configure(DefaultBiomeFeatures.BLUE_ICE_PILE_CONFIG)), 1), Pair.of(EmptyPoolElement.INSTANCE, 9)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/snowy/villagers"), new Identifier("empty"), ImmutableList.of(new Pair(new LegacySinglePoolElement("village/snowy/villagers/nitwit"), 1), new Pair(new LegacySinglePoolElement("village/snowy/villagers/baby"), 1), new Pair(new LegacySinglePoolElement("village/snowy/villagers/unemployed"), 10)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/snowy/zombie/villagers"), new Identifier("empty"), ImmutableList.of(new Pair(new LegacySinglePoolElement("village/snowy/villagers/nitwit"), 1), new Pair(new LegacySinglePoolElement("village/snowy/villagers/baby"), 1), new Pair(new LegacySinglePoolElement("village/snowy/villagers/unemployed"), 10)), StructurePool.Projection.RIGID));
    }
}