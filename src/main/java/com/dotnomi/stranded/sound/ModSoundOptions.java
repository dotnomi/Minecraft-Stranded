package com.dotnomi.stranded.sound;

import com.dotnomi.stranded.Stranded;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;

import java.util.function.Consumer;

@SuppressWarnings("SameParameterValue")
public class ModSoundOptions {
  public static final SimpleOption<Double> VOICEOVER_VOLUME = registerSoundOption("voiceover_volume", volume -> {
    MinecraftClient.getInstance().getSoundManager().updateSoundVolume(SoundCategory.valueOf("voiceover"), volume.floatValue());
  });

  private static SimpleOption<Double> registerSoundOption(String name, Consumer<Double> changeCallback) {
    String translationKey = "options." + Stranded.MOD_ID + "." + name;
    return new SimpleOption<>(
      translationKey,
      SimpleOption.emptyTooltip(),
      (optionText, value) -> Text.of(I18n.translate(translationKey).replace("{value}", String.valueOf((int) (value * 100)))),
      SimpleOption.DoubleSliderCallbacks.INSTANCE,
      1.0,
      changeCallback
    );
  }
}
