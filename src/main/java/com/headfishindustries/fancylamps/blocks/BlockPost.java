package com.headfishindustries.fancylamps.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockPost extends Block{

	public static final PropertyBool HAS_ABOVE = PropertyBool.create("up");
	public static final PropertyBool HAS_BELOW = PropertyBool.create("down");

	public BlockPost() {
		super(Material.ROCK, MapColor.QUARTZ);
		this.blockHardness = 0.2f;
		this.blockResistance = 8f;
		this.setHarvestLevel("pickaxe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(HAS_ABOVE, false).withProperty(HAS_BELOW, false));
		this.setLightOpacity(0);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, HAS_ABOVE, HAS_BELOW);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState s) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos.up()).getBlock() instanceof BlockPost)
			state = state.withProperty(HAS_ABOVE, true);
		else
			state = state.withProperty(HAS_ABOVE, false);
		
		
		if (worldIn.getBlockState(pos.down()).getBlock() instanceof BlockPost)
			state = state.withProperty(HAS_BELOW, true);
		else
			state = state.withProperty(HAS_BELOW, false);
		return state;		
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		return new AxisAlignedBB(0.4, 0, 0.4, 0.6, 1, 0.6);	
    }
}
