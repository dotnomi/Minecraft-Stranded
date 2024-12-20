package com.dotnomi.stranded.event.handler;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class PlayerJoinHandler implements ServerPlayConnectionEvents.Join, ServerEntityWorldChangeEvents.AfterPlayerChange {
  @Override
  public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
    ServerPlayerEntity player = handler.player;
    if (player == null) {
      return;
    }
  }

  @Override
  public void afterChangeWorld(ServerPlayerEntity player, ServerWorld origin, ServerWorld destination) {

  }


}
