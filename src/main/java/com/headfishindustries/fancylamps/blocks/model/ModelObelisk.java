package com.headfishindustries.fancylamps.blocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.util.math.Vec3d;

public class ModelObelisk extends ModelBase {
	

	private final ModelRenderer base;
	private final ModelRenderer stem;
	private final ModelRenderer tip;
	
	public ModelObelisk() {
		textureWidth = 96;
		textureHeight = 96;
		
		base = new ModelRenderer(this, 0, 0);
		base.addBox(4, 0, 4, 24, 32, 24);
		
		stem = new ModelRenderer(this, 0, 6);
		stem.addBox(6, 32, 6, 20, 32, 20);
		
		
		tip = new ModelRenderer(this, 0, 64);
		tip.addBox(8, 48, 8, 16, 32, 16);
		
	}
	
	public void renderBase() {
		base.render(1f/32f);
		
	}
	
	public void renderStem() {
		stem.render(1f/32f);
	}
	
	public void renderTip() {
		tip.render(1f/32f);
	}
	
}
