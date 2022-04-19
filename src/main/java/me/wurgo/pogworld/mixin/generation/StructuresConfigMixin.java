package me.wurgo.pogworld.mixin.generation;

import me.wurgo.pogworld.PogWorld;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

@Mixin(StructuresConfig.class)
public class StructuresConfigMixin {
    @Shadow @Final private Map<StructureFeature<?>, StructureConfig> structures;
    private int spacing;
    private int separation;

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void pogworld_inject_sc_const_tail(CallbackInfo info) {
        StructureFeature[] excluded = new StructureFeature[] {
                StructureFeature.STRONGHOLD,
                StructureFeature.FORTRESS,
                StructureFeature.BASTION_REMNANT
        };

        for (StructureFeature<?> feature : structures.keySet()) {
            StructureConfig config = structures.get(feature);
            spacing = config.getSpacing();
            separation = config.getSeparation();

            if (Arrays.stream(excluded).noneMatch(structureFeature -> feature == structureFeature)) {
                Random random = new Random();
                spacing = tryDivide(config.getSpacing()) - random.nextInt(4);
                separation = tryDivide(config.getSeparation()) - random.nextInt(4);
            } else if (feature == StructureFeature.FORTRESS) {
                spacing = 11;
                separation = 2;
            } else if (feature == StructureFeature.BASTION_REMNANT) {
                spacing = 9;
                separation = 2;
            }

            if (spacing <= 0) { spacing += -spacing + 1; }
            if (spacing <= separation) { spacing += (separation - spacing) + 1; }

            PogWorld.log(feature.getName() + ": " + spacing);
            structures.put(feature, new StructureConfig(spacing, separation, config.getSalt()));
        }
    }

    private int tryDivide(int integer) {
        try { return integer / 3; }
        catch (ArithmeticException e) { return 1; }
    }
}
