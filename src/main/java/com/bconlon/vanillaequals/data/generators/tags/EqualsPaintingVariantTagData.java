package com.bconlon.vanillaequals.data.generators.tags;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.blocks.EqualsPaintingVariants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariants;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EqualsPaintingVariantTagData extends PaintingVariantTagsProvider {
    public EqualsPaintingVariantTagData(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, VanillaEquals.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(PaintingVariantTags.PLACEABLE)
                .add(
                        PaintingVariants.EARTH,
                        PaintingVariants.WIND,
                        PaintingVariants.WATER,
                        PaintingVariants.FIRE,
                        EqualsPaintingVariants.BIRDIE.getKey(),
                        EqualsPaintingVariants.CS_DESERT.getKey(),
                        EqualsPaintingVariants.CS_DESERT_2.getKey(),
                        EqualsPaintingVariants.CS_ITALY.getKey(),
                        EqualsPaintingVariants.CS_ITALY_2.getKey(),
                        EqualsPaintingVariants.CS_ITALY_3.getKey(),
                        EqualsPaintingVariants.CS_MILITIA.getKey(),
                        EqualsPaintingVariants.DE_AZTEC.getKey(),
                        EqualsPaintingVariants.DE_CHATEAU.getKey(),
                        EqualsPaintingVariants.DE_DUST.getKey(),
                        EqualsPaintingVariants.DE_DUST_2.getKey(),
                        EqualsPaintingVariants.DE_DUST2.getKey(),
                        EqualsPaintingVariants.DE_DUST2_2.getKey(),
                        EqualsPaintingVariants.FLUKEY.getKey(),
                        EqualsPaintingVariants.HS.getKey(),
                        EqualsPaintingVariants.THEN.getKey()
                );
    }
}
