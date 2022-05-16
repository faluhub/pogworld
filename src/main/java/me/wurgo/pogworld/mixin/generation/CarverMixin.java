package me.wurgo.pogworld.mixin.generation;

import com.mojang.datafixers.Dynamic;
import me.wurgo.pogworld.access.CarverAccess;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

@Mixin(Carver.class)
public class CarverMixin {
    @Shadow @Final public static Carver<ProbabilityConfig> UNDERWATER_CANYON;
    @Shadow @Final private Function<Dynamic<?>, ? extends CarverConfig> configDeserializer;

    @Redirect(method = "carveRegion", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I", ordinal = 1))
    private int pogworld_redirect_c_carveRegion_max(int a, int b) {
        if (this.configDeserializer == ((CarverAccess) UNDERWATER_CANYON).getConfigDeserializer()) { return 4; }
        else { return Math.max(a, b); }
    }
}
