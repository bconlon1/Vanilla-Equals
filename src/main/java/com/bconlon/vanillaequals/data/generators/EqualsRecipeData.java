package com.bconlon.vanillaequals.data.generators;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.blocks.EqualsBlocks;
import com.bconlon.vanillaequals.data.providers.EqualsRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class EqualsRecipeData extends EqualsRecipeProvider {
    public EqualsRecipeData(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, VanillaEquals.MODID);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        oneToOneConversionRecipe(consumer, Items.YELLOW_DYE, EqualsBlocks.BUTTERCUP, "yellow_dye");
    }
}
