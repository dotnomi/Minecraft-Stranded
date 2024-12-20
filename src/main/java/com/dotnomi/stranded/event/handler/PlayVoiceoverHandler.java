package com.dotnomi.stranded.event.handler;

import com.dotnomi.stranded.data.StrandedWorldState;
import com.dotnomi.stranded.data.StrandedWorldStateManager;
import com.dotnomi.stranded.event.PlayVoiceoverEvent;
import com.dotnomi.stranded.networking.packet.PlayVoiceoverS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.world.ServerWorld;

public class PlayVoiceoverHandler implements PlayVoiceoverEvent {
  @Override
  public void onPlayVoiceover(ServerWorld world, String voiceoverId) {
    StrandedWorldState worldState = StrandedWorldStateManager.getStrandedWorldState(world);

    if (!worldState.isVoiceoverUnlocked(voiceoverId)) {
      worldState.addUnlockedVoiceover(voiceoverId);
    }

    world.getServer().getPlayerManager().getPlayerList().forEach(player -> {
      ServerPlayNetworking.send(player, new PlayVoiceoverS2CPacket(voiceoverId));
    });
  }
}
