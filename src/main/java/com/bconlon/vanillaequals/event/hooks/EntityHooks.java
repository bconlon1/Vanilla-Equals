package com.bconlon.vanillaequals.event.hooks;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.attachment.AgeableAttachment;
import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.MobVariantAttachment;
import com.bconlon.vanillaequals.entity.Variant;
import com.bconlon.vanillaequals.entity.VariantGroupData;
import com.bconlon.vanillaequals.entity.passive.variant.ChickenVariant;
import com.bconlon.vanillaequals.entity.passive.variant.CowVariant;
import com.bconlon.vanillaequals.entity.passive.variant.PigVariant;
import com.bconlon.vanillaequals.network.EqualsPackets;
import com.bconlon.vanillaequals.network.packet.clientbound.SetAgePacket;
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
                    if (mob.getType() == EntityType.CHICKEN) {
                        variant = ChickenVariant.byName(variantName);
                    } else if (mob.getType() == EntityType.COW) {
                        variant = CowVariant.byName(variantName);
                    } else if (mob.getType() == EntityType.PIG) {
                        variant = PigVariant.byName(variantName);
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
            if (mob.getType() == EntityType.CHICKEN) {
                variant = ChickenVariant.selectVariant(level.getRandom(), biome);
            } else if (mob.getType() == EntityType.COW) {
                variant = CowVariant.selectVariant(level.getRandom(), biome);
            } else if (mob.getType() == EntityType.PIG) {
                variant = PigVariant.selectVariant(level.getRandom(), biome);
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

    public static SpawnGroupData determineSpawnAge(Mob mob, ServerLevelAccessor level, MobSpawnType spawnType, SpawnGroupData spawnGroupData) {
        if (spawnType == MobSpawnType.NATURAL || spawnType == MobSpawnType.CHUNK_GENERATION) {
            if (mob.getType() == EntityType.SQUID || mob.getType() == EntityType.GLOW_SQUID) {
                AgeableAttachment attachment = mob.getData(EqualsAttachments.AGEABLE);
                if (spawnGroupData == null) {
                    spawnGroupData = new AgeableMob.AgeableMobGroupData(true);
                }
                AgeableMob.AgeableMobGroupData ageableGroupData = (AgeableMob.AgeableMobGroupData) spawnGroupData;
                if (ageableGroupData.isShouldSpawnBaby() && ageableGroupData.getGroupSize() > 0 && level.getRandom().nextFloat() <= ageableGroupData.getBabySpawnChance()) {
                    VanillaEquals.LOGGER.info("baby: " + mob.position());
                    attachment.setAge(mob, -24000);
                }
                ageableGroupData.increaseGroupSizeByOne();
            }
        }
        return spawnGroupData;
    }

    public static void spawnOffspring(Mob parentA, Mob parentB, AgeableMob child) {
        if (child != null) {
            MobVariantAttachment parentAAttachment = parentA.getData(EqualsAttachments.MOB_VARIANT);
            MobVariantAttachment parentBAttachment = parentB.getData(EqualsAttachments.MOB_VARIANT);
            MobVariantAttachment childAttachment = child.getData(EqualsAttachments.MOB_VARIANT);
            Variant variant = null;
            if (parentA.getType() == EntityType.CHICKEN && parentB.getType() == EntityType.CHICKEN) {
                variant = child.level().getRandom().nextBoolean() ? parentAAttachment.getVariant(ChickenVariant.GET) : parentBAttachment.getVariant(ChickenVariant.GET);
            } else if (parentA.getType() == EntityType.COW && parentB.getType() == EntityType.COW) {
                variant = child.level().getRandom().nextBoolean() ? parentAAttachment.getVariant(CowVariant.GET) : parentBAttachment.getVariant(CowVariant.GET);
            } else if (parentA.getType() == EntityType.PIG && parentB.getType() == EntityType.PIG) {
                variant = child.level().getRandom().nextBoolean() ? parentAAttachment.getVariant(PigVariant.GET) : parentBAttachment.getVariant(PigVariant.GET);
            }
            if (variant != null) {
                childAttachment.setVariant(variant);
            }
        }
    }

    public static void syncMobVariant(Entity entity) {
        MobVariantAttachment attachment = entity.getData(EqualsAttachments.MOB_VARIANT);
        Variant variant = null;
        SetVariantPacket packet = null;
        if (entity.getType() == EntityType.CHICKEN) {
            variant = attachment.getVariant(ChickenVariant.GET);
            packet = new SetVariantPacket.Chicken(entity.getId(), variant.getSerializedName());
        } else if (entity.getType() == EntityType.COW) {
            variant = attachment.getVariant(CowVariant.GET);
            packet = new SetVariantPacket.Cow(entity.getId(), variant.getSerializedName());
        } else if (entity.getType() == EntityType.PIG) {
            variant = attachment.getVariant(PigVariant.GET);
            packet = new SetVariantPacket.Pig(entity.getId(), variant.getSerializedName());
        }
        if (variant != null) {
            EqualsPackets.sendToAll(packet);
        }
    }

    public static void syncMobAge(Entity entity) {
        if (entity.getType() == EntityType.SQUID || entity.getType() == EntityType.GLOW_SQUID) {
            AgeableAttachment attachment = entity.getData(EqualsAttachments.AGEABLE);
            EqualsPackets.sendToAll(new SetAgePacket(entity.getId(), attachment.getAge(entity)));
        }
    }

    public static void tickAge(LivingEntity entity) {
        entity.getData(EqualsAttachments.AGEABLE).tickAge(entity);
    }
}
