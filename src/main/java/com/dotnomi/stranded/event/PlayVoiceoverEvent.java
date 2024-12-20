package com.dotnomi.stranded.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.world.ServerWorld;

public interface PlayVoiceoverEvent {
  Event<PlayVoiceoverEvent> EVENT = EventFactory.createArrayBacked(PlayVoiceoverEvent.class,
    (listeners) -> (world, voiceoverId) -> {
      for (PlayVoiceoverEvent listener : listeners) {
        listener.onPlayVoiceover(world, voiceoverId);
      }
    });

  void onPlayVoiceover(ServerWorld world, String voiceoverId);
}
