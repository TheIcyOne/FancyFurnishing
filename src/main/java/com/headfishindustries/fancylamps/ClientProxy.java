package com.headfishindustries.fancylamps;

import com.headfishindustries.fancylamps.blocks.INeedACommonBlockColour;
import com.headfishindustries.fancylamps.blocks.INeedACommonItemColour;
import com.headfishindustries.fancylamps.blocks.render.RenderEnderGem;
import com.headfishindustries.fancylamps.blocks.render.RenderItemEnderGem;
import com.headfishindustries.fancylamps.blocks.tile.TileEnderGem;
import com.headfishindustries.fancylamps.blocks.tile.TileFloatingCandle;
import com.headfishindustries.fancylamps.modelfuckery.UnModelLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.animation.AnimationTESR;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		MinecraftForge.EVENT_BUS.register(ClientRenderingRegistry.class);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEnderGem.class, new RenderEnderGem());
		ClientRegistry.bindTileEntitySpecialRenderer(TileFloatingCandle.class, new AnimationTESR<>());
		//ClientRegistry.bindTileEntitySpecialRenderer(TileCandle.class, new RenderCandle());
		
		ModelLoaderRegistry.registerLoader(new UnModelLoader());
	}
	
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);

    	final IBlockColor blockColourHandler = (state, blockAccess, pos, tintIndex) -> {
    		if (blockAccess != null && pos != null && state !=null && blockAccess.getBlockState(pos).getBlock() instanceof INeedACommonBlockColour) {
    			INeedACommonBlockColour block = (INeedACommonBlockColour) (blockAccess.getBlockState(pos).getBlock());
    			return block.colorMultiplier(state, blockAccess, pos, tintIndex);
    		}

    		return 0xFFFFFF;
    	};
    	
    	final IItemColor itemColourHandler = (stack, meta) -> {
    		if (stack != null && stack.getItem() != null && stack.getItem() instanceof INeedACommonItemColour){
    			INeedACommonItemColour item = (INeedACommonItemColour) stack.getItem();
    			return item.colorMultiplier(stack, 1);
    		}
    		return 0xFFFFFF;
    	};
    	
    	Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(blockColourHandler, FancyLamps.DEFS.candle);
    	Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColourHandler, FancyLamps.DEFS.candle);
    	
		
	}
	
	public static class ClientRenderingRegistry{
		@SubscribeEvent
	    public static void registerModels(ModelRegistryEvent event) {
			for (EnumGemType t : EnumGemType.values()) {
				ModelLoader.setCustomModelResourceLocation(FancyLamps.DEFS.itemGem, t.getMeta(), new ModelResourceLocation(FancyLamps.MODID + ":gem_" + t.getName(), "inventory"));
				ModelLoader.setCustomModelResourceLocation(FancyLamps.DEFS.itemObelisk, t.getMeta(), new ModelResourceLocation(FancyLamps.MODID + ":obelisk_" + t.getName(), "inventory"));
				ModelLoader.setCustomModelResourceLocation(FancyLamps.DEFS.itemLamp, t.getMeta(), new ModelResourceLocation(FancyLamps.MODID + ":lamp_" + t.getName(), "inventory"));
			}
			
			ModelLoader.setCustomModelResourceLocation(FancyLamps.DEFS.itemPillar, 0, new ModelResourceLocation(FancyLamps.MODID + ":pillar", "inventory"));
			ModelLoader.setCustomModelResourceLocation(FancyLamps.DEFS.itemPost, 0, new ModelResourceLocation(FancyLamps.MODID + ":post", "inventory"));
			ModelLoader.setCustomModelResourceLocation(FancyLamps.DEFS.itemArcaneBase, 0, new ModelResourceLocation(FancyLamps.MODID + ":arcane_base_block", "inventory"));
			ModelLoader.setCustomModelResourceLocation(FancyLamps.DEFS.itemArcaneBaseChiselled, 0, new ModelResourceLocation(FancyLamps.MODID + ":arcane_base_chisel", "inventory"));
			
			ModelLoader.setCustomModelResourceLocation(FancyLamps.DEFS.itemLanternTorch, 0, new ModelResourceLocation(FancyLamps.MODID + ":lantern_torch", "inventory"));
			ModelLoader.setCustomModelResourceLocation(FancyLamps.DEFS.itemLanternRedstone, 0, new ModelResourceLocation(FancyLamps.MODID + ":lantern_redstone", "inventory"));

			
			FancyLamps.DEFS.itemEnderGem.setTileEntityItemStackRenderer(new RenderItemEnderGem());
			ModelLoader.setCustomModelResourceLocation(FancyLamps.DEFS.itemEnderGem, 0, new ModelResourceLocation(FancyLamps.MODID + ":teisr_ender_gem", "inventory"));
			for (EnumDyeColor colour : EnumDyeColor.values())
			ModelLoader.setCustomModelResourceLocation(FancyLamps.DEFS.itemCandle, colour.getMetadata(), new ModelResourceLocation(FancyLamps.MODID + ":floating_candle", "inventory"));
		}
	}
}
