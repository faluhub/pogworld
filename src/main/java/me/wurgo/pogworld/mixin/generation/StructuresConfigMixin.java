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

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Mixin(StructuresConfig.class)
public class StructuresConfigMixin {
    @Shadow @Final private Map<StructureFeature<?>, StructureConfig> structures;
    @Mutable @Shadow @Final public static StrongholdConfig DEFAULT_STRONGHOLD;
    @Shadow @Final public static ImmutableMap<StructureFeature<?>, StructureConfig> DEFAULT_STRUCTURES;

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void pogworld_inject_sc_const_tail(CallbackInfo info) {
        StructureFeature[] excluded = new StructureFeature[] {
                StructureFeature.STRONGHOLD,
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
                spacing = 2;
                separation = 1;
            }

            if (spacing <= 0) { spacing += -spacing + 1; }
            if (spacing <= separation) { spacing += (separation - spacing) + 1; }

            structures.put(feature, new StructureConfig(spacing + 1, separation, config.getSalt()));
        }
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void pogworld_inject_sc_static_tail(CallbackInfo ci) {
        Iterator var0 = Registry.STRUCTURE_FEATURE.iterator();

        StructureFeature structureFeature;
        do {
            if (!var0.hasNext()) {
                DEFAULT_STRONGHOLD = new StrongholdConfig(16, 3, 128);
                return;
            }

            structureFeature = (StructureFeature) var0.next();
        } while(DEFAULT_STRUCTURES.containsKey(structureFeature));
    }

    private int tryDivide(int integer) {
        try { return integer / 3; }
        catch (ArithmeticException e) { return 1; }
    }
}
