package com.dotnomi.stranded.worldgen.world;

import com.dotnomi.stranded.Stranded;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.WorldPreset;

public class ModWorldPresets {
  public static final RegistryKey<WorldPreset> MARS_WORLD = registerWorldPreset("mars");

  @SuppressWarnings("SameParameterValue")
  private static RegistryKey<WorldPreset> registerWorldPreset(String name) {
    return RegistryKey.of(RegistryKeys.WORLD_PRESET, Identifier.of(Stranded.MOD_ID, name));
  }
}
