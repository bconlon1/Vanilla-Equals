package com.bconlon.vanillaequals.event.listeners;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.event.hooks.EntityHooks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod.EventBusSubscriber(modid = VanillaEquals.MODID)
public class EntityListeners {
    @SubscribeEvent
    public static void onMobSpawn(MobSpawnEvent.FinalizeSpawn event) {
        Mob mob = event.getEntity();
        ServerLevelAccessor level = event.getLevel();
        double x = event.getX();
        double y = event.getY();
        double z = event.getZ();
        MobSpawnType spawnType = event.getSpawnType();
        SpawnGroupData spawnGroupData = event.getSpawnData();
        CompoundTag tag = event.getSpawnTag();
        EntityHooks.spawnEggDetermineVariant(mob, spawnType, tag);
        SpawnGroupData newGroupData = EntityHooks.chooseMobVariant(mob, level, x, y, z, spawnType, spawnGroupData);
        newGroupData = EntityHooks.determineSpawnAge(mob, level, spawnType, newGroupData);
        event.setSpawnData(newGroupData);
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
        EntityHooks.syncMobAge(entity);
    }

    @SubscribeEvent
    public static void onMobTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        EntityHooks.tickAge(entity);
    }
}
