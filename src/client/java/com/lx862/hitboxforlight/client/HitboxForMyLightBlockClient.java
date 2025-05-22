package com.lx862.hitboxforlight.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HitboxForMyLightBlockClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("HitboxForMyLightBlock");
    private static ClientConfig config = new ClientConfig();

    @Override
    public void onInitializeClient() {
        config.readOrCreateConfig();
    }

    public static ClientConfig getConfig() {
        return config;
    }
}
