package com.dudechill8D.skincypher.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.PlayerSkinTextureDownloader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;

import static com.dudechill8D.skincypher.client.SkincypherClient.LOGGER;

@Mixin(PlayerSkinTextureDownloader.class)
public class PlayerSkinTextureDownloaderMixin {
    @Inject(method = "download", at = @At(value = "RETURN"))
    private static void aName(Path path, String uri, CallbackInfoReturnable<NativeImage> cir, @Local(ordinal = 0) NativeImage nativeImage) {
        if (nativeImage.getWidth() == 64 && nativeImage.getHeight() == 64) {
            LOGGER.info("===============Pixel 0 0: {}===============", Integer.toHexString(nativeImage.getColorArgb(0, 0)));
            LOGGER.info("===============Pixel 8 0: {}===============", Integer.toHexString(nativeImage.getColorArgb(8, 0)));
        }
    }
}
