package me.wurgo.pogworld.mixin.access;

import net.minecraft.structure.EndCityGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Random;

@Mixin(EndCityGenerator.class)
public interface EndCityGeneratorAccess {
    @Invoker("createPart")
    static boolean invokeCreatePart(StructureManager manager, EndCityGenerator.Part piece, int depth, EndCityGenerator.Piece parent, BlockPos pos, List<StructurePiece> pieces, Random random) {
        return true;
    }

    @Accessor("BRIDGE_PIECE")
    static EndCityGenerator.Part getBridgePiece() {
        return null;
    }
}
