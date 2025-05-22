package com.lx862.hitboxforlight.client.gui;

import com.lx862.hitboxforlight.client.gui.widget.WidgetWithChildren;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;

public class GuiHelper {
    public static int MAX_CONTENT_WIDTH = 400;
    public static int BOTTOM_ROW_MARGIN = 6;
    public static int ARGB_WHITE = 0xFFFFFFFF;

    public static boolean inRectangle(double targetX, double targetY, int rectX, int rectY, int rectW, int rectH) {
        return (targetX > rectX && targetX <= rectX + rectW) && (targetY > rectY && targetY <= rectY + rectH);
    }

    public static void scaleToFit(PoseStack poseStack, int targetW, double maxW, boolean keepAspectRatio) {
        scaleToFit(poseStack, targetW, maxW, keepAspectRatio, 0);
    }

    public static void scaleToFit(PoseStack poseStack, double targetW, double maxW, boolean keepAspectRatio, double height) {
        height = height / 2;
        double scaleX = Math.min(1, maxW / targetW);
        if(scaleX < 1) {
            if(keepAspectRatio) {
                poseStack.translate(0, height / 2.0, 0);
                poseStack.scale((float)scaleX, (float)scaleX, (float)scaleX);
                poseStack.translate(0, -height / 2.0, 0);
            } else {
                poseStack.scale((float)scaleX, 1, 1);
            }
        }
    }

    public static void drawRectangle(GuiGraphics guiGraphics, double x, double y, double width, double height, int color) {
        guiGraphics.fill((int)x, (int)y, (int)(x + width), (int)(y + height), color);
    }

    public static void setWidgetVisibility(AbstractWidget widget, boolean bl) {
        if(widget instanceof WidgetWithChildren widgetWithChildren) {
            widgetWithChildren.setVisible(bl);
        } else {
            widget.visible = bl;
        }
    }
}
