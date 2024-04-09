package com.bconlon.vanillaequals.mixin.mixins.common;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class BlockStateBaseMixin {
    @Inject(at = @At("HEAD"), method = "getOffset(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/Vec3;", cancellable = true)
    private void getOffset(BlockGetter level, BlockPos pos, CallbackInfoReturnable<Vec3> cir) {
        BlockBehaviour.BlockStateBase blockStateBase = (BlockBehaviour.BlockStateBase) (Object) this;
        Block block = blockStateBase.getBlock();
        if (block instanceof TallGrassBlock) {
            long i = Mth.getSeed(pos.getX(), 0, pos.getZ());
            double d0 = ((double) ((float) (i >> 4 & 15L) / 15.0F) - 1.0) * (double) block.getMaxVerticalOffset();
            cir.setReturnValue(new Vec3(0.0, d0, 0.0));
        }
    }
}
