package com.bconlon.vanillaequals.event.hooks;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.MobVariantAttachment;
import com.bconlon.vanillaequals.entity.passive.CowVariant;
import com.bconlon.vanillaequals.entity.passive.Variant;
import com.bconlon.vanillaequals.network.EqualsPackets;
import com.bconlon.vanillaequals.network.packet.clientbound.SetVariantPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;

public class EntityHooks {
    public static void chooseMobVariant(Mob mob, ServerLevelAccessor level, double x, double y, double z, MobSpawnType spawnType) {
        if (spawnType == MobSpawnType.NATURAL) {
            BlockPos pos = BlockPos.containing(x, y, z);
            Holder<Biome> biome = level.getBiome(pos);
            if (mob.getType() == EntityType.COW) { //todo switch case
                MobVariantAttachment attachment = mob.getData(EqualsAttachments.MOB_VARIANT);
                CowVariant variant = CowVariant.selectVariant(level.getRandom(), biome);
                attachment.setVariant(variant);
            }
        }
    }

    public static void spawnEggDetermineVariant(Mob mob, MobSpawnType spawnType, CompoundTag tag) {
        if (spawnType == MobSpawnType.SPAWN_EGG) {
            MobVariantAttachment attachment = mob.getData(EqualsAttachments.MOB_VARIANT);
            if (tag != null && tag.contains("NaturalVariant")) {
                StringTag stringTag = (StringTag) tag.get("NaturalVariant");
                if (stringTag != null) {
                    String variantName = stringTag.getAsString();
                    if (mob.getType() == EntityType.COW) { //todo switch case
                        CowVariant cowVariant = CowVariant.byName(variantName);
                        attachment.setVariant(cowVariant);
                    }
                }
            }
        }
    }

    public static void syncMobVariant(Entity entity) {
        if (entity.getType() == EntityType.COW) {
            MobVariantAttachment attachment = entity.getData(EqualsAttachments.MOB_VARIANT);
            Variant variant = attachment.getVariant(CowVariant.GET);
            EqualsPackets.sendToAll(new SetVariantPacket.Cow(entity.getId(), variant.getSerializedName()));
        }
    }
}
