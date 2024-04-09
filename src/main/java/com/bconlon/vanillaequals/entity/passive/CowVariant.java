package com.bconlon.vanillaequals.entity.passive;

import com.bconlon.vanillaequals.EqualsTags;
import com.bconlon.vanillaequals.VanillaEquals;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.biome.Biome;

import java.util.Locale;
import java.util.function.Function;

public enum CowVariant implements Variant { //todo balancing
    DEFAULT(EqualsTags.Biomes.SPAWNS_DEFAULT_COW, 0.5F, new ResourceLocation("textures/entity/cow/cow.png"), "default"),
    ALBINO(EqualsTags.Biomes.SPAWNS_ALBINO_COW, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/cow/albino.png"), "albino"),
    ASHEN(EqualsTags.Biomes.SPAWNS_ASHEN_COW, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/cow/ashen.png"), "ashen"),
    COOKIE(EqualsTags.Biomes.SPAWNS_COOKIE_COW, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/cow/cookie.png"), "cookie"),
    CREAM(EqualsTags.Biomes.SPAWNS_CREAM_COW, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/cow/cream.png"), "cream"),
    DAIRY(EqualsTags.Biomes.SPAWNS_DAIRY_COW, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/cow/dairy.png"), "dairy"),
    PINTO(EqualsTags.Biomes.SPAWNS_PINTO_COW, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/cow/pinto.png"), "pinto"),
    SUNSET(EqualsTags.Biomes.SPAWNS_SUNSET_COW, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/cow/sunset.png"), "sunset");

    public static final StringRepresentable.EnumCodec<CowVariant> CODEC = StringRepresentable.fromEnum(CowVariant::values);
    public static final Function<String, Variant> GET = CowVariant::byName;

    private final TagKey<Biome> spawnBiome;
    private final float rarity;
    private final ResourceLocation texture;
    private final String name;

    CowVariant(TagKey<Biome> spawnBiome, float rarity, ResourceLocation texture, String name) {
        this.spawnBiome = spawnBiome;
        this.rarity = rarity;
        this.texture = texture;
        this.name = name;
    }

    @Override
    public TagKey<Biome> getSpawnBiome() {
        return this.spawnBiome;
    }

    @Override
    public float getRarity() {
        return this.rarity;
    }

    @Override
    public ResourceLocation getTexture() {
        return this.texture;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public static CowVariant byName(String name) {
        return CODEC.byName(name, DEFAULT);
    }

    public static CowVariant selectVariant(RandomSource random, Holder<Biome> biome) {
        for (CowVariant cowVariant : CowVariant.values()) {
            if (biome.is(cowVariant.getSpawnBiome()) && random.nextFloat() < cowVariant.getRarity()) {
                return cowVariant;
            }
        }
        return DEFAULT;
    }
}
