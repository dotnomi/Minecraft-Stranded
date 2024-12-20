package com.dotnomi.stranded.networking.packet;

import com.dotnomi.stranded.Stranded;
import com.dotnomi.stranded.StrandedClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public record PlayVoiceoverS2CPacket(String voiceoverId) implements CustomPayload {
  public static final Id<PlayVoiceoverS2CPacket> IDENTIFIER =
    new Id<>(Identifier.of(Stranded.MOD_ID, "play_voiceover_s2c_packet"));

  public static final PacketCodec<RegistryByteBuf, PlayVoiceoverS2CPacket> CODEC =
    PacketCodec.tuple(
      PacketCodecs.STRING,
      PlayVoiceoverS2CPacket::voiceoverId,
      PlayVoiceoverS2CPacket::new
    );

  @Override
  public Id<? extends CustomPayload> getId() {
    return IDENTIFIER;
  }

  public void sendPacket(ServerPlayerEntity targetedPlayer) {
    ServerPlayNetworking.send(targetedPlayer, this);
  }

  public void handlePacket(ClientPlayNetworking.Context ignored) {
    StrandedClient.VOICEOVER_MANAGER.playVoiceover(voiceoverId);
  }
}
