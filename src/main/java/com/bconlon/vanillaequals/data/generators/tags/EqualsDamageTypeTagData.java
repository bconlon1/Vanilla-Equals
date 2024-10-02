package com.bconlon.vanillaequals.data.generators.tags;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.data.resources.registries.EqualsDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class EqualsDamageTypeTagData extends TagsProvider<DamageType> {
    public EqualsDamageTypeTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, Registries.DAMAGE_TYPE, registries, VanillaEquals.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(Tags.DamageTypes.IS_POISON).add(EqualsDamageTypes.FATAL_POISON_DAMAGE);
    }
}
