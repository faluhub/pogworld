package me.wurgo.pogworld.mixin.generation;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.chunk.StrongholdConfig;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Mixin(StructuresConfig.class)
public class StructuresConfigMixin {
    @Shadow @Final private Map<StructureFeature<?>, StructureConfig> structures;

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void pogworld_inject_sc_const_tail(CallbackInfo info) {
        StructureFeature[] excluded = new StructureFeature[] {
                StructureFeature.STRONGHOLD, // this doesn't change anything I don't think
                StructureFeature.FORTRESS,
                StructureFeature.BASTION_REMNANT,
                StructureFeature.MINESHAFT,
                StructureFeature.MONUMENT,
                StructureFeature.BURIED_TREASURE,
                StructureFeature.NETHER_FOSSIL
        };

        for (StructureFeature<?> feature : structures.keySet()) {
            StructureConfig config = structures.get(feature);
            int spacing = config.getSpacing();
            int separation = config.getSeparation();

            if (Arrays.stream(excluded).noneMatch(structureFeature -> feature == structureFeature)) {
                spacing = tryDivide(config.getSpacing());
                separation = tryDivide(config.getSeparation());
            } else if (feature == StructureFeature.FORTRESS) {
                spacing = 10;
                separation = 2;
            } else if (feature == StructureFeature.BASTION_REMNANT) {
                spacing = 8;
                separation = 2;
            } else if (feature == StructureFeature.BURIED_TREASURE) {
                spacing = 1;
                separation = 0;
            }

            if (spacing <= 0) { spacing += -spacing + 1; }
            if (spacing <= separation) { spacing += (separation - spacing) + 1; }

            structures.put(feature, new StructureConfig(spacing + 1, separation, config.getSalt()));
        }
    }

    private int tryDivide(int integer) {
        try { return integer / 3; }
        catch (ArithmeticException e) { return 1; }
    }
}
