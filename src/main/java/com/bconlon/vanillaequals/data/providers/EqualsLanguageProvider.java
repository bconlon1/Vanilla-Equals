package com.bconlon.vanillaequals.data.providers;

import com.bconlon.vanillaequals.VanillaEquals;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public abstract class EqualsLanguageProvider extends LanguageProvider {
    public EqualsLanguageProvider(PackOutput output) {
        super(output, VanillaEquals.MODID, "en_us");
    }

    public void addPaintingTitle(String key, String name) {
        this.add("painting." + VanillaEquals.MODID + "." + key + ".title", name);
    }

    public void addPaintingAuthor(String key, String name) {
        this.add("painting." + VanillaEquals.MODID + "." + key + ".author", name);
    }

    public void addPackDescription(String packName, String description) {
        this.add("pack." + VanillaEquals.MODID + "." + packName + ".description", description);
    }
}
