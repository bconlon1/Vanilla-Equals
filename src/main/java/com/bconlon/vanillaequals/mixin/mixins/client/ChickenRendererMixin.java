package com.bconlon.vanillaequals.mixin.mixins.client;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.entity.Variant;
import com.bconlon.vanillaequals.entity.passive.variant.ChickenVariant;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Chicken;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChickenRenderer.class)
public class ChickenRendererMixin {
    @Inject(at = @At("HEAD"), method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Chicken;)Lnet/minecraft/resources/ResourceLocation;", cancellable = true)
    private void getTextureLocation(Chicken chicken, CallbackInfoReturnable<ResourceLocation> cir) {
        if (chicken.hasData(EqualsAttachments.MOB_VARIANT)) {
            Variant variant = chicken.getData(EqualsAttachments.MOB_VARIANT).getVariant(ChickenVariant.GET);
            cir.setReturnValue(variant.getTexture());
        }
    }
}
