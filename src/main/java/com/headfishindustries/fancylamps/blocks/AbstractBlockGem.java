package com.headfishindustries.fancylamps.blocks;

import java.util.Random;

import com.headfishindustries.fancylamps.EnumGemType;
import com.headfishindustries.fancylamps.FancyDefs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class AbstractBlockGem extends AbstractBlockLamp{
	
	public static PropertyEnum<EnumGemType> GEM_TYPE = PropertyEnum.create("gemtype", EnumGemType.class);

	public AbstractBlockGem() {
		super(Material.GLASS);
		this.blockResistance = 8f;
		this.blockHardness = 0.2f;
		this.setDefaultState(this.blockState.getBaseState().withProperty(GEM_TYPE, EnumGemType.BLANK));
	}
		
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (tab != FancyDefs.FANCY_TAB) return;
		for (EnumGemType gem : EnumGemType.values()) {
			items.add(new ItemStack(this, 1, gem.getMeta()));
		}
	}
	
	@Override
	public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand) {
		super.randomDisplayTick(stateIn, world, pos, rand);
		world.spawnParticle(EnumParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, Block.getStateId(stateIn));
/*		switch((EnumGemType) stateIn.getProperties().get(GEM_TYPE)) {
		case BLANK:
			world.spawnParticle(EnumParticleTypes.CRIT, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, 0);
			break;
		case CORRUPT:
			world.spawnParticle(EnumParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, 0);
			break;
		case CRIMSON:
			world.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, 0);
			break;
		case PURITY:
			world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, 0);
			break;
		case RAINBOW:
			world.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, rand.nextDouble() - 1.0, 0);
			break;
		default:
			break;
		
		}*/
	}
	
	@Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
	}
    
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, GEM_TYPE);
    }
    

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return meta < EnumGemType.values().length ? getDefaultState().withProperty(GEM_TYPE, EnumGemType.values()[meta]) : getDefaultState();
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
       	EnumGemType type = state.getValue(GEM_TYPE);
        return type.getMeta();
    }
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos,  getStateFromMeta(stack.getMetadata()), 18);
	}

	public static class ItemBlockGem extends ItemBlock{

		public ItemBlockGem(Block block) {
			super(block);
			setHasSubtypes(true);
		}
		
		@Override
		public String getUnlocalizedName(ItemStack stack) {
			return stack.getItem().getRegistryName().getResourcePath() + "_" + EnumGemType.values()[stack.getMetadata()].getName().toLowerCase();
		}
	}

}
