package com.bconlon.vanillaequals;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class EqualsTags {
    public static class Biomes {
        public static final TagKey<Biome> SPAWNS_DEFAULT_COW = tag("spawns_default_cow");
        public static final TagKey<Biome> SPAWNS_ALBINO_COW = tag("spawns_albino_cow");
        public static final TagKey<Biome> SPAWNS_ASHEN_COW = tag("spawns_ashen_cow");
        public static final TagKey<Biome> SPAWNS_COOKIE_COW = tag("spawns_cookie_cow");
        public static final TagKey<Biome> SPAWNS_CREAM_COW = tag("spawns_cream_cow");
        public static final TagKey<Biome> SPAWNS_DAIRY_COW = tag("spawns_dairy_cow");
        public static final TagKey<Biome> SPAWNS_PINTO_COW = tag("spawns_pinto_cow");
        public static final TagKey<Biome> SPAWNS_SUNSET_COW = tag("spawns_sunset_cow");

        public static final TagKey<Biome> SPAWNS_DEFAULT_CHICKEN = tag("spawns_default_chicken");
        public static final TagKey<Biome> SPAWNS_AMBER_CHICKEN = tag("spawns_amber_chicken");
        public static final TagKey<Biome> SPAWNS_BRONZED_CHICKEN = tag("spawns_bronzed_chicken");
        public static final TagKey<Biome> SPAWNS_GOLD_CRESTED_CHICKEN = tag("spawns_gold_crested_chicken");
        public static final TagKey<Biome> SPAWNS_MIDNIGHT_CHICKEN = tag("spawns_midnight_chicken");
        public static final TagKey<Biome> SPAWNS_SKEWBALD_CHICKEN = tag("spawns_skewbald_chicken");
        public static final TagKey<Biome> SPAWNS_STORMY_CHICKEN = tag("spawns_stormy_chicken");
    }

    private static TagKey<Biome> tag(String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(VanillaEquals.MODID, name));
    }
}
