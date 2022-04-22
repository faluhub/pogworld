package me.wurgo.pogworld.mixin.generation;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {
// this doesn't work
//    @Mutable @Shadow @Final private List<ChunkPos> field_24749;
//    private List<ChunkPos> done = new ArrayList<>();
//
//    @ModifyVariable(method = "method_28507", at = @At("HEAD"), ordinal = 0, argsOnly = true)
//    private ChunkPos pogworld_inject_cg_28507_tail(ChunkPos chunkPos) {
//        for (ChunkPos cp : this.field_24749) {
//            if (!done.contains(cp)) {
//                done.add(cp);
//                return cp;
//            }
//        }
//
//        return null;
//    }
// this doesn't work
//    @Inject(method = "method_28509", at = @At("TAIL"))
//    private void pogworld_inject_cg_28509_tail(CallbackInfo ci) {
//        List<ChunkPos> newList = new ArrayList<>();
//
//        for (ChunkPos cp : this.field_24749) {
//            newList.add(new ChunkPos(cp.x << 4, cp.z << 4));
//        }
//
//        this.field_24749.clear();
//        this.field_24749.addAll(newList);
//    }
}
