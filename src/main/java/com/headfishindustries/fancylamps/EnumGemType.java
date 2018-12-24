package com.headfishindustries.fancylamps;

import net.minecraft.util.IStringSerializable;

public enum EnumGemType implements IStringSerializable{
	BLANK(0),
	CORRUPT(1),
	CRIMSON(2),
	PURITY(3),
	RAINBOW(4);
	
	private final int meta;
	
	private EnumGemType(int m) {
		this.meta = m;
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}
	
	public int getMeta() {
		return this.meta;
	}
	
}
