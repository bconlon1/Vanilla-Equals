package com.bconlon.vanillaequals.event.hooks;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.MobVariantAttachment;
import com.bconlon.vanillaequals.entity.Variant;
import com.bconlon.vanillaequals.entity.VariantGroupData;
import com.bconlon.vanillaequals.entity.passive.variant.VariantFunctions;
import com.bconlon.vanillaequals.network.packet.clientbound.SetVariantPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.network.PacketDistributor;

public class EntityHooks {
    public static SpawnGroupData chooseMobVariant(Mob mob, ServerLevelAccessor level, double x, double y, double z, MobSpawnType spawnType, SpawnGroupData spawnGroupData) {
        if (spawnType == MobSpawnType.NATURAL || spawnType == MobSpawnType.CHUNK_GENERATION) {
            MobVariantAttachment attachment = mob.getData(EqualsAttachments.MOB_VARIANT);
            BlockPos pos = BlockPos.containing(x, y, z);
            Holder<Biome> biome = level.getBiome(pos);
            Variant variant = null;
            if (VariantFunctions.VARIANT_FUNCTIONS.containsKey(mob.getType())) {
                variant = VariantFunctions.VARIANT_FUNCTIONS.get(mob.getType()).randomSelector().apply(level.getRandom(), biome);
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

    public static void spawnOffspring(Mob parentA, Mob parentB, AgeableMob child) {
        if (child != null) {
            MobVariantAttachment parentAAttachment = parentA.getData(EqualsAttachments.MOB_VARIANT);
            MobVariantAttachment parentBAttachment = parentB.getData(EqualsAttachments.MOB_VARIANT);
            MobVariantAttachment childAttachment = child.getData(EqualsAttachments.MOB_VARIANT);
            Variant variant = null;
            if (parentA.getType() == parentB.getType() && VariantFunctions.VARIANT_FUNCTIONS.containsKey(parentA.getType())) {
                variant = child.level().getRandom().nextBoolean()
                        ? parentAAttachment.getVariant(VariantFunctions.VARIANT_FUNCTIONS.get(parentA.getType()).variantGetter())
                        : parentBAttachment.getVariant(VariantFunctions.VARIANT_FUNCTIONS.get(parentB.getType()).variantGetter());
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
        if (VariantFunctions.VARIANT_FUNCTIONS.containsKey(entity.getType())) {
            variant = attachment.getVariant(VariantFunctions.VARIANT_FUNCTIONS.get(entity.getType()).variantGetter());
            packet = VariantFunctions.VARIANT_FUNCTIONS.get(entity.getType()).packet().apply(entity.getId(), variant.getSerializedName());
        }
        if (variant != null) {
            PacketDistributor.sendToAllPlayers(packet);
        }
    }
}
