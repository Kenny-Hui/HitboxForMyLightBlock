package com.lx862.hitboxforlight.client;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import net.minecraft.client.Minecraft;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;

import java.io.*;
import java.nio.file.Path;

public class ClientConfig {
    private static final Path configDirectory = LoaderImpl.getConfigDirectory().resolve("hitboxformylightblock.json");
    private boolean showHitboxInCreative = true;

    public void readOrCreateConfig() {
        try {
            read();
        } catch (FileNotFoundException e) {
            write();
        }
    }

    public void read() throws FileNotFoundException {
        HitboxForMyLightBlockClient.LOGGER.info("Reading Config...");
        JsonElement jsonElement = JsonParser.parseReader(new JsonReader(new FileReader(configDirectory.toFile())));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        showHitboxInCreative = GsonHelper.getAsBoolean(jsonObject, "showHitboxInCreative", false);
    }

    public void write() {
        HitboxForMyLightBlockClient.LOGGER.info("Writing Config...");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("showHitboxInCreative", showHitboxInCreative);
        try(Writer writer = new FileWriter(configDirectory.toFile())) {
            new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject, writer);
        } catch (IOException e) {
            HitboxForMyLightBlockClient.LOGGER.error("Failed to write config file!", e);
        }
    }

    public boolean showHitboxInCreative() {
        return this.showHitboxInCreative;
    }

    public void setShowHitboxInCreative(boolean bl) {
        this.showHitboxInCreative = bl;
    }

    public boolean shouldShowHitbox(Item itemHolding) {
        return Minecraft.getInstance().player.isCreative() && this.showHitboxInCreative;
    }
}
