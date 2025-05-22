package com.lx862.hitboxforlight.client;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class LoaderImpl {
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static String getModVersion() {
        return FabricLoader.getInstance().getModContainer("hitboxformylightblock").get().getMetadata().getVersion().toString();
    }
}
