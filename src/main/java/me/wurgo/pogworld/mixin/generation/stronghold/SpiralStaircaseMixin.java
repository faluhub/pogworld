package me.wurgo.pogworld.mixin.generation.stronghold;

import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(StrongholdGenerator.SpiralStaircase.class)
public abstract class SpiralStaircaseMixin extends StrongholdGenerator.Piece {
    protected SpiralStaircaseMixin(StructurePieceType structurePieceType, int i) {
        super(structurePieceType, i);
    }

    @Inject(method = "<init>(Lnet/minecraft/structure/StructurePieceType;ILjava/util/Random;II)V", at = @At("TAIL"))
    private void pogworld_inject_ss_const_tail(StructurePieceType structurePieceType, int i, Random random, int j, int k, CallbackInfo ci) {
        this.setOrientation(Direction.EAST);
    }
}
