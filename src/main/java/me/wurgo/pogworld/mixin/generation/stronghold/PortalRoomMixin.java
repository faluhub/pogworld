package me.wurgo.pogworld.mixin.generation.stronghold;

import me.wurgo.pogworld.PogWorld;
import net.minecraft.block.*;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(StrongholdGenerator.PortalRoom.class)
public abstract class PortalRoomMixin extends StrongholdGenerator.Piece {
    protected PortalRoomMixin(StructurePieceType structurePieceType, int i) {
        super(structurePieceType, i);
    }

    @Inject(method = "generate", at = @At("TAIL"))
    private void pogworld_inject_pr_generate_tail(IWorld serverWorldAccess, ChunkGenerator<?> generator, Random random, BlockBox box, ChunkPos pos, CallbackInfoReturnable<Boolean> cir) {
        boolean[] bls = PogWorld.calculateEyes(serverWorldAccess.getSeed());

        BlockState blockState4 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
        BlockState blockState5 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
        BlockState blockState6 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
        BlockState blockState7 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);

        this.addBlock(serverWorldAccess, blockState4.with(EndPortalFrameBlock.EYE, bls[0]), 4, 3, 8, boundingBox);
        this.addBlock(serverWorldAccess, blockState4.with(EndPortalFrameBlock.EYE, bls[1]), 5, 3, 8, boundingBox);
        this.addBlock(serverWorldAccess, blockState4.with(EndPortalFrameBlock.EYE, bls[2]), 6, 3, 8, boundingBox);
        this.addBlock(serverWorldAccess, blockState5.with(EndPortalFrameBlock.EYE, bls[3]), 4, 3, 12, boundingBox);
        this.addBlock(serverWorldAccess, blockState5.with(EndPortalFrameBlock.EYE, bls[4]), 5, 3, 12, boundingBox);
        this.addBlock(serverWorldAccess, blockState5.with(EndPortalFrameBlock.EYE, bls[5]), 6, 3, 12, boundingBox);
        this.addBlock(serverWorldAccess, blockState6.with(EndPortalFrameBlock.EYE, bls[6]), 3, 3, 9, boundingBox);
        this.addBlock(serverWorldAccess, blockState6.with(EndPortalFrameBlock.EYE, bls[7]), 3, 3, 10, boundingBox);
        this.addBlock(serverWorldAccess, blockState6.with(EndPortalFrameBlock.EYE, bls[8]), 3, 3, 11, boundingBox);
        this.addBlock(serverWorldAccess, blockState7.with(EndPortalFrameBlock.EYE, bls[9]), 7, 3, 9, boundingBox);
        this.addBlock(serverWorldAccess, blockState7.with(EndPortalFrameBlock.EYE, bls[10]), 7, 3, 10, boundingBox);
        this.addBlock(serverWorldAccess, blockState7.with(EndPortalFrameBlock.EYE, bls[11]), 7, 3, 11, boundingBox);

        if (PogWorld.isTwelveEye(bls)) {
            BlockState blockState8 = Blocks.END_PORTAL.getDefaultState();
            this.addBlock(serverWorldAccess, blockState8, 4, 3, 9, boundingBox);
            this.addBlock(serverWorldAccess, blockState8, 5, 3, 9, boundingBox);
            this.addBlock(serverWorldAccess, blockState8, 6, 3, 9, boundingBox);
            this.addBlock(serverWorldAccess, blockState8, 4, 3, 10, boundingBox);
            this.addBlock(serverWorldAccess, blockState8, 5, 3, 10, boundingBox);
            this.addBlock(serverWorldAccess, blockState8, 6, 3, 10, boundingBox);
            this.addBlock(serverWorldAccess, blockState8, 4, 3, 11, boundingBox);
            this.addBlock(serverWorldAccess, blockState8, 5, 3, 11, boundingBox);
            this.addBlock(serverWorldAccess, blockState8, 6, 3, 11, boundingBox);
        }
    }
}
