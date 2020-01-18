package com.headfishindustries.fancylamps.blocks;

import net.minecraft.item.ItemStack;

public interface INeedACommonItemColour
{
	int colorMultiplier(ItemStack stack, int tintIndex);
}
