package com.dotnomi.stranded.client.hud;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class RenderHelper {
  public static void drawCenteredText(DrawContext drawContext, TextRenderer textRenderer, String text, int centerX, int centerY, float scale, int color, boolean shadow) {
    double unscaledTextWidth = textRenderer.getWidth(text);
    double unscaledTextHeight = textRenderer.fontHeight;

    double textX = centerX / (double) scale - (unscaledTextWidth / 2.0);
    double textY = centerY / (double) scale - (unscaledTextHeight / 2.0);

    MatrixStack matrices = drawContext.getMatrices();
    matrices.push();
    matrices.scale(scale, scale, 1.0f);
    drawContext.drawText(textRenderer, Text.of(text), (int) Math.round(textX), (int) Math.round(textY), color, shadow);
    matrices.pop();
  }

  public static void drawVerticalCenteredText(DrawContext drawContext, TextRenderer textRenderer, String text, int startX, int centerY, float scale, int color, boolean shadow) {
    double unscaledTextHeight = textRenderer.fontHeight;
    double textY = centerY / (double) scale - (unscaledTextHeight / 2.0);

    MatrixStack matrices = drawContext.getMatrices();
    matrices.push();
    matrices.scale(scale, scale, 1.0f);
    drawContext.drawText(textRenderer, Text.of(text), startX, (int) Math.round(textY), color, shadow);
    matrices.pop();
  }

  public static void drawWrappedCenteredText(DrawContext drawContext, TextRenderer textRenderer, String text, int centerX, int centerY, int maxWidth, float scale, int color, boolean shadow) {
    List<OrderedText> lines = textRenderer.wrapLines(Text.of(text), maxWidth);

    int lineHeight = textRenderer.fontHeight;
    int totalHeight = lines.size() * lineHeight;

    double startY = centerY / (double) scale - (totalHeight / 2.0);
    int currentY = (int) startY;
    for (OrderedText line : lines) {
      double textX = centerX / (double) scale - (textRenderer.getWidth(line) / 2.0);

      drawContext.drawText(textRenderer, line, (int) textX, currentY, color, shadow);
      currentY += lineHeight;
    }
  }

  public static void drawTile(DrawContext drawContext, Identifier texture, int x, int y, int u, int v, int tileSize, int textureSize) {
    drawContext.drawTexture(texture, x, y, u, v, tileSize, tileSize, textureSize, textureSize);
  }
}
