package com.bconlon.vanillaequals.client.renderer;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.client.renderer.entity.model.DetailedCowModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod.EventBusSubscriber(modid = VanillaEquals.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EqualsRenderers {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EqualsModelLayers.COW, DetailedCowModel::createBodyLayer);
    }
}
