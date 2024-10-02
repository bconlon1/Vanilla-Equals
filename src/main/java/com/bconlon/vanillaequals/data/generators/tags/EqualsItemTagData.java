package com.bconlon.vanillaequals.data.generators.tags;

import com.bconlon.vanillaequals.VanillaEquals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class EqualsItemTagData extends ItemTagsProvider {
    public EqualsItemTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper helper) {
        super(output, registries, blockTags, VanillaEquals.MODID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {

    }
}
