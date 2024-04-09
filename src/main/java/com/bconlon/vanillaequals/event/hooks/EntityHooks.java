package com.bconlon.vanillaequals.event.hooks;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.MobVariantAttachment;
import com.bconlon.vanillaequals.entity.passive.CowVariant;
import com.bconlon.vanillaequals.entity.passive.Variant;
import com.bconlon.vanillaequals.entity.passive.VariantGroupData;
import com.bconlon.vanillaequals.network.EqualsPackets;
import com.bconlon.vanillaequals.network.packet.clientbound.SetVariantPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;

public class EntityHooks {
    public static void spawnEggDetermineVariant(Mob mob, MobSpawnType spawnType, CompoundTag tag) {
        if (spawnType == MobSpawnType.SPAWN_EGG) {
            MobVariantAttachment attachment = mob.getData(EqualsAttachments.MOB_VARIANT);
            if (tag != null && tag.contains("NaturalVariant")) {
                StringTag stringTag = (StringTag) tag.get("NaturalVariant");
                if (stringTag != null) {
                    String variantName = stringTag.getAsString();
                    Variant variant = null;
                    if (mob.getType() == EntityType.COW) { //todo switch case
                        variant = CowVariant.byName(variantName);
                    }
                    if (variant != null) {
                        attachment.setVariant(variant);
                    }
                }
            }
        }
    }

    public static SpawnGroupData chooseMobVariant(Mob mob, ServerLevelAccessor level, double x, double y, double z, MobSpawnType spawnType, SpawnGroupData spawnGroupData) {
        if (spawnType == MobSpawnType.NATURAL || spawnType == MobSpawnType.CHUNK_GENERATION) {
            MobVariantAttachment attachment = mob.getData(EqualsAttachments.MOB_VARIANT);
            BlockPos pos = BlockPos.containing(x, y, z);
            Holder<Biome> biome = level.getBiome(pos);
            Variant variant = null;
            if (mob.getType() == EntityType.COW) { //todo switch case
                variant = CowVariant.selectVariant(level.getRandom(), biome);
            }
            if (variant != null) {
                if (spawnGroupData instanceof VariantGroupData variantGroupData) {
                    variant = variantGroupData.variant;
                } else {
                    spawnGroupData = new VariantGroupData(variant);
                }
                attachment.setVariant(variant);
            }
        }
        return spawnGroupData;
    }

    public static void syncMobVariant(Entity entity) {
        if (entity.getType() == EntityType.COW) {
            MobVariantAttachment attachment = entity.getData(EqualsAttachments.MOB_VARIANT);
            Variant variant = attachment.getVariant(CowVariant.GET);
            EqualsPackets.sendToAll(new SetVariantPacket.Cow(entity.getId(), variant.getSerializedName()));
        }
    }
}
