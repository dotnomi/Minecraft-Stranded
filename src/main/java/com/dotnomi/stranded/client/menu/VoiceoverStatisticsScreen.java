package com.dotnomi.stranded.client.menu;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.text.Text;

import java.util.List;

public class VoiceoverStatisticsScreen extends Screen {
  private final Screen parent;
  private List<String> playedVoiceovers; // Eine Liste der abgespielten Voiceovers
  private ElementListWidget<VoiceoverEntry> voiceoverListWidget;

  //private final StrandedWorldState worldState;

  public VoiceoverStatisticsScreen(Screen parent, List<String> playedVoiceovers) {
    super(Text.of("Voiceover Statistics"));
    this.parent = parent;
    this.playedVoiceovers = playedVoiceovers;

    //this.worldState = StrandedWorldStateManager.getStrandedWorldState();
  }

  @Override
  protected void init() {
    super.init();

    // Lade alle freigeschalteten Voiceovers aus dem WorldState
    //this.unlockedVoiceovers = worldState.getUnlockedVoiceovers();

    // Breite und Höhe der Voiceover-Einträge
    int entryWidth = 300;
    int entryHeight = 30;
    int yOffset = 40;

    // Füge für jedes abgespielte Voiceover eine Anzeige hinzu
    /*for (int i = 0; i < unlockedVoiceovers.size(); i++) {
      String voiceoverId = unlockedVoiceovers.get(i);
      String subtitleText = I18n.translate("voiceover." + voiceoverId).replace(Constants.VOICE_OVER_SUBTITLE_SEPERATOR, " ");

      int x = (this.width / 2) - (entryWidth / 2);
      int y = yOffset + (i * (entryHeight + 5));

      // Subtitle Label
      this.addDrawableChild(new LabelWidget(x, y, entryWidth - 60, entryHeight, Text.of(subtitleText)));

      // Play/Pause Button
      this.addDrawableChild(new IconButton(x + entryWidth - 60, y, 20, 20,
        new Identifier(Stranded.MOD_ID, "textures/gui/play_icon.png"),
        button -> togglePlayPause(voiceoverId)
      ));

      // Stop Button
      this.addDrawableChild(new IconButton(x + entryWidth - 30, y, 20, 20,
        new Identifier(Stranded.MOD_ID, "textures/gui/stop_icon.png"),
        button -> stopVoiceover()
      ));
    }*/

    // "Done"-Button
    /*this.addDrawableChild(new ButtonWidget(
      this.width / 2 - 100,
      this.height - 30,
      200,
      20,
      Text.translatable("gui.done"),
      button -> this.client.setScreen(parent),
      null
    ));*/
  }

  private void replayVoiceover(String voiceoverId) {
    //VoiceoverManager.replayVoiceover(voiceoverId);
  }

  private class VoiceoverEntry extends ElementListWidget.Entry<VoiceoverEntry> {
    private final String voiceoverId;

    public VoiceoverEntry(String voiceoverId) {
      this.voiceoverId = voiceoverId;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (button == 0) {
        // TODO: REPLAY VOICEOVER
        return true;
      }
      return false;
    }

    @Override
    public void render(DrawContext drawContext, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
      if (hovered) {
        drawContext.drawText(VoiceoverStatisticsScreen.this.textRenderer, Text.of("Click to replay"), x + 5, y + 15, 0xAAAAAA, true);
      }
    }

    @Override
    public List<? extends Element> children() {
      return List.of();
    }

    @Override
    public List<? extends Selectable> selectableChildren() {
      return List.of();
    }
  }
}
