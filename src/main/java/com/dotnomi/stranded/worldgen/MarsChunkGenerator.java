/*
package com.dotnomi.stranded.world;

import com.dotnomi.stranded.world.biome.ModBiomes;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.structure.Structure;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MarsChunkGenerator extends ChunkGenerator {

  public MarsChunkGenerator() {
    ///super(new FixedBiomeSource(ModBiomes.MARS_BIOME), new Structure.Config(null));
  }

  @Override
  protected MapCodec<? extends ChunkGenerator> getCodec() {
    return null;
  }

  @Override
  public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk, GenerationStep.Carver carverStep) {

  }

  @Override
  public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {
    BlockPos.Mutable mutable = new BlockPos.Mutable();
    ChunkPos chunkPos = chunk.getPos();

    for (int x = 0; x < 16; x++) {
      for (int z = 0; z < 16; z++) {
        int worldX = chunkPos.getStartX() + x;
        int worldZ = chunkPos.getStartZ() + z;
        int worldY = 64; // Höhe der Oberfläche

        mutable.set(worldX, worldY, worldZ);
        chunk.setBlockState(mutable, Blocks.RED_SAND.getDefaultState(), false);
      }
    }
  }

  @Override
  public void populateEntities(ChunkRegion region) {

  }

  @Override
  public int getWorldHeight() {
    return 0;
  }

  @Override
  public CompletableFuture<Chunk> populateNoise(Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
    return null;
  }

  @Override
  public int getSeaLevel() {
    return 0;
  }

  @Override
  public int getMinimumY() {
    return 0;
  }

  @Override
  public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world, NoiseConfig noiseConfig) {
    return 0;
  }

  @Override
  public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world, NoiseConfig noiseConfig) {
    return null;
  }

  @Override
  public void getDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {

  }
}
*/
