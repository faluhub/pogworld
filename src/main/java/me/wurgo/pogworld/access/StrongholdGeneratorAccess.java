package me.wurgo.pogworld.access;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StrongholdGenerator.class)
public interface StrongholdGeneratorAccess {
    @Accessor("field_15266")
    static void setActivePieceType(Class<? extends StrongholdGenerator.Piece> piece) {
        throw new AssertionError();
    }
}
