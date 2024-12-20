package com.dotnomi.stranded.util;

import com.dotnomi.stranded.Stranded;
import com.dotnomi.stranded.worldgen.biome.ModBiomes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class PlayerTeleporter {
  public static void sendPlayerToMars(ServerPlayerEntity player) {
    MinecraftServer server = player.getServer();
    if (server == null) {
      return;
    }

    RegistryKey<World> marsWorld = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Stranded.MOD_ID, "mars"));
    ServerWorld mars = server.getWorld(marsWorld);
    if (mars == null) {
      return;
    }

    if (player.getWorld() == mars) {
      player.sendMessage(Text.of("HAAAALLLLLOOOO"));
    }

    ServerWorld overworld = server.getWorld(World.OVERWORLD);
    if (overworld == null || player.getWorld() != overworld) {
      return;
    }

    if (overworld.getBiome(player.getBlockPos()) == ModBiomes.MARS_BIOME) {
      player.sendMessage(Text.of("Welcome to Mars!"));
      //
    }
  }
}
