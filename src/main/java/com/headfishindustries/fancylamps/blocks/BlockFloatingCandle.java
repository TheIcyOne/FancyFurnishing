package com.headfishindustries.fancylamps.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.headfishindustries.fancylamps.FancyDefs;
import com.headfishindustries.fancylamps.blocks.tile.TileCandle;
import com.headfishindustries.fancylamps.blocks.tile.TileFloatingCandle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliserExt;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(modid="thaumcraft", iface = "thaumcraft.api.crafting.IInfusionStabiliserExt", striprefs = true)
public class BlockFloatingCandle extends BlockColored implements IInfusionStabiliserExt, IBlockColor{
	
	protected static final AxisAlignedBB AABB = new AxisAlignedBB(4/16, 0, 4/16, 12/16, 12/16, 12/16);
	
	public BlockFloatingCandle() {
		super(Material.CIRCUITS);
		this.setLightLevel(1f);
		this.setCreativeTab(FancyDefs.FANCY_TAB);
		this.setDefaultState(blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
	}
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return new AxisAlignedBB(0.375D, 0.25D, 0.375D, 0.625D, 1D, 0.625D);
    }

	@Override
	public boolean canStabaliseInfusion(World world, BlockPos pos) {
		return true;
	}

	@Override
	public float getStabilizationAmount(World world, BlockPos pos) {
		return 0.2f;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		world.setBlockState(pos, state.withProperty(COLOR, EnumDyeColor.byMetadata(stack.getMetadata())));
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState s) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	//	return EnumBlockRenderType.MODEL;
	}

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		return state.getValue(COLOR).getColorValue();
	}
	
	@Override
	protected BlockStateContainer createBlockState()
    {
        return new ExtendedBlockState(this, new IProperty[] {COLOR}, new IUnlistedProperty[] {Properties.AnimationProperty});
    }
	
	@Override
	public boolean hasTileEntity(IBlockState s) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World w, IBlockState s) {
		TileFloatingCandle c = new TileFloatingCandle();
		c.init();
		return c;
	}

	
	public static class ItemFloatingCandle extends ItemBlock implements IItemColor{

		public ItemFloatingCandle(BlockFloatingCandle block) {
			super(block);
		}

		@Override
		public int colorMultiplier(ItemStack stack, int tintIndex) {
			return EnumDyeColor.byMetadata(stack.getMetadata()).getColorValue();
		}
		
		/** To allow mid-air placement. **/
		@Override
		public ActionResult<ItemStack> onItemRightClick(World w, EntityPlayer p, EnumHand h){
			ItemStack s = p.getHeldItem(h);
			
			if (w.isRemote) return ActionResult.newResult(EnumActionResult.SUCCESS, s);
			
			double x = Math.floor(p.posX);
			double y = Math.floor(p.posY + p.eyeHeight);
			double z = Math.floor(p.posZ);
			
			Vec3d look = p.getLookVec();
			
			EnumFacing f = EnumFacing.getFacingFromVector((float) look.x,(float) look.y,(float) look.z);
			
			switch(f) {
			case UP:
				y = Math.floor(p.getEntityBoundingBox().maxY + 1);
				break;
			case DOWN:
				y = Math.floor(p.getEntityBoundingBox().minY -1);
				break;
			case NORTH:
				z = Math.floor(p.getEntityBoundingBox().minZ -1);
				break;
			case SOUTH:
				z = Math.floor(p.getEntityBoundingBox().maxZ + 1);
				break;
			case EAST:
				x = Math.floor(p.getEntityBoundingBox().maxX + 1);
				break;
			case WEST:
				x = Math.floor(p.getEntityBoundingBox().minX - 1);
				break;
			
			}
			
			BlockPos pos = new BlockPos(x, y, z);
			if (w.mayPlace(block, pos, false, f, p)) {
				s.onItemUse(p, w, pos, h, f, 0, 0, 0);
			}
			
			return ActionResult.newResult(EnumActionResult.SUCCESS, s);
		}
		
	}

}
