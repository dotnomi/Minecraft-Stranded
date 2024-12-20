package com.dotnomi.stranded;

import com.dotnomi.stranded.block.ModBlocks;
import com.dotnomi.stranded.config.Mission;
import com.dotnomi.stranded.config.ModConfig;
import com.dotnomi.stranded.config.Module;
import com.dotnomi.stranded.config.Resource;
import com.dotnomi.stranded.event.PlayVoiceoverEvent;
import com.dotnomi.stranded.event.handler.PlayVoiceoverHandler;
import com.dotnomi.stranded.event.handler.PlayerTickHandler;
import com.dotnomi.stranded.item.ModItems;
import com.dotnomi.stranded.networking.ModC2SPackets;
import com.dotnomi.stranded.networking.ModPayloads;
import com.dotnomi.stranded.sound.ModSoundCategories;
import com.dotnomi.stranded.sound.ModSounds;
import com.dotnomi.stranded.worldgen.ModChunkGenerators;
import com.dotnomi.stranded.worldgen.OverworldSequenceChunkGenerator;
import com.dotnomi.stranded.worldgen.biome.ModBiomes;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stranded implements ModInitializer {
	public static final String MOD_ID = "stranded";
	public static final Logger LOGGER = LoggerFactory.getLogger("Stranded");
	public static final ModConfig CONFIG = new ModConfig();

	public static ModSoundCategories SOUND_CATEGORIES = ModSoundCategories.getInstance();

	@Override
	public void onInitialize() {
		CONFIG.initializeConfig();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();

		ModPayloads.initialize();
		ModC2SPackets.initialize();

		registerEvents();

		for (Mission mission: CONFIG.getConfigData().getMissions()) {
			for (Module module: mission.getModules()) {
				for (Resource resource: module.getNeededResources()) {
					Item resourceItem = resource.getItem();
					LOGGER.info(resourceItem.toString());
				}
			}
		}

		CustomPortalBuilder.beginPortal()
			.frameBlock(Blocks.DIAMOND_BLOCK)
			.lightWithItem(Items.ENDER_EYE)
			.destDimID(Identifier.of(MOD_ID, "mars"))
			.tintColor(45,65,101)
			.registerPortal();
	}

	private void registerEvents() {
		RegistryKey<World> marsWorld = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Stranded.MOD_ID, "mars"));

		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());

		ServerWorldEvents.LOAD.register((server, world) -> {
			if (world.getRegistryKey() == marsWorld) {
				BlockPos oldSpawnPosition = world.getSpawnPos();
				BlockPos newSpawnPosition = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, oldSpawnPosition);
				world.setSpawnPos(newSpawnPosition, 0.0f);
			}
		});

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			RegistryEntry<Biome> marsBiome = ModBiomes.toRegistryEntry(server.getRegistryManager(), ModBiomes.MARS_BIOME);
			ServerWorld mars = server.getWorld(marsWorld);
			if (mars == null) {
				return;
			}

			for (ServerPlayerEntity player: server.getPlayerManager().getPlayerList()) {
				if (player.getWorld() == server.getWorld(World.OVERWORLD)
					&& server.getOverworld().getBiome(player.getBlockPos()) == marsBiome) {
					//player.teleport(mars, mars.getSpawnPos().getX(), mars.getSpawnPos().getY(), mars.getSpawnPos().getZ(), player.getYaw(), player.getPitch());
					player.setSpawnPoint(mars.getRegistryKey(), mars.getSpawnPos(), 0.0f, true, false);
				}
			}
		});

		PlayVoiceoverEvent.EVENT.register(new PlayVoiceoverHandler());
	}
}