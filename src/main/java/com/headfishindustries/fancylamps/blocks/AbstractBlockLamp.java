package com.headfishindustries.fancylamps.blocks;

import com.headfishindustries.fancylamps.FancyDefs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class AbstractBlockLamp extends Block{
	
	LightType type = LightType.NORMAL;

	public AbstractBlockLamp(Material mat) {
		super(mat);
		this.setLightOpacity(0);
		this.translucent = true;
		this.setHarvestLevel("pickaxe", 1);
		this.setCreativeTab(FancyDefs.FANCY_TAB);
		this.setLightLevel(1f);
	}
	
	public AbstractBlockLamp(Material mat, LightType t) {
		this(mat);
		this.type = t;
	}
	
	@Override
	public int getLightValue(IBlockState s, IBlockAccess world, BlockPos pos) {
		switch(this.type) {
		case CLIENT:
			return FMLCommonHandler.instance().getEffectiveSide().isClient() ? this.lightValue : 0;
		case SERVER:
			return FMLCommonHandler.instance().getEffectiveSide().isServer() ? this.lightValue : 0;
		case BLANK:
			return 0;
		case NORMAL:
			return this.lightValue;
		default:
			break;
		
		}
		return lightOpacity;
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
	
	
	
	static enum LightType{
		//No idea why you'd want a blank lamp, but here it is anyway.
		BLANK,
		CLIENT,
		SERVER,
		NORMAL
	}

}
