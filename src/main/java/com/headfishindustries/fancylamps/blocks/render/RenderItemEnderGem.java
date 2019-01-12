package com.headfishindustries.fancylamps.blocks.render;

import org.lwjgl.opengl.GL11;

import com.headfishindustries.fancylamps.FancyLamps;
import com.headfishindustries.fancylamps.blocks.model.ModelEnderGem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItemEnderGem extends TileEntityItemStackRenderer{

	private ResourceLocation casingTexture = new ResourceLocation("minecraft", "textures/blocks/glass_black.png");
	private ResourceLocation gemTexture = new ResourceLocation(FancyLamps.MODID, "textures/blocks/ender_gem.png");
	
	private ModelEnderGem model = new ModelEnderGem();

	@Override
	public void renderByItem(ItemStack stack) {
		super.renderByItem(stack);
		GlStateManager.pushMatrix();{
			GlStateManager.scale(0.625, 0.625, 0.625);
			GlStateManager.translate(0.1, 0.2, 0);
			GlStateManager.rotate(1f, 30f, 145f, 0f);
			GlStateManager.pushMatrix();
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(gemTexture);				
				GlStateManager.pushMatrix();{
					model.renderGem((Minecraft.getMinecraft().world.getWorldTime()) * 0.075f);
				}
				GlStateManager.popMatrix();
			}
			GlStateManager.popMatrix();
				
			GlStateManager.pushMatrix();{
				Minecraft.getMinecraft().renderEngine.bindTexture(casingTexture);
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GlStateManager.color(1f, 1f, 1f, 0.9f);
				
				model.renderCasing();
			}
			GlStateManager.popMatrix();
		}
		GlStateManager.popMatrix();
	}
}
