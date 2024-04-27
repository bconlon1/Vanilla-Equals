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
        this.addItem(EqualsItems.AMBER_CHICKEN_SPAWN_EGG, "Amber Chicken Spawn Egg");
        this.addItem(EqualsItems.BRONZED_CHICKEN_SPAWN_EGG, "Bronzed Chicken Spawn Egg");
        this.addItem(EqualsItems.GOLD_CRESTED_CHICKEN_SPAWN_EGG, "Gold Crested Chicken Spawn Egg");
        this.addItem(EqualsItems.MIDNIGHT_CHICKEN_SPAWN_EGG, "Midnight Chicken Spawn Egg");
        this.addItem(EqualsItems.SKEWBALD_CHICKEN_SPAWN_EGG, "Skewbald Chicken Spawn Egg");
        this.addItem(EqualsItems.STORMY_CHICKEN_SPAWN_EGG, "Stormy Chicken Spawn Egg");
        this.addItem(EqualsItems.ALBINO_COW_SPAWN_EGG, "Albino Cow Spawn Egg");
        this.addItem(EqualsItems.ASHEN_COW_SPAWN_EGG, "Ashen Cow Spawn Egg");
        this.addItem(EqualsItems.COOKIE_COW_SPAWN_EGG, "Cookie Cow Spawn Egg");
        this.addItem(EqualsItems.CREAM_COW_SPAWN_EGG, "Cream Cow Spawn Egg");
        this.addItem(EqualsItems.DAIRY_COW_SPAWN_EGG, "Dairy Cow Spawn Egg");
        this.addItem(EqualsItems.PINTO_COW_SPAWN_EGG, "Pinto Cow Spawn Egg");
        this.addItem(EqualsItems.SUNSET_COW_SPAWN_EGG, "Sunset Cow Spawn Egg");
        this.addItem(EqualsItems.MOTTLED_PIG_SPAWN_EGG, "Mottled Pig Spawn Egg");
        this.addItem(EqualsItems.PALE_PIG_SPAWN_EGG, "Pale Pig Spawn Egg");
        this.addItem(EqualsItems.PIEBALD_PIG_SPAWN_EGG, "Piebald Pig Spawn Egg");
        this.addItem(EqualsItems.PINK_FOOTED_PIG_SPAWN_EGG, "Pink Footed Pig Spawn Egg");
        this.addItem(EqualsItems.SOOTY_PIG_SPAWN_EGG, "Sooty Pig Spawn Egg");
        this.addItem(EqualsItems.SPOTTED_PIG_SPAWN_EGG, "Spotted Pig Spawn Egg");

        // Paintings
        this.addPaintingTitle("birdie", "Birdie");
        this.addPaintingAuthor("birdie", "Kristoffer Zetterstrand");
        this.addPaintingTitle("cs_desert", "cs_desert");
        this.addPaintingAuthor("cs_desert", "Kristoffer Zetterstrand");
        this.addPaintingTitle("cs_desert_2", "cs_desert");
        this.addPaintingAuthor("cs_desert_2", "Kristoffer Zetterstrand");
        this.addPaintingTitle("cs_italy", "cs_italy");
        this.addPaintingAuthor("cs_italy", "Kristoffer Zetterstrand");
        this.addPaintingTitle("cs_italy_2", "cs_italy");
        this.addPaintingAuthor("cs_italy_2", "Kristoffer Zetterstrand");
        this.addPaintingTitle("cs_italy_3", "cs_italy");
        this.addPaintingAuthor("cs_italy_3", "Kristoffer Zetterstrand");
        this.addPaintingTitle("cs_militia", "cs_militia");
        this.addPaintingAuthor("cs_militia", "Kristoffer Zetterstrand");
        this.addPaintingTitle("de_aztec", "de_aztec");
        this.addPaintingAuthor("de_aztec", "Kristoffer Zetterstrand");
        this.addPaintingTitle("de_chateau", "de_chateau");
        this.addPaintingAuthor("de_chateau", "Kristoffer Zetterstrand");
        this.addPaintingTitle("de_dust", "de_dust");
        this.addPaintingAuthor("de_dust", "Kristoffer Zetterstrand");
        this.addPaintingTitle("de_dust_2", "de_dust");
        this.addPaintingAuthor("de_dust_2", "Kristoffer Zetterstrand");
        this.addPaintingTitle("de_dust2", "de_dust2");
        this.addPaintingAuthor("de_dust2", "Kristoffer Zetterstrand");
        this.addPaintingTitle("de_dust2_2", "de_dust2");
        this.addPaintingAuthor("de_dust2_2", "Kristoffer Zetterstrand");
        this.addPaintingTitle("flukey", "Flukey");
        this.addPaintingAuthor("flukey", "Kristoffer Zetterstrand");
        this.addPaintingTitle("hs", "hs");
        this.addPaintingAuthor("hs", "Kristoffer Zetterstrand");
        this.addPaintingTitle("then", "Then");
        this.addPaintingAuthor("then", "Kristoffer Zetterstrand");

//        this.addPaintingTitle("makrill", "Makrill");
//        this.addPaintingAuthor("makrill", "Kristoffer Zetterstrand");

        // Packs
        this.addPackDescription("mod", "Vanilla Equals Resources");
    }
}
