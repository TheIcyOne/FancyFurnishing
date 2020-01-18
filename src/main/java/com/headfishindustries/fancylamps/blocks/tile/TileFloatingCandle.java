package com.headfishindustries.fancylamps.blocks.tile;

import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.headfishindustries.fancylamps.FancyLamps;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.animation.TimeValues;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class TileFloatingCandle extends TileEntity implements ITickable{
	
	float motion = 0f;
	float pos = 0f;
	double d = 0;
	double hash;
	
	private final TimeValues.VariableValue move;
	private final IAnimationStateMachine as;
	
	public TileFloatingCandle() {
		pos = (float) (Math.random() * 8f);
		
		if (FMLCommonHandler.instance().getSide().equals(Side.CLIENT)) {
			move = new TimeValues.VariableValue(0);
			as = ModelLoaderRegistry.loadASM(new ResourceLocation(FancyLamps.MODID, "asms/block/candle.json"), ImmutableMap.of("move", move));
		} else {
			move = null;
			as = null;
		}
	}
	
	public TileFloatingCandle init() {
		return this;
	}
	
	@Override
	public boolean hasCapability(Capability<?> cap, EnumFacing side) {
		return cap == CapabilityAnimation.ANIMATION_CAPABILITY || super.hasCapability(cap, side);
	}
	
	@Override
	public <T> T getCapability(Capability<T> cap, EnumFacing side) {
		
		if (cap == CapabilityAnimation.ANIMATION_CAPABILITY) return CapabilityAnimation.ANIMATION_CAPABILITY.cast(as);
		
		
		return super.getCapability(cap, side);
	}

	@Override
	public void update() {
		hash = (hash == 0.0D) ? this.world.rand.nextInt() : hash;
		float max = 6f;
		float min = 2f;
		
		d = d + 1;		
	
		pos = (float) (min + (Math.sin((d + hash)/50) + 1) * (max - min) / 2);
		
		if (world.rand.nextDouble() > 0.7) world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, getPos().getX() + 0.5, getPos().getY() + 0.85, getPos().getZ() + 0.5, 0.0D, 0.0D, 0.0D);
		
		if (world.isRemote) {
			move.setValue(pos / 8);
		}
		
		
	}

}
