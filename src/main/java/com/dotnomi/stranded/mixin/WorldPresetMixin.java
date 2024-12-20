package com.dotnomi.stranded.mixin;

import com.dotnomi.stranded.Stranded;
import com.dotnomi.stranded.worldgen.world.ModWorldPresets;
import com.dotnomi.stranded.worldgen.OverworldSequenceChunkGenerator;
import com.dotnomi.stranded.worldgen.biome.ModBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.WorldPreset;
import net.minecraft.world.gen.WorldPresets;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.FlatChunkGenerator;
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.FlatChunkGeneratorLayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(WorldPresets.Registrar.class)
public abstract class WorldPresetMixin {
  @Unique
  private static final RegistryKey<WorldPreset> MARS_WORLD = ModWorldPresets.MARS_WORLD;

  @Shadow
  protected abstract void register(RegistryKey<WorldPreset> key, DimensionOptions dimensionOptions);

  @Shadow protected abstract DimensionOptions createOverworldOptions(ChunkGenerator chunkGenerator);

  @Shadow @Final private RegistryEntryLookup<Biome> biomeLookup;

  @Inject(method = "bootstrap()V", at = @At("RETURN"))
  private void addWorldPresets(CallbackInfo callbackInfo) {
    Stranded.LOGGER.info("Adding custom world preset for Mars.");

    RegistryEntry<Biome> flatWorldBiome = biomeLookup.getOrThrow(ModBiomes.MARS_BIOME);
    List<FlatChunkGeneratorLayer> layers = new ArrayList<>();
    layers.add(new FlatChunkGeneratorLayer(1, Blocks.BEDROCK));
    layers.add(new FlatChunkGeneratorLayer(60, Blocks.RED_SANDSTONE));
    layers.add(new FlatChunkGeneratorLayer(3, Blocks.RED_SAND));

    FlatChunkGeneratorConfig chunkGeneratorConfig =
      new FlatChunkGeneratorConfig(Optional.empty(), flatWorldBiome, new ArrayList<>());
    chunkGeneratorConfig = chunkGeneratorConfig.with(layers, Optional.empty(), flatWorldBiome);

    register(MARS_WORLD, createOverworldOptions(new FlatChunkGenerator(chunkGeneratorConfig)));
  }
}
