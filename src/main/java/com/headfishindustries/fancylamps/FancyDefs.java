package com.headfishindustries.fancylamps;

import com.headfishindustries.fancylamps.blocks.ArcaneGem;
import com.headfishindustries.fancylamps.blocks.ArcaneLamp;
import com.headfishindustries.fancylamps.blocks.BlockFloatingCandle;
import com.headfishindustries.fancylamps.blocks.BlockFloatingCandle.ItemFloatingCandle;
import com.headfishindustries.fancylamps.blocks.BlockLantern114;
import com.headfishindustries.fancylamps.blocks.BlockPillar;
import com.headfishindustries.fancylamps.blocks.BlockPost;
import com.headfishindustries.fancylamps.blocks.EnderCrystalGem;
import com.headfishindustries.fancylamps.blocks.AbstractBlockGem;
import com.headfishindustries.fancylamps.blocks.AbstractObelisk;
import com.headfishindustries.fancylamps.blocks.tile.TileCandle;
import com.headfishindustries.fancylamps.blocks.tile.TileEnderGem;
import com.headfishindustries.fancylamps.blocks.tile.TileFloatingCandle;
import com.headfishindustries.fancylamps.blocks.tile.TileObelisk;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class FancyDefs {

	public Block gem;
	public Block obeliskBase, obeliskStem, obeliskTip;
	public Block lamp;
	public Block pillar, post;
	public Block enderGem;
	public Block arcaneBase, arcaneBaseChiselled;
	
	public Block lantern114_torch, lantern114_redstone;
	
	public Block candle;
		
	public static final FancyTab FANCY_TAB = new FancyTab();
	
	public Item itemGem, itemObelisk, itemLamp, itemPillar, itemPost, itemEnderGem, itemArcaneBase, itemArcaneBaseChiselled, itemLanternTorch, itemLanternRedstone, itemCandle;
	
	public FancyDefs(){
		def();
	}
	
	private void def() {
		this.gem = new ArcaneGem().setRegistryName(makeRL("arcane_gem")).setUnlocalizedName("arcane_gem");
		this.obeliskBase = new AbstractObelisk.ObeliskBase().setRegistryName(makeRL("obelisk_base")).setUnlocalizedName("obelisk");
		this.obeliskStem = new AbstractObelisk.ObeliskStem().setRegistryName(makeRL("obelisk_stem")).setUnlocalizedName("obelisk");
		this.obeliskTip = new AbstractObelisk.ObeliskTip().setRegistryName(makeRL("obelisk_tip")).setUnlocalizedName("obelisk");
		this.lamp = new ArcaneLamp().setRegistryName(makeRL("arcane_lamp")).setUnlocalizedName("arcane_lamp");
		this.pillar = new BlockPillar().setRegistryName(makeRL("pillar")).setUnlocalizedName("pillar");
		this.post = new BlockPost().setRegistryName(makeRL("post")).setUnlocalizedName("post");
		this.enderGem = new EnderCrystalGem().setRegistryName(makeRL("ender_gem")).setUnlocalizedName("ender_gem").setLightLevel(1f);
		this.arcaneBase = new Block(Material.ROCK, MapColor.BLACK).setRegistryName(makeRL("arcane_base")).setUnlocalizedName("arcane_base").setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setHardness(1.0f);
		this.arcaneBaseChiselled = new Block(Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY).setRegistryName(makeRL("arcane_base_chisel")).setUnlocalizedName("arcane_base_chisel").setHardness(1.0f).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		this.lantern114_torch = new BlockLantern114(false).setRegistryName(makeRL("lantern_torch")).setUnlocalizedName("lantern_torch");
		this.lantern114_redstone = new BlockLantern114(true).setRegistryName(makeRL("lantern_redstone")).setUnlocalizedName("lantern_redstone");
		
		this.candle = new BlockFloatingCandle().setRegistryName(makeRL("floating_candle")).setUnlocalizedName("floating_candle");
		
		
		this.itemGem = new AbstractBlockGem.ItemBlockGem(this.gem).setRegistryName(makeRL("arcane_gem"));
		this.itemObelisk = new AbstractBlockGem.ItemBlockGem(this.obeliskBase).setRegistryName(makeRL("obelisk"));
		this.itemLamp = new AbstractBlockGem.ItemBlockGem(this.lamp).setRegistryName(makeRL("lamp"));
		this.itemPillar = new ItemBlock(this.pillar).setRegistryName(makeRL("pillar")).setUnlocalizedName("pillar");
		this.itemPost = new ItemBlock(this.post).setRegistryName(makeRL("post")).setUnlocalizedName("post");
		this.itemEnderGem = new ItemBlock(this.enderGem).setRegistryName(makeRL("ender_gem")).setUnlocalizedName("ender_gem");
		this.itemArcaneBase= new ItemBlock(this.arcaneBase).setRegistryName(makeRL("arcane_base")).setUnlocalizedName("arcane_base");
		this.itemArcaneBaseChiselled = new ItemBlock(this.arcaneBaseChiselled).setRegistryName(makeRL("arcane_base_chisel")).setUnlocalizedName("arcane_base_chisel");
		
		this.itemLanternTorch = new ItemBlock(this.lantern114_torch).setRegistryName(makeRL("lantern_torch")).setUnlocalizedName("lantern_torch");
		this.itemLanternRedstone = new ItemBlock(this.lantern114_redstone).setRegistryName(makeRL("lantern_redstone")).setUnlocalizedName("lantern_redstone");
		
		this.itemCandle = new ItemFloatingCandle((BlockFloatingCandle) this.candle).setRegistryName(makeRL("floating_candle")).setUnlocalizedName("floating_candle");

		
	}
	
	public void registerBlocks(RegistryEvent.Register<Block> event){
		IForgeRegistry<Block> reg = event.getRegistry();
		
		reg.register(this.gem);
		reg.registerAll(this.obeliskBase, this.obeliskStem, this.obeliskTip);
		reg.register(this.lamp);
		reg.registerAll(this.pillar, this.post, this.enderGem, this.arcaneBase, this.arcaneBaseChiselled);
		reg.registerAll(this.lantern114_torch, this.lantern114_redstone);
		reg.register(this.candle);
	}
	
	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> reg = event.getRegistry();
		reg.registerAll(itemGem, itemObelisk, itemLamp, itemPillar, itemPost, itemEnderGem, itemArcaneBase, itemArcaneBaseChiselled);
		reg.registerAll(this.itemLanternTorch, this.itemLanternRedstone);
		reg.register(this.itemCandle);
		regTiles();
	}
	
	private void regTiles() {
		GameRegistry.registerTileEntity(TileEnderGem.class, makeRL("endgem"));
		GameRegistry.registerTileEntity(TileFloatingCandle.class, makeRL("candle"));
	}
	
	static ResourceLocation makeRL(String path) {
		return new ResourceLocation(FancyLamps.MODID, path);
	}
	
	
	public static class FancyTab extends CreativeTabs{

		public FancyTab() {
			super("fancytabs");
		}

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(FancyLamps.DEFS.itemGem);
		}
		
	}


}
