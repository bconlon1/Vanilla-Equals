package com.bconlon.vanillaequals.mixin.mixins.client;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.MobVariantAttachment;
import com.bconlon.vanillaequals.client.renderer.EqualsModelLayers;
import com.bconlon.vanillaequals.client.renderer.ModelTypeChoice;
import com.bconlon.vanillaequals.client.renderer.entity.model.DetailedCowModel;
import com.bconlon.vanillaequals.entity.Variant;
import com.bconlon.vanillaequals.entity.passive.variant.CowVariant;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {
    @Unique
    private final Map<EntityType<?>, ModelTypeChoice> modelChoices = new HashMap<>();

    @Shadow
    protected M model;

    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;Lnet/minecraft/client/model/EntityModel;F)V")
    private void init(EntityRendererProvider.Context context, M model, float shadowRadius, CallbackInfo ci) {
        this.modelChoices.put(EntityType.COW, new ModelTypeChoice(new CowModel<>(context.bakeLayer(ModelLayers.COW)), new DetailedCowModel(context.bakeLayer(EqualsModelLayers.COW))));
    }

    @Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V")
    private void render(T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        MobVariantAttachment attachment = entity.getData(EqualsAttachments.MOB_VARIANT);
        Variant variant = null;
        M model = null;
        if (entity.getType() == EntityType.COW) {
            variant = attachment.getVariant(CowVariant.GET);
            model = (M) this.modelChoices.get(EntityType.COW).choose(variant.getModelType());
        }
        if (variant != null && model != null) {
            this.model = model;
        }
    }

    @Inject(at = @At("HEAD"), method = "scale(Lnet/minecraft/world/entity/LivingEntity;Lcom/mojang/blaze3d/vertex/PoseStack;F)V")
    private void scale(T livingEntity, PoseStack poseStack, float partialTickTime, CallbackInfo ci) {
        if (livingEntity.getType() == EntityType.SQUID || livingEntity.getType() == EntityType.GLOW_SQUID) {
            if (livingEntity.isBaby()) {
                poseStack.scale(0.5F, 0.5F, 0.5F);
                poseStack.translate(0.0F, -1.2F, 0.0F);
            }
        }
    }
}
