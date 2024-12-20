package com.dotnomi.stranded.mixin;

import com.dotnomi.stranded.Stranded;
import com.dotnomi.stranded.StrandedClient;
import com.dotnomi.stranded.client.hud.RenderHelper;
import com.dotnomi.stranded.event.handler.KeyInputHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@SuppressWarnings("SameParameterValue")
@Mixin(InGameHud.class)
public class InGameHudMixin {

  @Unique
  private static final Identifier HUD_ELEMENTS_TEXTURE =
    Identifier.of(Stranded.MOD_ID, "textures/gui/voiceover_hud_elements.png");
  @Unique
  private static final int TEXTURE_SIZE = 64;
  @Unique
  private static final int TILE_SIZE = 16;

  @Final
  @Shadow
  private MinecraftClient client;

  @Inject(method = "renderMainHud", at = @At("TAIL"))
  private void addVoiceOverLayer(DrawContext drawContext, RenderTickCounter tickCounter, CallbackInfo ci) {
    if (StrandedClient.VOICEOVER_MANAGER.isVisible()) {
      renderVoiceover(drawContext, 1.0f);
    }
  }

  @Unique
  private void renderVoiceover(DrawContext drawContext, float opacity) {
    RenderSystem.disableDepthTest();
    RenderSystem.depthMask(false);
    RenderSystem.enableBlend();
    drawContext.setShaderColor(1.0F, 1.0F, 1.0F, opacity);
    drawVoiceoverTextField(
      drawContext,
      client.getWindow().getScaledWidth() / 2,
      client.getWindow().getScaledHeight() - 80,
      320,
      1.0f,
      4
    );
    RenderSystem.disableBlend();
    RenderSystem.depthMask(true);
    RenderSystem.enableDepthTest();
    drawContext.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
  }

  @Unique
  private void drawVoiceoverTextField(DrawContext drawContext, int centerX, int centerY, int maxWidth, float scale, int padding) {
    List<OrderedText> lines = client.textRenderer.wrapLines(Text.of(StrandedClient.VOICEOVER_MANAGER.getCurrentSubtitle()), maxWidth);
    MatrixStack matrices = drawContext.getMatrices();
    matrices.push();
    matrices.scale(scale, scale, 1.0f);

    int lineHeight = client.textRenderer.fontHeight;
    int textHeight = Math.max(lines.size()+2, 4) * lineHeight;

    int totalWidth = maxWidth + 2 * padding;
    int totalHeight = textHeight + 2 * padding;

    int startX = (int) ((centerX / scale) - (totalWidth / 2.0));
    int startY = (int) ((centerY / scale) - (totalHeight / 2.0));

    RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, startX, startY, 0, 0, TILE_SIZE, TEXTURE_SIZE);
    RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, startX + totalWidth - TILE_SIZE, startY, 32, 0, TILE_SIZE, TEXTURE_SIZE);
    RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, startX, startY + totalHeight - TILE_SIZE, 0, 32, TILE_SIZE, TEXTURE_SIZE);
    RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, startX + totalWidth - TILE_SIZE, startY + totalHeight - TILE_SIZE, 32, 32, TILE_SIZE, TEXTURE_SIZE);

    for (int x = startX + TILE_SIZE; x < startX + totalWidth - TILE_SIZE; x += TILE_SIZE) {
      RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, x, startY, 16, 0, TILE_SIZE, TEXTURE_SIZE);
      RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, x, startY + totalHeight - TILE_SIZE, 16, 32, TILE_SIZE, TEXTURE_SIZE);
    }

    for (int y = startY + TILE_SIZE; y < startY + totalHeight - TILE_SIZE; y += TILE_SIZE) {
      RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, startX, y, 0, 16, TILE_SIZE, TEXTURE_SIZE);
      RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, startX + totalWidth - TILE_SIZE, y, 32, 16, TILE_SIZE, TEXTURE_SIZE);
    }

    for (int x = startX + TILE_SIZE; x < startX + totalWidth - TILE_SIZE; x += TILE_SIZE) {
      for (int y = startY + TILE_SIZE; y < startY + totalHeight - TILE_SIZE; y += TILE_SIZE) {
        RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, x, y, 16, 16, TILE_SIZE, TEXTURE_SIZE);
      }
    }
    matrices.pop();
    RenderHelper.drawWrappedCenteredText(drawContext, client.textRenderer, StrandedClient.VOICEOVER_MANAGER.getCurrentSubtitle(), centerX, centerY, 320,1.0f, 0x88A3BC, true);

    int skipTextX = (int) (centerX / scale) + totalWidth / 2;
    int skipTextY = startY + totalHeight;

    drawSkipTextField(drawContext, skipTextX - TILE_SIZE / 2, skipTextY, 0.75f, 4);

    RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, centerX - TILE_SIZE, startY - TILE_SIZE / 2, 48, 0, TILE_SIZE, TEXTURE_SIZE);
    RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, centerX, startY - TILE_SIZE / 2, 48, 16, TILE_SIZE, TEXTURE_SIZE);
  }

  @Unique
  private void drawSkipTextField(DrawContext drawContext, int leftX, int centerY, float scale, int padding) {
    String skipVoiceOverText = getSkipVoiceOverText();
    MatrixStack matrices = drawContext.getMatrices();
    matrices.push();
    matrices.scale(scale, scale, 1.0f);

    int textWidth = client.textRenderer.getWidth(skipVoiceOverText);
    double textureStartX = leftX / (double) scale - padding - textWidth;
    double textureEndX = leftX / (double) scale + padding;
    int textureY = (int) (centerY / (double) scale - ((TILE_SIZE + 2) / 2.0));

    int currentTile = 0;
    int numberOfTiles = calculateNumberOfTiles(textWidth, padding, TILE_SIZE);
    for (int i = 0; i < numberOfTiles; i++) {
      if (currentTile == 0) {
        RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, (int) Math.round(textureStartX), textureY, 0, 48, TILE_SIZE, TEXTURE_SIZE);
      } else if (currentTile == numberOfTiles - 1) {
        RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, (int) Math.round(textureEndX - 2 * TILE_SIZE), textureY, 16, 48, TILE_SIZE, TEXTURE_SIZE);
        RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, (int) Math.round(textureEndX - TILE_SIZE), textureY, 32, 48, TILE_SIZE, TEXTURE_SIZE);
      } else {
        RenderHelper.drawTile(drawContext, HUD_ELEMENTS_TEXTURE, (int) Math.round(textureStartX + currentTile * TILE_SIZE), textureY, 16, 48, TILE_SIZE, TEXTURE_SIZE);
      }
      currentTile++;
    }
    matrices.pop();

    RenderHelper.drawVerticalCenteredText(drawContext, client.textRenderer, skipVoiceOverText, (int) Math.round(leftX / (double) scale - textWidth), centerY, scale, 0x88A3BC, true);
  }

  @Unique
  private String getSkipVoiceOverText() {
    String voiceOverKey = KeyInputHandler.getKeyName(KeyInputHandler.skipVoiceOverKey);

    if (voiceOverKey.startsWith("key.keyboard.")) {
      voiceOverKey = voiceOverKey.replace("key.keyboard.", "").toUpperCase();
    }

    return I18n.translate("hud.stranded.skip_voiceover")
      .replace("{key}", voiceOverKey);
  }

  @Unique
  private int calculateNumberOfTiles(int textWidth, int padding, int tileSize) {
    return (int) Math.ceil((double) (textWidth + 2 * padding) / tileSize);
  }
}
