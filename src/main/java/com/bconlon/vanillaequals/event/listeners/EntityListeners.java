package com.bconlon.vanillaequals.event.listeners;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.event.hooks.EntityHooks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod.EventBusSubscriber(modid = VanillaEquals.MODID)
public class EntityListeners {
    @SubscribeEvent
    public static void onMobSpawn(MobSpawnEvent.FinalizeSpawn event) { //todo might need to make this work with groups properly to make variants spawn in groups.
        Mob mob = event.getEntity();
        ServerLevelAccessor level = event.getLevel();
        double x = event.getX();
        double y = event.getY();
        double z = event.getZ();
        MobSpawnType spawnType = event.getSpawnType();
        CompoundTag tag = event.getSpawnTag();
        EntityHooks.chooseMobVariant(mob, level, x, y, z, spawnType);
        EntityHooks.spawnEggDetermineVariant(mob, spawnType, tag);
    }

    @SubscribeEvent
    public static void onMobTrack(PlayerEvent.StartTracking event) {
        Entity entity = event.getTarget();
        EntityHooks.syncMobVariant(entity);
    }
}
