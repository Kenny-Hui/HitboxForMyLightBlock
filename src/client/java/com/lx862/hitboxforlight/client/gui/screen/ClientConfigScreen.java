package com.lx862.hitboxforlight.client.gui.screen;

import com.lx862.hitboxforlight.client.ClientConfig;
import com.lx862.hitboxforlight.client.HitboxForMyLightBlockClient;
import com.lx862.hitboxforlight.client.LoaderImpl;
import com.lx862.hitboxforlight.client.gui.GuiHelper;
import com.lx862.hitboxforlight.client.gui.screen.base.TitledScreen;
import com.lx862.hitboxforlight.client.gui.widget.ListViewWidget;
import com.lx862.hitboxforlight.client.gui.widget.WidgetSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.network.chat.Component;

public class ClientConfigScreen extends TitledScreen {
    private final WidgetSet bottomRowWidget;
    private final ListViewWidget listViewWidget;
    private final Checkbox showInCreativeCheckbox;

    public ClientConfigScreen() {
        super(Component.translatable("gui.hitboxformylightblock.brand"));
        bottomRowWidget = new WidgetSet(20);
        listViewWidget = new ListViewWidget();

        this.showInCreativeCheckbox = Checkbox.builder(Component.empty(), Minecraft.getInstance().font).selected(HitboxForMyLightBlockClient.getConfig().showHitboxInCreative()).pos(0, 0).onValueChange((cb, bl) -> {
            ClientConfig config = HitboxForMyLightBlockClient.getConfig();
            config.setShowHitboxInCreative(bl);
        }).build();
    }

    @Override
    public Component getScreenSubtitle() {
        return Component.translatable("gui.hitboxformylightblock.version", LoaderImpl.getModVersion());
    }

    @Override
    protected void init() {
        super.init();
        listViewWidget.reset();
        bottomRowWidget.reset();

        int contentWidth = (int)Math.min((width * 0.75), GuiHelper.MAX_CONTENT_WIDTH);
        int listViewHeight = (int)((height - 60) * 0.75);
        int startX = (width - contentWidth) / 2;
        int startY = getStartY() + (TEXT_PADDING);

        int bottomEntryHeight = (height - startY - listViewHeight - (GuiHelper.BOTTOM_ROW_MARGIN * 2));

        addConfigEntries();
        addBottomButtons();
        addRenderableWidget(listViewWidget);
        addRenderableWidget(bottomRowWidget);
        listViewWidget.setXYSize(startX, startY, contentWidth, listViewHeight);
        bottomRowWidget.setXYSize(startX, startY + listViewHeight + GuiHelper.BOTTOM_ROW_MARGIN, contentWidth, bottomEntryHeight);
    }

    private void addConfigEntries() {
        // General
        listViewWidget.addCategory(Component.translatable("gui.hitboxformylightblock.config.listview.category.general"));
        listViewWidget.add(Component.translatable("gui.hitboxformylightblock.config.listview.title.creative_button"), showInCreativeCheckbox);
        addWidget(showInCreativeCheckbox);
    }


    private void addBottomButtons() {
        Button doneButton = Button.builder(Component.translatable("gui.done"), (btn) -> {
            onClose();
        }).size(0, 20).build();

        addRenderableWidget(doneButton);
        bottomRowWidget.addWidget(doneButton);
    }

    @Override
    public void onClose() {
        HitboxForMyLightBlockClient.getConfig().write();
        super.onClose();
    }
}
