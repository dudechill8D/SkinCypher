package com.dudechill8D.skincypher.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.SignatureState;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTextures;
import net.fabricmc.fabric.mixin.networking.client.accessor.MinecraftClientAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.PlayerSkinProvider;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.io.File;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.dudechill8D.skincypher.client.SkincypherClient.MOD_ID;
import static com.dudechill8D.skincypher.client.SkincypherClient.LOGGER;

// in the store method, completableFutureSkinID.thenAccept(skinID -> { /* read cypher and write to cache */ });



@Mixin(PlayerSkinProvider.class)
public class PlayerSkinProviderMixin {
    @Inject( method = "fetchSkinTextures(Ljava/util/UUID;Lcom/mojang/authlib/minecraft/MinecraftProfileTextures;)Ljava/util/concurrent/CompletableFuture;", at = @At(value = "INVOKE", target = "com/mojang/authlib/minecraft/MinecraftProfileTexture.getMetadata(Ljava/lang/String;)Ljava/lang/String;") )
    private void aName(UUID uuid, MinecraftProfileTextures textures, CallbackInfoReturnable<CompletableFuture<SkinTextures>> cir, @Local(ordinal = 0) CompletableFuture<Identifier> completableFutureSkinID) throws ExecutionException, InterruptedException {
        completableFutureSkinID.thenAccept(skinID -> {
            LOGGER.info("===============skinID: {}===============", skinID);
            LOGGER.info("===============skinID.hashCode(): {}===============", skinID.hashCode());
            if (textures.skin() != null) {
                LOGGER.info("===============textures.skin().getHash(): {}===============", textures.skin().getHash());
                LOGGER.info("===============textures.skin().hashCode(): {}===============", textures.skin().hashCode());
            }
            Path cypherCacheDir = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID);
            LOGGER.info("===============cypherCacheDir: {}===============", cypherCacheDir);
            Path gameDir = FabricLoader.getInstance().getGameDir();
            LOGGER.info("===============gameDir: {}===============", gameDir);

        });
    }

    @ModifyArgs(method = "fetchSkinTextures(Ljava/util/UUID;Lcom/mojang/authlib/minecraft/MinecraftProfileTextures;)Ljava/util/concurrent/CompletableFuture;", at = @At(value = "INVOKE", target = "com/mojang/authlib/minecraft/MinecraftProfileTexture.getMetadata(Ljava/lang/String;)Ljava/lang/String;") )
    private void anotherName(Args args) {
        MinecraftProfileTextures original = args.get(0);
        MinecraftProfileTexture skin = original.skin();
        MinecraftProfileTexture cape = original.cape();
        MinecraftProfileTexture elytra = new MinecraftProfileTexture("https://optifine.net/capes/KlexiKeks.png", null);
        args.set(0, new MinecraftProfileTextures(skin, cape, elytra, SignatureState.SIGNED));
    }
}
