package com.headfishindustries.fancylamps;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {		
		
		public void preInit(FMLPreInitializationEvent event){
			MinecraftForge.EVENT_BUS.register(FancyLamps.class);
		}
		
		public void init(FMLInitializationEvent event) {
			
		}

}
