package com.dotnomi.stranded.worldgen.dimension;

import com.dotnomi.stranded.Stranded;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {
  public static final RegistryKey<DimensionOptions> MARS_DIMENSION_OPTIONS = RegistryKey.of(RegistryKeys.DIMENSION,
    Identifier.of(Stranded.MOD_ID, "mars"));
  public static final RegistryKey<World> MARS_LEVEL = RegistryKey.of(RegistryKeys.WORLD,
    Identifier.of(Stranded.MOD_ID, "mars"));
  public static final RegistryKey<DimensionType> MARS_DIMENSION_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
    Identifier.of(Stranded.MOD_ID, "mars"));

  public static void bootstrapType(Registerable<DimensionType> context) {
    context.register(MARS_DIMENSION_TYPE, new DimensionType(
      OptionalLong.empty(), // fixedTime
      true, // hasSkylight
      false, // hasCeiling
      false, // ultraWarm
      true, // natural
      1.0, // coordinateScale
      true, // bedWorks
      false, // respawnAnchorWorks
      -64, // minY
      320, // height
      320, // logicalHeight
      BlockTags.INFINIBURN_OVERWORLD, // infiniburn
      DimensionTypes.OVERWORLD_ID, // effectsLocation
      1.0f, // ambientLight
      new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
  }
}
