package com.dudechill8D.skincypher.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.minecraft.MinecraftProfileTextures;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.texture.PlayerSkinProvider;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.dudechill8D.skincypher.client.SkincypherClient.MOD_ID;
import static com.dudechill8D.skincypher.client.SkincypherClient.LOGGER;

@Mixin(PlayerSkinProvider.class)
public class PlayerSkinProviderMixin {
    @Inject( method = "fetchSkinTextures(Ljava/util/UUID;Lcom/mojang/authlib/minecraft/MinecraftProfileTextures;)Ljava/util/concurrent/CompletableFuture;", at = @At(value = "INVOKE", target = "com/mojang/authlib/minecraft/MinecraftProfileTexture.getMetadata(Ljava/lang/String;)Ljava/lang/String;") )
    private void aName(UUID uuid, MinecraftProfileTextures textures, CallbackInfoReturnable<CompletableFuture<SkinTextures>> cir, @Local(ordinal = 0) CompletableFuture<Identifier> LrCompletableFutureSkinID) throws ExecutionException, InterruptedException {
        Path cypherCache = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID);
        Identifier skinID = LrCompletableFutureSkinID.get();
        LOGGER.info("==============={}===============", skinID);
    }
}
