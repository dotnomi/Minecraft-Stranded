package com.dotnomi.stranded;

import com.dotnomi.stranded.block.ModBlocks;
import com.dotnomi.stranded.config.Mission;
import com.dotnomi.stranded.config.ModConfig;
import com.dotnomi.stranded.config.Module;
import com.dotnomi.stranded.config.Resource;
import com.dotnomi.stranded.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stranded implements ModInitializer {
	public static final String MOD_ID = "stranded";
	public static final Logger LOGGER = LoggerFactory.getLogger("Stranded");
	public static final ModConfig CONFIG = new ModConfig();

	@Override
	public void onInitialize() {
		CONFIG.initializeConfig();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		for (Mission mission: CONFIG.getConfigData().getMissions()) {
			for (Module module: mission.getModules()) {
				for (Resource resource: module.getNeededResources()) {
					Item resourceItem = resource.getItem();
					LOGGER.info(resourceItem.toString());
				}
			}
		}
	}
}