package com.headfishindustries.fancylamps.blocks;

import com.headfishindustries.fancylamps.EnumGemType;
import com.headfishindustries.fancylamps.FancyLamps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class AbstractObelisk extends AbstractBlockGem{
	
	abstract void breakOthers(World worldIn, BlockPos pos);
	public abstract AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos);
	
	@Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
		breakOthers(worldIn, pos);
    }
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(FancyLamps.DEFS.obeliskBase, 1, getMetaFromState(state));
	}
	
	public static class ObeliskBase extends AbstractObelisk{
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
			return new AxisAlignedBB(0.125f, 0f, 0.125f, 0.875f, 1f, 0.875f);
	    }
		
		public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	    {
			return(super.canPlaceBlockAt(worldIn, pos) && worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos)
					&& worldIn.getBlockState(pos.up()).getBlock().isReplaceable(worldIn, pos.up()) 
					&& worldIn.getBlockState(pos.up(2)).getBlock().isReplaceable(worldIn, pos.up(2)));
	    }

		 public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
		 {
			 super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
			 worldIn.setBlockState(pos.up(), FancyLamps.DEFS.obeliskStem.getDefaultState().withProperty(GEM_TYPE, (EnumGemType) worldIn.getBlockState(pos).getProperties().get(GEM_TYPE)));
			 worldIn.setBlockState(pos.up(2), FancyLamps.DEFS.obeliskTip.getDefaultState().withProperty(GEM_TYPE, (EnumGemType) worldIn.getBlockState(pos).getProperties().get(GEM_TYPE)));
		 }

		@Override
		void breakOthers(World worldIn, BlockPos pos) {
			if (worldIn.getBlockState(pos.up()).getBlock() instanceof AbstractObelisk) {
				worldIn.destroyBlock(pos.up(), true);
			}
			if (worldIn.getBlockState(pos.up(2)).getBlock() instanceof AbstractObelisk) {
				worldIn.destroyBlock(pos.up(2), true);
			}
		}
	
		
		
	}
	
	public static class ObeliskStem extends AbstractObelisk{

		@Override
		void breakOthers(World worldIn, BlockPos pos) {
			if (worldIn.getBlockState(pos.down()).getBlock() instanceof AbstractObelisk) {
				worldIn.destroyBlock(pos.down(), true);
			}
			if (worldIn.getBlockState(pos.up()).getBlock() instanceof AbstractObelisk) {
				worldIn.destroyBlock(pos.up(), true);
			}
		}

		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
			
			return new AxisAlignedBB(0.1875f, 0f, 0.1875f, 0.8125f, 1f, 0.8125f);
		}
		
	}
	
	public static class ObeliskTip extends AbstractObelisk{

		@Override
		void breakOthers(World worldIn, BlockPos pos) {
			if (worldIn.getBlockState(pos.down()).getBlock() instanceof AbstractObelisk) {
				worldIn.destroyBlock(pos.down(), true);
			}
			if (worldIn.getBlockState(pos.down(2)).getBlock() instanceof AbstractObelisk) {
				worldIn.destroyBlock(pos.down(2), true);
			}
		}

		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
			return new AxisAlignedBB(0.25f, 0f, 0.25f, 0.75f, 1f, 0.75f);
		}
		
	}

}
