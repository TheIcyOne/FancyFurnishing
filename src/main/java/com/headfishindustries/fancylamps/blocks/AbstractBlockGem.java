package com.headfishindustries.fancylamps.blocks;

import com.headfishindustries.fancylamps.EnumGemType;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class AbstractBlockGem extends Block{
	
	public static PropertyEnum<EnumGemType> GEM_TYPE = PropertyEnum.create("gemtype", EnumGemType.class);

	public AbstractBlockGem() {
		super(Material.GLASS);
		this.translucent = true;
		this.blockResistance = 8f;
		this.blockHardness = 0.2f;
		this.setLightOpacity(0);
		this.setLightLevel(1.0f);
		this.setHarvestLevel("pickaxe", 1);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(GEM_TYPE, EnumGemType.BLANK));
	}
		
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (tab != CreativeTabs.DECORATIONS) return;
		for (EnumGemType gem : EnumGemType.values()) {
			items.add(new ItemStack(this, 1, gem.getMeta()));
		}
	}
	
	
	
	@Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
	}
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
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
