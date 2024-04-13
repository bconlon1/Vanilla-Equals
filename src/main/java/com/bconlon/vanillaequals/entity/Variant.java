package com.bconlon.vanillaequals.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.biome.Biome;

public interface Variant extends StringRepresentable {
    TagKey<Biome> getSpawnBiome();
    float getRarity();
    ResourceLocation getTexture();
}
