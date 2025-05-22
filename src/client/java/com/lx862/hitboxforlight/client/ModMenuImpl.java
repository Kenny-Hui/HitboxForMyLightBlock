package com.lx862.hitboxforlight.client;

import com.lx862.hitboxforlight.client.gui.screen.ClientConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (screen) -> new ClientConfigScreen().withPreviousScreen(screen);
    }
}
