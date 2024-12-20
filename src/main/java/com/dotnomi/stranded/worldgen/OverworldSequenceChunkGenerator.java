package com.dotnomi.stranded.worldgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.FlatChunkGenerator;
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig;

public class OverworldSequenceChunkGenerator extends FlatChunkGenerator {
  public static final MapCodec<OverworldSequenceChunkGenerator> CODEC = RecordCodecBuilder.mapCodec(
    instance -> instance.group(FlatChunkGeneratorConfig.CODEC.fieldOf("settings").forGetter(OverworldSequenceChunkGenerator::getConfig))
      .apply(instance, instance.stable(OverworldSequenceChunkGenerator::new))
  );
  private final FlatChunkGeneratorConfig config;

  public OverworldSequenceChunkGenerator(FlatChunkGeneratorConfig config) {
    super(config);
    this.config = config;
  }

  @Override
  protected MapCodec<? extends ChunkGenerator> getCodec() {
    return CODEC;
  }

  public FlatChunkGeneratorConfig getConfig() {
    return this.config;
  }

  @Override
  public void generateFeatures(StructureWorldAccess world, Chunk chunk, StructureAccessor structureAccessor) {
    super.generateFeatures(world, chunk, structureAccessor);
    world.setBlockState(new BlockPos(0, 75, 0), Blocks.DIAMOND_BLOCK.getDefaultState(), Block.FORCE_STATE);
  }

  private void placeMarker(StructureWorldAccess world, BlockPos blockPosition) {
    Block[] markerBlocks = {
      Blocks.DIAMOND_BLOCK, Blocks.WHITE_WOOL, Blocks.BLACK_WOOL, Blocks.BLACK_WOOL,
      Blocks.WHITE_WOOL, Blocks.BLACK_WOOL, Blocks.WHITE_WOOL, Blocks.WHITE_WOOL, Blocks.BLACK_WOOL
    };

    for (int i = 0; i < markerBlocks.length; i++) {
      BlockPos currentPosition = blockPosition.up(i);
      world.setBlockState(currentPosition, markerBlocks[i].getDefaultState(), 3);
    }
  }
}
