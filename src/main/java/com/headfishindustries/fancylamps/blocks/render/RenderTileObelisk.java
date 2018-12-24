package com.headfishindustries.fancylamps.blocks.render;

import javax.annotation.Nonnull;

import com.headfishindustries.fancylamps.EnumGemType;
import com.headfishindustries.fancylamps.FancyLamps;
import com.headfishindustries.fancylamps.blocks.AbstractBlockGem;
import com.headfishindustries.fancylamps.blocks.AbstractObelisk;
import com.headfishindustries.fancylamps.blocks.model.ModelObelisk;
import com.headfishindustries.fancylamps.blocks.tile.TileObelisk;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTileObelisk extends TileEntitySpecialRenderer<TileObelisk>{

	private static final String BODY_PATH = "textures/blocks/obelisk/obelisk_";
	private static final String BASE_PATH = "textures/blocks/obelisk/base_";
	
	private ResourceLocation bodyTexture;
	private ResourceLocation baseTexture;
	
	ModelObelisk model = new ModelObelisk();
	
	@Override
	public void render(@Nonnull TileObelisk ob, double x, double y, double z, float partial, int dig, float f) {
		IBlockState s = ob.getWorld().getBlockState(ob.getPos());
		if (!(s.getBlock() instanceof AbstractObelisk)) return;
			
		EnumGemType type = (EnumGemType) s.getProperties().get(AbstractBlockGem.GEM_TYPE);
			
		if (type == null) type = EnumGemType.BLANK;
			
		bodyTexture = new ResourceLocation(FancyLamps.MODID, BODY_PATH + type.toString() + ".png");
		baseTexture = new ResourceLocation(FancyLamps.MODID, BASE_PATH + type.toString() + ".png");
		
		Minecraft.getMinecraft().renderEngine.bindTexture(baseTexture);
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, z);
			
			model.renderBase();
			model.renderStem();
			model.renderTip();
		}
		GlStateManager.popMatrix();
	}

}
