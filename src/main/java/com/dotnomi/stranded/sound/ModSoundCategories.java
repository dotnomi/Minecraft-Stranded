package com.dotnomi.stranded.sound;

import net.minecraft.sound.SoundCategory;

public class ModSoundCategories {
  private static ModSoundCategories instance;

  private static SoundCategory voiceoverSoundCategory;

  private ModSoundCategories() {}

  public static ModSoundCategories getInstance() {
    if (instance == null) {
      instance = new ModSoundCategories();
    }
    return instance;
  }

  public void setVoiceoverSoundCategory(SoundCategory voiceoverSoundCategory) {
    ModSoundCategories.voiceoverSoundCategory = voiceoverSoundCategory;
  }

  public SoundCategory getVoiceoverSoundCategory() {
    return voiceoverSoundCategory;
  }
}
