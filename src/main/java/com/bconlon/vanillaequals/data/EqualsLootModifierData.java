package com.bconlon.vanillaequals.data;

import com.bconlon.vanillaequals.VanillaEquals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class EqualsLootModifierData extends GlobalLootModifierProvider {
    public EqualsLootModifierData(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, VanillaEquals.MODID);
    }

    @Override
    protected void start() {

    }
}
