package com.lx862.hitboxforlight.client.gui.screen.base;

import com.lx862.hitboxforlight.client.gui.GuiHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public abstract class TitledScreen extends ScreenBase {
    public static final int TEXT_PADDING = 10;
    public static final int TITLE_SCALE = 2;

    public TitledScreen(Component title) {
        super(title);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float tickDelta) {
        drawCustomBackground(guiGraphics, mouseX, mouseY, tickDelta);
        drawTitle(guiGraphics);
        drawSubtitle(guiGraphics);

        guiGraphics.fill(0, 0, width, getStartY(), 0x66000000);

        RenderSystem.enableBlend();
        guiGraphics.blit(HEADER_SEPARATOR, 0, getStartY(), 0, 0, width, 2, 1, 2);
        RenderSystem.disableBlend();
    }

    public void drawCustomBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float tickDelta) {
        super.renderBackground(guiGraphics, mouseX, mouseY, tickDelta);
    }

    private void drawTitle(GuiGraphics guiGraphics) {
        final PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.translate(width / 2.0, TEXT_PADDING, 0);
        poseStack.scale(TITLE_SCALE, TITLE_SCALE, TITLE_SCALE);
        GuiHelper.scaleToFit(poseStack, font.width(getTitle()), width / (float)TITLE_SCALE, true);
        guiGraphics.drawCenteredString(font, getTitle(), 0, 0, 0xFFFFFFFF);
        poseStack.popPose();
    }

    private void drawSubtitle(GuiGraphics guiGraphics) {
        double titleHeight = (font.lineHeight * TITLE_SCALE);
        Component subtitleText = getScreenSubtitle();
        final PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.translate(width / 2.0, titleHeight * 1, 0);
        poseStack.translate(0, TEXT_PADDING * 1.5, 0);
        GuiHelper.scaleToFit(poseStack, font.width(subtitleText), width, true);
        guiGraphics.drawCenteredString(font, subtitleText, 0, 0, 0xFFFFFFFF);
        poseStack.popPose();
    }

    /**
     * @return Return the Y coordinate that is below the title and subtitle
     */
    protected int getStartY() {
        double titleHeight = font.lineHeight * TITLE_SCALE;
        double subtitleHeight = font.lineHeight + (TEXT_PADDING);
        return TEXT_PADDING + (int)(titleHeight + subtitleHeight);
    }

    public abstract Component getScreenSubtitle();
}
