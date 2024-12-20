package com.dotnomi.stranded.client.voiceover;

import net.minecraft.client.sound.SoundInstance;

public record Voiceover(SoundInstance soundInstance, float duration, Subtitle subtitle) {
}
