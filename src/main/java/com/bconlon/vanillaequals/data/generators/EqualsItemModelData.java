package com.bconlon.vanillaequals.data.generators;

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
        this.eggItem(EqualsItems.ALBINO_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.ASHEN_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.COOKIE_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.CREAM_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.DAIRY_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.PINTO_COW_SPAWN_EGG.get());
        this.eggItem(EqualsItems.SUNSET_COW_SPAWN_EGG.get());
    }
}
