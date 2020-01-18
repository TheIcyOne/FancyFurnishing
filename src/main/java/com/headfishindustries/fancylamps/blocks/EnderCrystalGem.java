package com.headfishindustries.fancylamps.blocks;

import com.headfishindustries.fancylamps.FancyDefs;
import com.headfishindustries.fancylamps.blocks.tile.TileEnderGem;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class EnderCrystalGem extends Block implements ITileEntityProvider{

	public EnderCrystalGem() {
		super(Material.GLASS);
		this.translucent = true;
		this.blockResistance = 8f;
		this.blockHardness = 0.2f;
		this.setLightOpacity(0);
		this.setLightLevel(16f);
		this.setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(FancyDefs.FANCY_TAB);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEnderGem();
	}
	

	@Override
	public EnumBlockRenderType getRenderType(IBlockState s) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}	

}
