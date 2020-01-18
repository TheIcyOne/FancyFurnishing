package com.headfishindustries.fancylamps.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockLantern114 extends AbstractBlockLamp{
	
	boolean isRedstoneLamp;

	public BlockLantern114(boolean isRedstone) {
		super(Material.IRON);
		this.isRedstoneLamp = isRedstone;
		this.setHardness(0.2f);
		this.setHarvestLevel("pickaxe", 0);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		return new AxisAlignedBB(0.3125f, 0f, 0.3125f, 0.6875f, 0.65f, 0.6875f);
    }
	
	@Override
	public boolean canProvidePower(IBlockState s) {
		return this.isRedstoneLamp;
	}
	
	@Override
	public int getStrongPower(IBlockState s, IBlockAccess w, BlockPos pos, EnumFacing side) {
		return this.isRedstoneLamp ? 15 : 0;
	}
	
	@Override
	public int getWeakPower(IBlockState s, IBlockAccess w, BlockPos pos, EnumFacing side) {
		return this.isRedstoneLamp ? 15 : 0;
	}

}
