package com.dotnomi.stranded.sound;

import com.dotnomi.stranded.Stranded;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ModSounds {
  public static final List<SoundEvent> VOICEOVER_LANDING_INTRO = registerVoiceoverSoundEvents("voiceover_landing_intro", 4);

  private static List<SoundEvent> registerVoiceoverSoundEvents(String name, int count) {
    List<SoundEvent> soundEvents = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      soundEvents.add(registerSoundEvent(name + "_" + i));
    }
    return soundEvents;
  }

  private static SoundEvent registerSoundEvent(String name) {
    Identifier id = Identifier.of(Stranded.MOD_ID, name);
    return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
  }

  public static void registerSounds() {
    Stranded.LOGGER.info("Registering mod sounds");
  }
}
