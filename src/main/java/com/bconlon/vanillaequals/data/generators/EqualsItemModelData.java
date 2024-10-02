package com.bconlon.vanillaequals.data.generators;

import com.bconlon.vanillaequals.blocks.EqualsBlocks;
import com.bconlon.vanillaequals.data.providers.EqualsItemModelProvider;
import com.bconlon.vanillaequals.item.EqualsItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class EqualsItemModelData extends EqualsItemModelProvider {
    public EqualsItemModelData(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.itemBlockFlat(EqualsBlocks.BUTTERCUP.get());

        this.eggItem(EqualsItems.AMBER_CHICKEN_SPAWN_EGG.get());
        this.eggItem(EqualsItems.BRONZED_CHICKEN_SPAWN_EGG.get());
        this.eggItem(EqualsItems.GOLD_CRESTED_CHICKEN_SPAWN_EGG.get());
        this.eggItem(EqualsItems.MIDNIGHT_CHICKEN_SPAWN_EGG.get());
        this.eggItem(EqualsItems.SKEWBALD_CHICKEN_SPAWN_EGG.get());
        this.eggItem(EqualsItems.STORMY_CHICKEN_SPAWN_EGG.get());
        this.eggItem(EqualsItems.ALBINO_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.ASHEN_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.COOKIE_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.CREAM_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.DAIRY_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.PINTO_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.SUNSET_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.MOTTLED_PIG_SPAWN_EGG.get());
        this.eggItem(EqualsItems.PALE_PIG_SPAWN_EGG.get());
        this.eggItem(EqualsItems.PIEBALD_PIG_SPAWN_EGG.get());
        this.eggItem(EqualsItems.PINK_FOOTED_PIG_SPAWN_EGG.get());
        this.eggItem(EqualsItems.SOOTY_PIG_SPAWN_EGG.get());
        this.eggItem(EqualsItems.SPOTTED_PIG_SPAWN_EGG.get());
    }
}
