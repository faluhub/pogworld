package me.wurgo.pogworld.mixin;

import me.wurgo.pogworld.PogWorld;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin {
    @Inject(method = "createLevel", at = @At("TAIL"))
    private void pogworld_inject_cws_createLevel_tail(CallbackInfo ci) {
        PogWorld.eyesSeed.clear();
    }
}
