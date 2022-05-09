package me.wurgo.pogworld.mixin.generation.stronghold;

import me.wurgo.pogworld.access.StrongholdGeneratorAccess;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePiece;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(StrongholdGenerator.FiveWayCrossing.class)
public class FiveWayCrossingMixin {
    @Mutable @Shadow @Final private boolean upperRightExists;
    @Mutable @Shadow @Final private boolean upperLeftExists;
    @Mutable @Shadow @Final private boolean lowerRightExists;
    @Mutable @Shadow @Final private boolean lowerLeftExists;

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void pogworld_inject_fwc_const_tail(CallbackInfo ci) {
        this.upperRightExists = false;
        this.upperLeftExists = true;
        this.lowerRightExists = true;
        this.lowerLeftExists = false;
    }

    @Inject(method = "method_14918", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/StrongholdGenerator$FiveWayCrossing;method_14874(Lnet/minecraft/structure/StrongholdGenerator$Start;Ljava/util/List;Ljava/util/Random;II)Lnet/minecraft/structure/StructurePiece;"))
    private void pogworld_inject_fwc_placeJigsaw_14874(StructurePiece structurePiece, List<StructurePiece> list, Random random, CallbackInfo ci) {
        StrongholdGeneratorAccess.setActivePieceType(StrongholdGenerator.PortalRoom.class);
    }

    @Inject(method = "method_14918", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/StrongholdGenerator$FiveWayCrossing;method_14870(Lnet/minecraft/structure/StrongholdGenerator$Start;Ljava/util/List;Ljava/util/Random;II)Lnet/minecraft/structure/StructurePiece;"))
    private void pogworld_inject_fwc_placeJigsaw_14870(StructurePiece structurePiece, List<StructurePiece> list, Random random, CallbackInfo ci) {
        StrongholdGeneratorAccess.setActivePieceType(StrongholdGenerator.Library.class);
    }

    @Inject(method = "method_14918", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/StrongholdGenerator$FiveWayCrossing;method_14873(Lnet/minecraft/structure/StrongholdGenerator$Start;Ljava/util/List;Ljava/util/Random;II)Lnet/minecraft/structure/StructurePiece;"))
    private void pogworld_inject_fwc_placeJigsaw_14873(StructurePiece structurePiece, List<StructurePiece> list, Random random, CallbackInfo ci) {
        StrongholdGeneratorAccess.setActivePieceType(StrongholdGenerator.Library.class);
    }
}
