package com.headfishindustries.fancylamps;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
	
	
}


