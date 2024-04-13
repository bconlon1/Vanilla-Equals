package com.bconlon.vanillaequals.mixin.mixins.client;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.entity.Variant;
import com.bconlon.vanillaequals.entity.passive.variant.PigVariant;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Pig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PigRenderer.class)
public class PigRendererMixin {
    @Inject(at = @At("HEAD"), method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Pig;)Lnet/minecraft/resources/ResourceLocation;", cancellable = true)
    private void getTextureLocation(Pig pig, CallbackInfoReturnable<ResourceLocation> cir) {
        if (pig.hasData(EqualsAttachments.MOB_VARIANT)) {
            Variant variant = pig.getData(EqualsAttachments.MOB_VARIANT).getVariant(PigVariant.GET);
            cir.setReturnValue(variant.getTexture());
        }
    }
}
