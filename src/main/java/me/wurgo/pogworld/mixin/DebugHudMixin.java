package me.wurgo.pogworld.mixin;

import me.wurgo.pogworld.PogWorld;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DebugHud.class)
public class DebugHudMixin {
    @Inject(method = "getRightText", at = @At("RETURN"))
    private void pogworld_inject_dh_getRightText_return(CallbackInfoReturnable<List<String>> cir) {
        List<String> strings = cir.getReturnValue();

        strings.add("");
        strings.add("Using PogWorld v" + PogWorld.MOD_VERSION);
    }
}
