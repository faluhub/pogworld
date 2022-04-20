package me.wurgo.pogworld.mixin.generation;

import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Shadow @Final public static BlockPos END_SPAWN_POS;

    @Inject(method = "createEndSpawnPlatform", at = @At("TAIL"))
    private static void pogworld_inject_sw_createEndSpawnPlatform_tail(ServerWorld world, CallbackInfo ci) {
        BlockPos blockPos = END_SPAWN_POS;
        int i = blockPos.getX();
        int j = blockPos.getY() - 2;
        int k = blockPos.getZ();
        BlockPos.iterate(i - 4, j + 1, k - 4, i + 4, j + 15, k + 4).forEach((blockPosx) -> {
            world.setBlockState(blockPosx, Blocks.AIR.getDefaultState());
        });
    }
}
