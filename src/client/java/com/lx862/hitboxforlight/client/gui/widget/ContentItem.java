package com.lx862.hitboxforlight.client.gui.widget;

import com.lx862.hitboxforlight.client.gui.GuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.MutableComponent;

import java.util.Objects;

import static com.lx862.hitboxforlight.client.gui.widget.ListViewWidget.ENTRY_PADDING;

/**
 * Represent a row in {@link ListViewWidget}
 */
public class ContentItem extends AbstractListItem {
    public final MutableComponent title;
    public final AbstractWidget widget;

    public ContentItem(MutableComponent title, AbstractWidget widget, int height) {
        super(height);
        this.title = title;
        this.widget = widget;
    }

    /**
     * Create a new content item
     * @param title The main text that will appear on the left side
     * @param widget The widget to be placed on the right side. Note that it shall not be added as a renderable widget, as this list content will manually render the widget.
     */
    public ContentItem(MutableComponent title, AbstractWidget widget) {
        this(title, widget, 22);
    }

    /* */
    public void draw(GuiGraphics guiGraphics, int entryX, int entryY, int width, int height, int mouseX, int mouseY, boolean widgetVisible, double elapsed, float tickDelta) {
        super.draw(guiGraphics, entryX, entryY, width, height, mouseX, mouseY, widgetVisible, elapsed, tickDelta);
        drawListEntry(guiGraphics, entryX, entryY, width, mouseX, mouseY, widgetVisible, elapsed, tickDelta);
    }

    @Override
    public boolean matchQuery(String searchTerm) {
        return Objects.equals(searchTerm, "") || (title != null && title.getString().contains(searchTerm));
    }

    @Override
    public void positionChanged(int entryX, int entryY) {
        if(widget != null) {
            int offsetY = (height - widget.getHeight()) / 2;
            widget.setX(entryX - widget.getWidth());
            widget.setY(entryY + offsetY);
        }
    }

    @Override
    public void hidden() {
        if(widget != null) {
            GuiHelper.setWidgetVisibility(widget, false);
        }
    }

    @Override
    public void shown() {
        if(widget != null) {
            GuiHelper.setWidgetVisibility(widget, true);
        }
    }

    private void drawListEntry(GuiGraphics guiGraphics, int entryX, int entryY, int width, int mouseX, int mouseY, boolean widgetVisible, double elapsed, float tickDelta) {
        if(title != null) drawListEntryDescription(guiGraphics, entryX, entryY, width, elapsed);

        if(widget != null) {
            GuiHelper.setWidgetVisibility(widget, widgetVisible);
            // Manually draw the widget to ensure it conforms to our clip area
            widget.render(guiGraphics, mouseX, mouseY, tickDelta);
        }
    }

    private void drawListEntryDescription(GuiGraphics guiGraphics, int entryX, int entryY, int width, double elapsed) {
        int textHeight = 9;
        int textY = (height / 2) - (textHeight / 2);

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(entryX, entryY, 0);
        guiGraphics.pose().translate(ENTRY_PADDING, 0, 0);
        guiGraphics.drawString(Minecraft.getInstance().font, title, 0 , textY, 0xFFDDDDDD, true);
        guiGraphics.pose().popPose();
    }
}
