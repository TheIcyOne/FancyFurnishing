package com.headfishindustries.fancylamps.blocks.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import com.headfishindustries.fancylamps.FancyLamps;
import com.headfishindustries.fancylamps.blocks.model.ModelCandle;
import com.headfishindustries.fancylamps.blocks.tile.TileCandle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

public class RenderCandle extends TileEntitySpecialRenderer<TileCandle>{
	
	private ResourceLocation stemTexture; 
	private ResourceLocation wickTexture;
	private ModelCandle candle = new ModelCandle();
	
	public RenderCandle() {
/*		if (Loader.isModLoaded("thaumcraft")) {
			//Yay use fancy azanortextures
			stemTexture = new ResourceLocation("thaumcraft", "textures/blocks/candle.png");
			wickTexture = new ResourceLocation("thaumcraft", "textures/blocks/candlestub.png");
		} else {
			//Use my crappy textures instead
			stemTexture = new ResourceLocation(FancyLamps.MODID, "textures/blocks/candle.png");
			wickTexture = new ResourceLocation(FancyLamps.MODID, "textures/blocks/candlestub.png");
		}*/
		
		stemTexture = new ResourceLocation(FancyLamps.MODID, "textures/blocks/candle.png");
	//	wickTexture = new ResourceLocation(FancyLamps.MODID, "textures/blocks/wick.png");
	}
	
	
	
	@Override
	public void render(@Nonnull TileCandle ob, double x, double y, double z, float partial, int dig, float f) {
		GlStateManager.pushMatrix();{
			GlStateManager.translate(x, y, z);
			//GlStateManager.translate(0.2, 0.2 +  0.1 * Math.sin((ob.getWorld().getWorldTime() + ob.getPos().hashCode() + partial) * 0.005), 0.2);
			GlStateManager.scale(0.6, 0.6, 0.6);
			
			GlStateManager.pushMatrix();
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(stemTexture);
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				int col = ob.getColour();
				float r = ((col & 0xFF0000) >> 16)/255;
				float g = ((col & 0x00FF00) >> 8)/255;
				float b = ((col & 0x0000FF))/255;
				GlStateManager.color(r, g, b, 1f);
				
				candle.renderStem();
				
			}
			GlStateManager.popMatrix();
			
			GlStateManager.pushMatrix();
			{
			//	Minecraft.getMinecraft().renderEngine.bindTexture(wickTexture);
				candle.renderWick();
				
			}
			GlStateManager.popMatrix();
			
			
		}
		GlStateManager.popMatrix();
	}
	
}
