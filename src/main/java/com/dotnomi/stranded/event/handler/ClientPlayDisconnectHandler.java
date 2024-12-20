package com.dotnomi.stranded.event.handler;

import com.dotnomi.stranded.StrandedClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ClientPlayDisconnectHandler implements ClientPlayConnectionEvents.Disconnect {
  @Override
  public void onPlayDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
    StrandedClient.VOICEOVER_MANAGER.stopVoiceover();
  }
}
