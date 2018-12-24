package com.headfishindustries.fancylamps.blocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelEnderGem extends ModelBase{
	
	private final ModelRenderer casing;
	private final ModelRenderer gem;

	public ModelEnderGem() {
		textureWidth = 16;
		textureHeight = 16;
		casing = new ModelRenderer(this, 0, 0);
		casing.addBox(0, 0, 0, 16, 16, 16);			
		
		gem = new ModelRenderer(this, 0, 0);
		gem.addBox(-5f, -5f, -5f, 10, 10, 10);
		gem.setRotationPoint(16, 16, 16);
	}
	
	public void renderCasing() {
		casing.render(1/16f);
	}
	
	public void renderGem(float rot) {
		gem.rotateAngleX =(float) Math.sin(rot);
		gem.rotateAngleY = rot;
		gem.rotateAngleZ = (float) Math.cos(rot);
		gem.render(1/32f);
	}

}
