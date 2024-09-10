package com.bconlon.vanillaequals.entity.passive.variant;

import com.bconlon.vanillaequals.entity.Variant;
import com.bconlon.vanillaequals.network.packet.clientbound.SetChickenVariantPacket;
import com.bconlon.vanillaequals.network.packet.clientbound.SetCowVariantPacket;
import com.bconlon.vanillaequals.network.packet.clientbound.SetPigVariantPacket;
import com.bconlon.vanillaequals.network.packet.clientbound.SetVariantPacket;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class VariantFunctions {
    public static final Map<EntityType<?>, VariantFunctionHolder> VARIANT_FUNCTIONS = new ImmutableMap.Builder<EntityType<?>, VariantFunctionHolder>()
            .put(EntityType.CHICKEN, new VariantFunctionHolder(ChickenVariant::selectVariant, ChickenVariant.GET, SetChickenVariantPacket::new))
            .put(EntityType.COW, new VariantFunctionHolder(CowVariant::selectVariant, CowVariant.GET, SetCowVariantPacket::new))
            .put(EntityType.PIG, new VariantFunctionHolder(PigVariant::selectVariant, PigVariant.GET, SetPigVariantPacket::new))
            .build();

    public record VariantFunctionHolder(BiFunction<RandomSource, Holder<Biome>, Variant> randomSelector, Function<String, Variant> variantGetter, BiFunction<Integer, String, SetVariantPacket> packet) {

    }
}
