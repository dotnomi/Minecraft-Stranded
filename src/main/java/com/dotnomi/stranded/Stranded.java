package com.dotnomi.stranded;

import com.dotnomi.stranded.block.ModBlocks;
import com.dotnomi.stranded.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stranded implements ModInitializer {
	public static final String MOD_ID = "stranded";
	public static final Logger LOGGER = LoggerFactory.getLogger("Stranded");

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}