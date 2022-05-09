package me.wurgo.pogworld.mixin.generation;

import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(StructuresConfig.class)
public class StructuresConfigMixin {
    @Shadow @Final private Map<StructureFeature<?>, StructureConfig> structures;

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void pogworld_inject_sc_const_tail(CallbackInfo info) {
        StructureFeature[] excluded = new StructureFeature[] {
                StructureFeature.STRONGHOLD,
                StructureFeature.FORTRESS,
                StructureFeature.BASTION_REMNANT,
                StructureFeature.MONUMENT,
                StructureFeature.BURIED_TREASURE,
                StructureFeature.NETHER_FOSSIL,
                StructureFeature.RUINED_PORTAL,
                StructureFeature.OCEAN_RUIN
        };

        for (StructureFeature<?> feature : structures.keySet()) {
            boolean add = true;
            StructureConfig config = structures.get(feature);
            int spacing = config.getSpacing();
            int separation = config.getSeparation();

            if (Arrays.stream(excluded).noneMatch(structureFeature -> feature == structureFeature)) {
                spacing = tryDivide(config.getSpacing());
                separation = tryDivide(config.getSeparation());
            } else if (feature == StructureFeature.FORTRESS) {
                spacing = 9;
                separation = 2;
            } else if (feature == StructureFeature.BASTION_REMNANT) {
                spacing = 7;
                separation = 2;
            } else if (feature == StructureFeature.RUINED_PORTAL) {
                spacing = 11;
                separation = 4;
                add = false;
            } else { add = false; }

            if (spacing <= 0) { spacing += -spacing + 1; }
            if (spacing <= separation) { spacing += (separation - spacing) + 1; }

            structures.put(feature, new StructureConfig(add ? spacing + 1 : spacing, separation, config.getSalt()));
        }
    }

    private int tryDivide(int integer) {
        try { return integer / 3; }
        catch (ArithmeticException e) { return 1; }
    }
}
