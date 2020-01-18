package com.headfishindustries.fancylamps.blocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCandle extends ModelBase{
	
	private final ModelRenderer stem;
	private final ModelRenderer wick;
	
	public ModelCandle() {
		textureWidth = 32;
		textureHeight = 32;
		
		wick = new ModelRenderer(this, 0, 0);
		wick.addBox(7.5f, 12, 7.5f, 1, 2, 1);
		
		stem = new ModelRenderer(this, 0, 0);
		stem.addBox(4, 0, 4, 8, 12, 8);
		
	}
	
	public void renderStem() {
		stem.render(1/16f);
	}
	
	public void renderWick() {
		wick.render(1/16f);
	}
	

}
