package com.headfishindustries.fancylamps.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ArcaneLamp extends AbstractBlockGem{
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		return new AxisAlignedBB(0.125f, 0f, 0.125f, 0.875f, 1f, 0.875f);
    }


}
