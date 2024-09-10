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

public enum PigVariant implements Variant {
    DEFAULT(EqualsTags.Biomes.SPAWNS_DEFAULT_PIG, 0.5F, ResourceLocation.withDefaultNamespace("textures/entity/pig/pig.png"), "default"),
    MOTTLED(EqualsTags.Biomes.SPAWNS_MOTTLED_PIG, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/pig/mottled.png"), "mottled"),
    PALE(EqualsTags.Biomes.SPAWNS_PALE_PIG, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/pig/pale.png"), "pale"),
    PIEBALD(EqualsTags.Biomes.SPAWNS_PIEBALD_PIG, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/pig/piebald.png"), "piebald"),
    PINK_FOOTED(EqualsTags.Biomes.SPAWNS_PINK_FOOTED_PIG, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/pig/pink_footed.png"), "pink_footed"),
    SOOTY(EqualsTags.Biomes.SPAWNS_SOOTY_PIG, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/pig/sooty.png"), "sooty"),
    SPOTTED(EqualsTags.Biomes.SPAWNS_SPOTTED_PIG, 0.5F, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "textures/entity/pig/spotted.png"), "spotted");

    public static final StringRepresentable.EnumCodec<PigVariant> CODEC = StringRepresentable.fromEnum(PigVariant::values);
    public static final Function<String, Variant> GET = PigVariant::byName;

    private final TagKey<Biome> spawnBiome;
    private final float rarity;
    private final ResourceLocation texture;
    private final String name;

    PigVariant(TagKey<Biome> spawnBiome, float rarity, ResourceLocation texture, String name) {
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

    public static PigVariant byName(String name) {
        return CODEC.byName(name, DEFAULT);
    }

    public static PigVariant selectVariant(RandomSource random, Holder<Biome> biome) {
        for (PigVariant variant : PigVariant.values()) {
            if (biome.is(variant.getSpawnBiome()) && random.nextFloat() < variant.getRarity()) {
                return variant;
            }
        }
        return DEFAULT;
    }
}
