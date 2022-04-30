package me.wurgo.pogworld.mixin.generation;

import net.minecraft.structure.IglooGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(IglooGenerator.class)
public class IglooGeneratorMixin {
    @Shadow @Final private static Identifier BOTTOM_TEMPLATE;
    @Shadow @Final private static Identifier MIDDLE_TEMPLATE;
    @Shadow @Final private static Identifier TOP_TEMPLATE;

    @Inject(method = "addPieces", at = @At("TAIL"))
    private static void pogworld_inject_ig_addPieces_tail(StructureManager manager, BlockPos pos, BlockRotation rotation, List<StructurePiece> pieces, Random random, DefaultFeatureConfig defaultConfig, CallbackInfo ci) {
        pieces.clear();

        int i = random.nextInt(8) + 4;
        pieces.add(new IglooGenerator.Piece(manager, BOTTOM_TEMPLATE, pos, rotation, i * 3));

        for(int j = 0; j < i - 1; ++j) {
            pieces.add(new IglooGenerator.Piece(manager, MIDDLE_TEMPLATE, pos, rotation, j * 3));
        }

        pieces.add(new IglooGenerator.Piece(manager, TOP_TEMPLATE, pos, rotation, 0));
    }
}
