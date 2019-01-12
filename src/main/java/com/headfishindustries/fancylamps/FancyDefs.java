package com.headfishindustries.fancylamps;

import com.headfishindustries.fancylamps.blocks.ArcaneGem;
import com.headfishindustries.fancylamps.blocks.ArcaneLamp;
import com.headfishindustries.fancylamps.blocks.BlockPillar;
import com.headfishindustries.fancylamps.blocks.BlockPost;
import com.headfishindustries.fancylamps.blocks.EnderCrystalGem;
import com.headfishindustries.fancylamps.blocks.AbstractBlockGem;
import com.headfishindustries.fancylamps.blocks.AbstractObelisk;
import com.headfishindustries.fancylamps.blocks.tile.TileEnderGem;
import com.headfishindustries.fancylamps.blocks.tile.TileObelisk;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
	
	public Item itemGem, itemObelisk, itemLamp, itemPillar, itemPost, itemEnderGem, itemArcaneBase, itemArcaneBaseChiselled;
	
	public FancyDefs(){
		def();
	}
	
	private void def() {
		this.gem = new ArcaneGem().setRegistryName(new ResourceLocation(FancyLamps.MODID, "arcane_gem")).setUnlocalizedName("arcane_gem");
		this.obeliskBase = new AbstractObelisk.ObeliskBase().setRegistryName(new ResourceLocation(FancyLamps.MODID, "obelisk_base")).setUnlocalizedName("obelisk");
		this.obeliskStem = new AbstractObelisk.ObeliskStem().setRegistryName(new ResourceLocation(FancyLamps.MODID, "obelisk_stem")).setUnlocalizedName("obelisk");
		this.obeliskTip = new AbstractObelisk.ObeliskTip().setRegistryName(new ResourceLocation(FancyLamps.MODID, "obelisk_tip")).setUnlocalizedName("obelisk");
		this.lamp = new ArcaneLamp().setRegistryName(new ResourceLocation(FancyLamps.MODID, "arcane_lamp")).setUnlocalizedName("arcane_lamp");
		this.pillar = new BlockPillar().setRegistryName(new ResourceLocation(FancyLamps.MODID, "pillar")).setUnlocalizedName("pillar");
		this.post = new BlockPost().setRegistryName(new ResourceLocation(FancyLamps.MODID, "post")).setUnlocalizedName("post");
		this.enderGem = new EnderCrystalGem().setRegistryName(new ResourceLocation(FancyLamps.MODID, "ender_gem")).setUnlocalizedName("ender_gem").setLightLevel(1f);
		this.arcaneBase = new Block(Material.ROCK, MapColor.BLACK).setRegistryName(new ResourceLocation(FancyLamps.MODID, "arcane_base")).setUnlocalizedName("arcane_base").setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setHardness(1.0f);
		this.arcaneBaseChiselled = new Block(Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY).setRegistryName(new ResourceLocation(FancyLamps.MODID, "arcane_base_chisel")).setUnlocalizedName("arcane_base_chisel").setHardness(1.0f).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		this.itemGem = new AbstractBlockGem.ItemBlockGem(this.gem).setRegistryName(new ResourceLocation(FancyLamps.MODID, "arcane_gem"));
		this.itemObelisk = new AbstractBlockGem.ItemBlockGem(this.obeliskBase).setRegistryName(new ResourceLocation(FancyLamps.MODID, "obelisk"));
		this.itemLamp = new AbstractBlockGem.ItemBlockGem(this.lamp).setRegistryName(new ResourceLocation(FancyLamps.MODID, "lamp"));
		this.itemPillar = new ItemBlock(this.pillar).setRegistryName(new ResourceLocation(FancyLamps.MODID, "pillar")).setUnlocalizedName("pillar");
		this.itemPost = new ItemBlock(this.post).setRegistryName(new ResourceLocation(FancyLamps.MODID, "post")).setUnlocalizedName("post");
		this.itemEnderGem = new ItemBlock(this.enderGem).setRegistryName(new ResourceLocation(FancyLamps.MODID, "ender_gem")).setUnlocalizedName("ender_gem");
		this.itemArcaneBase= new ItemBlock(this.arcaneBase).setRegistryName(new ResourceLocation(FancyLamps.MODID, "arcane_base")).setUnlocalizedName("arcane_base");
		this.itemArcaneBaseChiselled = new ItemBlock(this.arcaneBaseChiselled).setRegistryName(new ResourceLocation(FancyLamps.MODID, "arcane_base_chisel")).setUnlocalizedName("arcane_base_chisel");
		
	}
	
	public void registerBlocks(RegistryEvent.Register<Block> event){
		IForgeRegistry<Block> reg = event.getRegistry();
		
		reg.register(this.gem);
		reg.registerAll(this.obeliskBase, this.obeliskStem, this.obeliskTip);
		reg.register(this.lamp);
		reg.registerAll(this.pillar, this.post, this.enderGem, this.arcaneBase, this.arcaneBaseChiselled);
	}
	
	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> reg = event.getRegistry();
		reg.registerAll(itemGem, itemObelisk, itemLamp, itemPillar, itemPost, itemEnderGem, itemArcaneBase, itemArcaneBaseChiselled);
		regTiles();
	}
	
	private void regTiles() {
		GameRegistry.registerTileEntity(TileEnderGem.class, new ResourceLocation(FancyLamps.MODID, "endgem"));
	}


}
