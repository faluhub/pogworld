package me.wurgo.pogworld.mixin.generation;

import net.minecraft.block.BlockState;
import net.minecraft.structure.processor.BlockAgeStructureProcessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BlockAgeStructureProcessor.class)
public class BlockAgeStructureProcessorMixin {
    @Inject(method = "processObsidian", at = @At("RETURN"), cancellable = true)
    private void pogworld_inject_basp_processObsidian_return(Random random, CallbackInfoReturnable<BlockState> cir) {
        cir.setReturnValue(null);
    }
}
