package me.wurgo.pogworld.mixin.generation.end;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    @Shadow @Final public MinecraftServer server;

    public ServerPlayerEntityMixin(World world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "changeDimension", at = @At("TAIL"))
    private void pogworld_inject_spe_changeDimension_tail(DimensionType newDimension, CallbackInfoReturnable<Entity> cir) {
        DimensionType dimensionType = this.dimension;
        if (!(dimensionType == DimensionType.THE_END && newDimension == DimensionType.OVERWORLD)) {
            if (newDimension == DimensionType.THE_END) {
                int i = MathHelper.floor(this.getX());
                int j = MathHelper.floor(this.getY()) - 1;
                int k = MathHelper.floor(this.getZ());

                BlockPos.iterate(i - 4, j + 1, k - 4, i + 4, j + 15, k + 4).forEach((blockPosx) -> {
                    world.setBlockState(blockPosx, Blocks.AIR.getDefaultState());
                });

                float h = this.yaw;
                this.refreshPositionAndAngles(i, j, k, h, 0.0F);
                this.setVelocity(Vec3d.ZERO);
            }
        }
    }
}
