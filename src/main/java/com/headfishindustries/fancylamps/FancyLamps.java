package com.headfishindustries.fancylamps;

import com.headfishindustries.fancylamps.blocks.render.RenderItemEnderGem;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid=FancyLamps.MODID, version = FancyLamps.VERSION, name = FancyLamps.NAME, acceptedMinecraftVersions="[1.12, 1.13]")
public class FancyLamps {

	public static final String MODID = "fancylamps";
	public static final String VERSION = "%gradle.version%";
	public static final String NAME = "Fancy Lamps";
	
	public static FancyDefs DEFS;
	
	@SidedProxy(serverSide="com.headfishindustries.fancylamps.CommonProxy", clientSide = "com.headfishindustries.fancylamps.ClientProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
		DEFS = new FancyDefs();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent e) {
		proxy.init(e);
	}
	
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		DEFS.registerBlocks(event);
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		DEFS.registerItems(event);
	}
	
	@SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
		for (EnumGemType t : EnumGemType.values()) {
			ModelLoader.setCustomModelResourceLocation(DEFS.itemGem, t.getMeta(), new ModelResourceLocation(MODID + ":gem_" + t.getName(), "inventory"));
			ModelLoader.setCustomModelResourceLocation(DEFS.itemObelisk, t.getMeta(), new ModelResourceLocation(MODID + ":obelisk_" + t.getName(), "inventory"));
			ModelLoader.setCustomModelResourceLocation(DEFS.itemLamp, t.getMeta(), new ModelResourceLocation(MODID + ":lamp_" + t.getName(), "inventory"));
		}
		
		ModelLoader.setCustomModelResourceLocation(DEFS.itemPillar, 0, new ModelResourceLocation(MODID + ":pillar", "inventory"));
		ModelLoader.setCustomModelResourceLocation(DEFS.itemPost, 0, new ModelResourceLocation(MODID + ":post", "inventory"));
		ModelLoader.setCustomModelResourceLocation(DEFS.itemArcaneBase, 0, new ModelResourceLocation(MODID + ":arcane_base_block", "inventory"));
		ModelLoader.setCustomModelResourceLocation(DEFS.itemArcaneBaseChiselled, 0, new ModelResourceLocation(MODID + ":arcane_base_chisel", "inventory"));
		
		FancyLamps.DEFS.itemEnderGem.setTileEntityItemStackRenderer(new RenderItemEnderGem());
		ModelLoader.setCustomModelResourceLocation(DEFS.itemEnderGem, 0, new ModelResourceLocation(MODID + ":teisr_ender_gem", "inventory"));
	}
}


