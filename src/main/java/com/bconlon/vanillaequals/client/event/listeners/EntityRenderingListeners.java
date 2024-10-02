package com.bconlon.vanillaequals.client.event.listeners;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.client.event.hooks.EntityRenderingHooks;
import com.bconlon.vanillaequals.mixin.mixins.client.accessor.LivingEntityRendererAccessor;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;

public class EntityRenderingListeners {
    @EventBusSubscriber(modid = VanillaEquals.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class Mod {
        @SubscribeEvent
        public static void onRegisterWithContext(EntityRenderersEvent.AddLayers event) {
            EntityRendererProvider.Context context = event.getContext();
            EntityRenderingHooks.setupModelChoices(context);
        }
    }

    @EventBusSubscriber(modid = VanillaEquals.MODID, value = Dist.CLIENT)
    public static class Game {
        @SubscribeEvent
        public static <T extends LivingEntity, M extends EntityModel<T>> void onPreRenderLiving(RenderLivingEvent.Pre<?, ?> event) {
            LivingEntity entity = event.getEntity();
            LivingEntityRendererAccessor<T, M> rendererAccessor = (LivingEntityRendererAccessor<T, M>) event.getRenderer();
            EntityRenderingHooks.chooseModelVariant(entity, rendererAccessor);
        }
    }
}
