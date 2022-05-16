package me.wurgo.pogworld.access;

import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Function;

@Mixin(Carver.class)
public interface CarverAccess {
    @Accessor("configDeserializer")
    Function<Dynamic<?>, ? extends CarverConfig> getConfigDeserializer();
}
