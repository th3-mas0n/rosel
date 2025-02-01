
package com.thatoneaiguy.discharged.block;

import com.thatoneaiguy.discharged.init.DischargedBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import java.util.random.RandomGenerator;

public class RawRoselBlock extends Block {
    public RawRoselBlock(Settings settings) {
        super(settings);
    }


	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		super.scheduledTick(state, world, pos, (Random) random);

		if (world.getBlockState(pos.down()).getBlock() == Blocks.SOUL_CAMPFIRE) {
			world.setBlockState(pos, DischargedBlocks.ROUGH_ROSEL.getDefaultState());
		}
	}
}
