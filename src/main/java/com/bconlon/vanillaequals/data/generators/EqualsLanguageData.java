package com.bconlon.vanillaequals.data.generators;

import com.bconlon.vanillaequals.data.providers.EqualsLanguageProvider;
import com.bconlon.vanillaequals.item.EqualsItems;
import net.minecraft.data.PackOutput;

public class EqualsLanguageData extends EqualsLanguageProvider {
    public EqualsLanguageData(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        // Items
        this.addItem(EqualsItems.ALBINO_COW_SPAWN_EGG, "Albino Cow Spawn Egg");
        this.addItem(EqualsItems.ASHEN_COW_SPAWN_EGG, "Ashen Cow Spawn Egg");
        this.addItem(EqualsItems.COOKIE_COW_SPAWN_EGG, "Cookie Cow Spawn Egg");
        this.addItem(EqualsItems.CREAM_COW_SPAWN_EGG, "Cream Cow Spawn Egg");
        this.addItem(EqualsItems.DAIRY_COW_SPAWN_EGG, "Dairy Cow Spawn Egg");
        this.addItem(EqualsItems.PINTO_COW_SPAWN_EGG, "Pinto Cow Spawn Egg");
        this.addItem(EqualsItems.SUNSET_COW_SPAWN_EGG, "Sunset Cow Spawn Egg");

        // Paintings
        this.addPaintingTitle("makrill", "Makrill");
        this.addPaintingAuthor("makrill", "Kristoffer Zetterstrand");

        // Packs
        this.addPackDescription("mod", "Vanilla Equals Resources");
    }
}
