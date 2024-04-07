package com.bconlon.vanillaequals.data.generators;

import com.bconlon.vanillaequals.data.providers.EqualsLanguageProvider;
import net.minecraft.data.PackOutput;

public class EqualsLanguageData extends EqualsLanguageProvider {
    public EqualsLanguageData(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        // Paintings
        this.addPaintingTitle("makrill", "Makrill");
        this.addPaintingAuthor("makrill", "Kristoffer Zetterstrand");
    }
}
