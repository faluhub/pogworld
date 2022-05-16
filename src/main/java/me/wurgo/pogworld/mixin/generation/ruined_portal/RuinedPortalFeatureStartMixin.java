package me.wurgo.pogworld.mixin.generation.ruined_portal;

import net.minecraft.structure.RuinedPortalStructurePiece;
import net.minecraft.world.gen.feature.RuinedPortalFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(RuinedPortalFeature.Start.class)
public class RuinedPortalFeatureStartMixin {
    @ModifyVariable(method = "init(Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;IILnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/RuinedPortalFeatureConfig;)V", at = @At("STORE"))
    private RuinedPortalStructurePiece.VerticalPlacement pogworld_modvar_rpf_verticalPlacement6(RuinedPortalStructurePiece.VerticalPlacement verticalPlacement6) {
        return RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
    }
}
