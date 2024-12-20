package com.dotnomi.stranded.client.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class IconButton extends ButtonWidget {
  private final Identifier iconTexture;

  public IconButton(int x, int y, int width, int height, Identifier iconTexture, Tooltip tooltip, PressAction onPress, NarrationSupplier narrationSupplier) {
    super(x, y, width, height, Text.empty(), onPress, narrationSupplier);
    this.setTooltip(tooltip);
    this.iconTexture = iconTexture;
  }

  @Override
  protected void renderWidget(DrawContext drawContext, int mouseX, int mouseY, float delta) {
    super.renderWidget(drawContext, mouseX, mouseY, delta);
    RenderSystem.setShaderTexture(0, iconTexture);
    int iconSize = Math.min(this.width, this.height) - 4;

    RenderSystem.setShaderColor(0.0f, 0.0f, 0.0f, 0.5f);
    drawContext.drawTexture(iconTexture, this.getX() + 3, this.getY() + 3, 0, 0, iconSize, iconSize, 16, 16);

    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    drawContext.drawTexture(iconTexture, this.getX() + 2, this.getY() + 2, 0, 0, iconSize, iconSize, 16, 16);
  }
}
