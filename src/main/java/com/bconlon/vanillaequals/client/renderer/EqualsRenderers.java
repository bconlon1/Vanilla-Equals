package com.bconlon.vanillaequals.client.renderer;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.client.renderer.entity.model.DetailedCowModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = VanillaEquals.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class EqualsRenderers {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EqualsModelLayers.COW, DetailedCowModel::createBodyLayer);
    }
}
