package com.dudechill8D.skincypher.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SkincypherClient implements ClientModInitializer {
    public static final String MOD_ID = "SkinCypher";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing SkinCypher");
        try {
            Path configDir = Files.createDirectories(FabricLoader.getInstance().getConfigDir().resolve(MOD_ID));
            LOGGER.info("===============Config Dir: {}===============", configDir);
        } catch (IOException e) {
            LOGGER.info("===============Config Dir: ERROR===============");
            throw new RuntimeException(e);
        }
    }
}
