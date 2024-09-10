package com.bconlon.vanillaequals.event.listeners;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.event.hooks.EntityHooks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = VanillaEquals.MODID)
public class EntityListeners {
    @SubscribeEvent
    public static void onMobSpawn(FinalizeSpawnEvent event) {
        Mob mob = event.getEntity();
        ServerLevelAccessor level = event.getLevel();
        double x = event.getX();
        double y = event.getY();
        double z = event.getZ();
        MobSpawnType spawnType = event.getSpawnType();
        SpawnGroupData spawnGroupData = event.getSpawnData();
        SpawnGroupData newGroupData = EntityHooks.chooseMobVariant(mob, level, x, y, z, spawnType, spawnGroupData);
        event.setSpawnData(newGroupData);
        EntityHooks.syncMobVariant(mob);
    }

    @SubscribeEvent
    public static void onMobBreed(BabyEntitySpawnEvent event) {
        Mob parentA = event.getParentA();
        Mob parentB = event.getParentB();
        AgeableMob child = event.getChild();
        EntityHooks.spawnOffspring(parentA, parentB, child);
    }

    @SubscribeEvent
    public static void onMobTrack(PlayerEvent.StartTracking event) {
        Entity entity = event.getTarget();
        EntityHooks.syncMobVariant(entity);
    }
}
