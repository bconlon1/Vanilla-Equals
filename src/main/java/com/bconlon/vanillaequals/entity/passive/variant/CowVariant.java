package com.bconlon.vanillaequals.entity.passive.variant;

import com.bconlon.vanillaequals.EqualsTags;
import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.entity.Variant;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.biome.Biome;

import java.util.function.Function;

public enum CowVariant implements Variant { //todo balancing
    DEFAULT(EqualsTags.Biomes.SPAWNS_DEFAULT_COW, 0.5F, ResourceLocation.withDefaultNamespace("textures/entity/cow/cow.png"), ModelType.DEFAULT, "default"),
    ALBINO(EqualsTags.Biomes.SPAWNS_ALBINO_COW, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/cow/albino.png"), ModelType.DETAILED, "albino"),
    ASHEN(EqualsTags.Biomes.SPAWNS_ASHEN_COW, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/cow/ashen.png"), ModelType.DETAILED, "ashen"),
    COOKIE(EqualsTags.Biomes.SPAWNS_COOKIE_COW, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/cow/cookie.png"), ModelType.DETAILED, "cookie"),
    CREAM(EqualsTags.Biomes.SPAWNS_CREAM_COW, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/cow/cream.png"), ModelType.DEFAULT, "cream"),
    DAIRY(EqualsTags.Biomes.SPAWNS_DAIRY_COW, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/cow/dairy.png"), ModelType.DEFAULT, "dairy"),
    PINTO(EqualsTags.Biomes.SPAWNS_PINTO_COW, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/cow/pinto.png"), ModelType.DETAILED, "pinto"),
    SUNSET(EqualsTags.Biomes.SPAWNS_SUNSET_COW, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/cow/sunset.png"), ModelType.DETAILED, "sunset");

    public static final StringRepresentable.EnumCodec<CowVariant> CODEC = StringRepresentable.fromEnum(CowVariant::values);
    public static final Function<String, Variant> GET = CowVariant::byName;

    private final TagKey<Biome> spawnBiome;
    private final float rarity;
    private final ResourceLocation texture;
    private final ModelType modelType;
    private final String name;

    CowVariant(TagKey<Biome> spawnBiome, float rarity, ResourceLocation texture, ModelType modelType, String name) {
        this.spawnBiome = spawnBiome;
        this.rarity = rarity;
        this.texture = texture;
        this.modelType = modelType;
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
    public ModelType getModelType() {
        return this.modelType;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public static CowVariant byName(String name) {
        return CODEC.byName(name, DEFAULT);
    }

    public static CowVariant selectVariant(RandomSource random, Holder<Biome> biome) {
        for (CowVariant variant : CowVariant.values()) {
            if (biome.is(variant.getSpawnBiome()) && random.nextFloat() < variant.getRarity()) {
                return variant;
            }
        }
        return DEFAULT;
    }
}
