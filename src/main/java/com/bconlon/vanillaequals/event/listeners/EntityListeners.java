package com.bconlon.vanillaequals.event.listeners;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.event.hooks.EntityHooks;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = VanillaEquals.MODID)
public class EntityListeners {
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        EntityHooks.addGoals(entity);
    }

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
        Player player = event.getEntity();
        Entity entity = event.getTarget();
        EntityHooks.syncMobVariant(entity);
        EntityHooks.syncLivestockData(player, entity);
    }
}
