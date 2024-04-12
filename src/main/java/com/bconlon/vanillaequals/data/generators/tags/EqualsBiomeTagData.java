package com.bconlon.vanillaequals.data.generators.tags;

import com.bconlon.vanillaequals.EqualsTags;
import com.bconlon.vanillaequals.VanillaEquals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EqualsBiomeTagData extends BiomeTagsProvider {
    public EqualsBiomeTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, registries, VanillaEquals.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(EqualsTags.Biomes.SPAWNS_DEFAULT_CHICKEN).addTag(BiomeTags.IS_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_BRONZED_CHICKEN).addTag(BiomeTags.IS_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_GOLD_CRESTED_CHICKEN).addTag(BiomeTags.IS_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_MIDNIGHT_CHICKEN).addTag(BiomeTags.IS_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_SKEWBALD_CHICKEN).addTag(BiomeTags.IS_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_STORMY_CHICKEN).addTag(BiomeTags.IS_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_DEFAULT_COW).addTag(BiomeTags.IS_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_ALBINO_COW).addTag(Tags.Biomes.IS_COLD_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_ASHEN_COW).addTag(Tags.Biomes.IS_COLD_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_COOKIE_COW).addTag(Tags.Biomes.IS_COLD_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_CREAM_COW).addTag(Tags.Biomes.IS_PLAINS);
        this.tag(EqualsTags.Biomes.SPAWNS_DAIRY_COW).addTag(BiomeTags.IS_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_PINTO_COW).addTag(Tags.Biomes.IS_DRY_OVERWORLD);
        this.tag(EqualsTags.Biomes.SPAWNS_SUNSET_COW).addTag(Tags.Biomes.IS_DRY_OVERWORLD);
    }
}
