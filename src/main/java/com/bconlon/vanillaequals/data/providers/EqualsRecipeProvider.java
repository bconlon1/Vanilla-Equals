package com.bconlon.vanillaequals.data.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class EqualsRecipeProvider extends RecipeProvider {
    protected final String id;

    public EqualsRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String id) {
        super(output, provider);
        this.id = id;
    }
}
