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
                ServerWorld serverWorld2 = this.server.getWorld(newDimension);
                int o = MathHelper.floor(this.getX());
                int p = MathHelper.floor(this.getY()) - 1;
                int q = MathHelper.floor(this.getZ());

                for(int t = -2; t <= 4; ++t) {
                    for(int u = -2; u <= 4; ++u) {
                        for(int v = -1; v < 15; ++v) {
                            int w = o + u;
                            int x = p + v;
                            int y = q - t;
                            boolean bl = v < 0;
                            serverWorld2.setBlockState(new BlockPos(w, x, y), bl ? Blocks.OBSIDIAN.getDefaultState() : Blocks.AIR.getDefaultState());
                        }
                    }
                }

                float h = this.yaw;
                this.refreshPositionAndAngles(o, p, q, h, 0.0F);
                this.setVelocity(Vec3d.ZERO);
            }
        }
    }
}
