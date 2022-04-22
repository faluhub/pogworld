package me.wurgo.pogworld.mixin.generation;

import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(StrongholdGenerator.class)
public class StrongholdGeneratorMixin {
    @Inject(method = "method_14847", at = @At("RETURN"), cancellable = true)
    private static void pogworld_inject_sg_14847_return(Class<? extends StrongholdGenerator.Piece> class_, List<StructurePiece> list, Random random, int i, int j, int k, @Nullable Direction direction, int l, CallbackInfoReturnable<StrongholdGenerator.Piece> cir) {
        int piece = random.nextInt(2);

        if (direction != null) {
            switch (piece) {
                case 0:
                    cir.setReturnValue(StrongholdGenerator.PortalRoom.method_14863(list, i, j, k, direction, l));
                    return;
                case 1:
                    cir.setReturnValue(StrongholdGenerator.Library.method_14860(list, random, i, j, k, direction, l));
                    return;
            }

            cir.setReturnValue(StrongholdGenerator.FiveWayCrossing.method_14858(list, random, i, j, k, direction, l));
        }
    }
}
