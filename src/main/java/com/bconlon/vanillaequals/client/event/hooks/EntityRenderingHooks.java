package com.bconlon.vanillaequals.client.event.hooks;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.MobVariantAttachment;
import com.bconlon.vanillaequals.client.renderer.EqualsModelLayers;
import com.bconlon.vanillaequals.client.renderer.ModelTypeChoice;
import com.bconlon.vanillaequals.client.renderer.entity.model.DetailedCowModel;
import com.bconlon.vanillaequals.entity.Variant;
import com.bconlon.vanillaequals.entity.passive.variant.CowVariant;
import com.bconlon.vanillaequals.mixin.mixins.client.accessors.LivingEntityRendererAccessor;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

public class EntityRenderingHooks {
    private static final Map<EntityType<?>, ModelTypeChoice> MODEL_CHOICES = new HashMap<>();

    public static void setupModelChoices(EntityRendererProvider.Context context) {
        MODEL_CHOICES.put(EntityType.COW, new ModelTypeChoice(new CowModel<>(context.bakeLayer(ModelLayers.COW)), new DetailedCowModel(context.bakeLayer(EqualsModelLayers.COW))));
    }

    public static <T extends LivingEntity, M extends EntityModel<T>> void chooseModelVariant(LivingEntity entity, LivingEntityRendererAccessor<T, M> rendererAccessor) {
        MobVariantAttachment attachment = entity.getData(EqualsAttachments.MOB_VARIANT);
        Variant variant = null;
        M model = null;
        if (entity.getType() == EntityType.COW) {
            variant = attachment.getVariant(CowVariant.GET);
            model = (M) MODEL_CHOICES.get(EntityType.COW).choose(variant.getModelType());
        }
        if (variant != null && model != null) {
            rendererAccessor.vanilla_equals$setModel(model);
        }
    }
}
