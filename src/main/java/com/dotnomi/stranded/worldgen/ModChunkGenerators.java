package com.dotnomi.stranded.worldgen;

import com.dotnomi.stranded.Stranded;
import com.mojang.serialization.MapCodec;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class ModChunkGenerators {
  public static final RegistryKey<MapCodec<? extends ChunkGenerator>> OVERWORLD_SEQUENCE_GENERATOR = registerChunkGenerator("overworld-sequence-generator");

  public static void registerChunkGenerators(Registerable<MapCodec<? extends ChunkGenerator>> context) {
    MapCodec<? extends ChunkGenerator> mapCodec = OverworldSequenceChunkGenerator.CODEC;
    context.register(OVERWORLD_SEQUENCE_GENERATOR, mapCodec);
  }

  @SuppressWarnings("SameParameterValue")
  private static RegistryKey<MapCodec<? extends ChunkGenerator>> registerChunkGenerator(String name) {
    Registry.register(Registries.CHUNK_GENERATOR, Identifier.of(Stranded.MOD_ID, name), OverworldSequenceChunkGenerator.CODEC);
    return RegistryKey.of(RegistryKeys.CHUNK_GENERATOR, Identifier.of(Stranded.MOD_ID, name));
  }
}
