package com.dotnomi.stranded.mixin;

import com.dotnomi.stranded.Stranded;
import com.dotnomi.stranded.client.menu.IconButton;
import com.dotnomi.stranded.client.menu.VoiceoverStatisticsScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
  @Unique
  private static final Identifier VOICEOVER_ICON =
    Identifier.of(Stranded.MOD_ID, "textures/gui/voiceover_icon.png");

  protected GameMenuScreenMixin(Text title) {
    super(title);
  }

  @Inject(method = "initWidgets", at = @At("TAIL"))
  private void addVoiceoverButton(CallbackInfo ci) {
    if (this.client == null) {
      return;
    }

    IconButton voiceoverIconButton = new IconButton(
      this.width / 2 + 110, this.height / 4 + 8, 20, 20, VOICEOVER_ICON,
      Tooltip.of(Text.of("Voiceover Statistics")),
      buttonWidget -> this.client.setScreen(new VoiceoverStatisticsScreen(this, List.of())),
      buttonWidget -> (net.minecraft.text.MutableText) Text.of("Test Narration"));

    this.addDrawableChild(voiceoverIconButton);
  }
}
