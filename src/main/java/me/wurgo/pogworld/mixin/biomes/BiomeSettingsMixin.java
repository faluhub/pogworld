package me.wurgo.pogworld.mixin.biomes;

import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.Settings.class)
public abstract class BiomeSettingsMixin {
    @Shadow private @Nullable String parent;

    @Inject(method = "parent", at = @At("TAIL"))
    private void pogworld_inject_bs_parent_tail(String parent, CallbackInfoReturnable<Biome.Settings> cir) {
        this.parent = null;
    }
}
