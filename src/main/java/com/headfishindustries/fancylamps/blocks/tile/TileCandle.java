package com.headfishindustries.fancylamps.blocks.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileCandle extends TileEntity{
	
	private int colour = 0xffffff;

	public int getColour() {
		return colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.colour = compound.hasKey("colour") ? compound.getInteger("colour") : 0xFFFFFF;
	}
	 
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setInteger("colour", this.colour);
		return compound;
	}

}
