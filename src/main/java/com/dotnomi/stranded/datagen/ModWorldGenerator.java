package com.dotnomi.stranded.datagen;

import com.dotnomi.stranded.Stranded;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModWorldGenerator extends FabricDynamicRegistryProvider {
  public ModWorldGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
    super(output, registriesFuture);
  }

  @Override
  protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
    entries.addAll(registries.getWrapperOrThrow(RegistryKeys.BIOME));
    entries.addAll(registries.getWrapperOrThrow(RegistryKeys.DIMENSION_TYPE));
    entries.addAll(registries.getWrapperOrThrow(RegistryKeys.CHUNK_GENERATOR));
    entries.addAll(registries.getWrapperOrThrow(RegistryKeys.WORLD_PRESET));
  }

  @Override
  public String getName() {
    return "World Generator";
  }
}
