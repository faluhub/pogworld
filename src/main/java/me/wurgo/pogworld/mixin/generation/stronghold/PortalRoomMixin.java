package me.wurgo.pogworld.mixin.generation.stronghold;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StrongholdGenerator.PortalRoom.class)
public class PortalRoomMixin {
    @ModifyConstant(method = "generate", constant = @Constant(floatValue = 0.9F))
    private float pogworld_modconst_pr_generate_floatValue(float oldValue) {
        return .7F;
    }
}
