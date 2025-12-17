package com.dudechill8D.skincypher.client;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class CapeTextureBuilder {

    public enum Alignment { SCALE, TOP, MIDDLE, BOTTOM }

    public static final int TEXTURE_WIDTH = 92;

    public static final int TEXTURE_HEIGHT = 44;

    public static NativeImage build(String pattern, int gradientTop, int gradientBottom, Alignment alignment) {
        NativeImage result = new NativeImage(TEXTURE_WIDTH, TEXTURE_HEIGHT, true);




        NativeImage result = null;
        try {
            result = NativeImage.read(MinecraftClient.class.getResourceAsStream("/assets/minecraft/textures/entity/banner/base.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            result.writeTo(new File("/home/hayden/Downloads/MC skin references/CapeTextureBuilder Test/out.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private final NativeImage getBannerTexture(String pattern) {
        try {
            return NativeImage.read(MinecraftClient.class.getResourceAsStream("/assets/minecraft/textures/entity/banner/base.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
