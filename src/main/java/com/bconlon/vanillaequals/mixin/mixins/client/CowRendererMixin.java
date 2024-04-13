package com.bconlon.vanillaequals.mixin.mixins.client;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.entity.passive.variant.CowVariant;
import com.bconlon.vanillaequals.entity.Variant;
import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CowRenderer.class)
public class CowRendererMixin {
    @Inject(at = @At("HEAD"), method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Cow;)Lnet/minecraft/resources/ResourceLocation;", cancellable = true)
    private void getTextureLocation(Cow cow, CallbackInfoReturnable<ResourceLocation> cir) {
        if (cow.hasData(EqualsAttachments.MOB_VARIANT)) {
            Variant variant = cow.getData(EqualsAttachments.MOB_VARIANT).getVariant(CowVariant.GET);
            cir.setReturnValue(variant.getTexture());
        }
    }
}
