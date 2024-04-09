package com.bconlon.vanillaequals.data.providers;

import com.bconlon.vanillaequals.VanillaEquals;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public abstract class EqualsItemModelProvider extends ItemModelProvider {
    public EqualsItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VanillaEquals.MODID, existingFileHelper);
    }

    public String itemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

    public void eggItem(Item item) {
        this.withExistingParent(this.itemName(item), this.mcLoc("item/template_spawn_egg"));
    }
}
