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
    }

    private static TagKey<Biome> tag(String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(VanillaEquals.MODID, name));
    }
}
