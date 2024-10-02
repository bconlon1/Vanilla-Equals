package com.bconlon.vanillaequals.data.providers;

import com.bconlon.vanillaequals.VanillaEquals;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public abstract class EqualsItemModelProvider extends ItemModelProvider {
    public EqualsItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VanillaEquals.MODID, existingFileHelper);
    }

    public String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public String itemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

    protected ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    protected ResourceLocation texture(String name, String location) {
        return this.modLoc("block/" + location + name);
    }

    public void itemBlockFlat(Block block) {
        this.withExistingParent(this.blockName(block), this.mcLoc("item/generated")).texture("layer0", this.texture(this.blockName(block)));
    }

    public void eggItem(Item item) {
        this.withExistingParent(this.itemName(item), this.mcLoc("item/template_spawn_egg"));
    }
}
