package com.headfishindustries.fancylamps;

import com.headfishindustries.fancylamps.blocks.render.RenderEnderGem;
import com.headfishindustries.fancylamps.blocks.render.RenderItemEnderGem;
import com.headfishindustries.fancylamps.blocks.tile.TileEnderGem;
import com.headfishindustries.fancylamps.modelfuckery.UnModelLoader;

import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEnderGem.class, new RenderEnderGem());
		
		ModelLoaderRegistry.registerLoader(new UnModelLoader());
	}
	
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		
		
	}
}
