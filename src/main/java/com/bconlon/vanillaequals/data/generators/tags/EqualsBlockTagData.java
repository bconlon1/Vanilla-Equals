package com.bconlon.vanillaequals.data.generators.tags;

import com.bconlon.vanillaequals.VanillaEquals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class EqualsBlockTagData extends BlockTagsProvider {
    public EqualsBlockTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, VanillaEquals.MODID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {

    }
}
