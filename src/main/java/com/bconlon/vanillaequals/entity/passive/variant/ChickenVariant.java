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

public enum ChickenVariant implements Variant { //todo balancing
    DEFAULT(EqualsTags.Biomes.SPAWNS_DEFAULT_CHICKEN, 0.5F, new ResourceLocation("textures/entity/chicken.png"), "default"),
    AMBER(EqualsTags.Biomes.SPAWNS_AMBER_CHICKEN, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/chicken/amber.png"), "amber"),
    BRONZED(EqualsTags.Biomes.SPAWNS_BRONZED_CHICKEN, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/chicken/bronzed.png"), "bronzed"),
    GOLD_CRESTED(EqualsTags.Biomes.SPAWNS_GOLD_CRESTED_CHICKEN, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/chicken/gold_crested.png"), "gold_crested"),
    MIDNIGHT(EqualsTags.Biomes.SPAWNS_MIDNIGHT_CHICKEN, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/chicken/midnight.png"), "midnight"),
    SKEWBALD(EqualsTags.Biomes.SPAWNS_SKEWBALD_CHICKEN, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/chicken/skewbald.png"), "skewbald"),
    STORMY(EqualsTags.Biomes.SPAWNS_STORMY_CHICKEN, 0.5F, new ResourceLocation(VanillaEquals.MODID, "textures/entity/chicken/stormy.png"), "stormy");

    public static final StringRepresentable.EnumCodec<ChickenVariant> CODEC = StringRepresentable.fromEnum(ChickenVariant::values);
    public static final Function<String, Variant> GET = ChickenVariant::byName;

    private final TagKey<Biome> spawnBiome;
    private final float rarity;
    private final ResourceLocation texture;
    private final String name;

    ChickenVariant(TagKey<Biome> spawnBiome, float rarity, ResourceLocation texture, String name) {
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
    public ModelType getModelType() { //todo
        return ModelType.DEFAULT;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public static ChickenVariant byName(String name) {
        return CODEC.byName(name, DEFAULT);
    }

    public static ChickenVariant selectVariant(RandomSource random, Holder<Biome> biome) {
        for (ChickenVariant variant : ChickenVariant.values()) {
            if (biome.is(variant.getSpawnBiome()) && random.nextFloat() < variant.getRarity()) {
                return variant;
            }
        }
        return DEFAULT;
    }
}
