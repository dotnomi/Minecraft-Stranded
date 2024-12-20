package com.dotnomi.stranded.worldgen.biome;

import com.dotnomi.stranded.Stranded;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModBiomes {
  public static final RegistryKey<Biome> MARS_BIOME = RegistryKey.of(RegistryKeys.BIOME,
    Identifier.of(Stranded.MOD_ID, "mars_biome"));

  public static void bootstrap(Registerable<Biome> context) {
    context.register(MARS_BIOME, marsBiome(context));
  }

  public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
    DefaultBiomeFeatures.addLandCarvers(builder);
    DefaultBiomeFeatures.addAmethystGeodes(builder);
    DefaultBiomeFeatures.addDungeons(builder);
    DefaultBiomeFeatures.addMineables(builder);
    DefaultBiomeFeatures.addSprings(builder);
    DefaultBiomeFeatures.addFrozenTopLayer(builder);
  }

  public static Biome marsBiome(Registerable<Biome> context) {
    SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
    spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

    DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
    DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

    GenerationSettings.LookupBackedBuilder biomeBuilder =
      new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

    globalOverworldGeneration(biomeBuilder);
    DefaultBiomeFeatures.addMossyRocks(biomeBuilder);
    DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
    DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

    biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
    DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
    DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

    DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
    DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

    return new Biome.Builder()
      .precipitation(true)
      .downfall(0.4f)
      .temperature(0.7f)
      .generationSettings(biomeBuilder.build())
      .spawnSettings(spawnBuilder.build())
      .effects((new BiomeEffects.Builder())
        .waterColor(0x8B3E2F) // Dunkles Rötlich-Braun, symbolisiert trübes Wasser
        .waterFogColor(0x6A2C1B) // Passender Nebel zu trübem Wasser
        .skyColor(0xC1440E) // Rötlich-orange Himmel wie auf dem Mars
        .grassColor(0xA0522D) // Rostbraune Farbe für "Gras"
        .foliageColor(0x8B4513) // Rostiges Braun für das Laub
        .fogColor(0xA65B2D)
        .moodSound(BiomeMoodSound.CAVE)
        .music(MusicType.createIngameMusic(RegistryEntry.of(SoundEvents.AMBIENT_CAVE.value()))).build())
      .build();
  }

  public static RegistryEntry<Biome> toRegistryEntry(DynamicRegistryManager.Immutable registryManager, RegistryKey<Biome> registryKey) {
    Registry<Biome> biomeRegistry = registryManager.get(RegistryKeys.BIOME);
    return biomeRegistry.getEntry(registryKey).orElseThrow();
  }
}
