package com.bconlon.vanillaequals.data.providers;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public abstract class EqualsBlockStateProvider extends BlockStateProvider {
    public EqualsBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    public String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    public ResourceLocation texture(String name, String location) {
        return this.modLoc("block/" + location + name);
    }

    public ResourceLocation texture(String name, String location, String suffix) {
        return this.modLoc("block/" + location + name + suffix);
    }

    public ResourceLocation extend(ResourceLocation location, String suffix) {
        return ResourceLocation.fromNamespaceAndPath(location.getNamespace(), location.getPath() + suffix);
    }

    public void crossBlock(Block block) {
        this.crossBlock(block, models().cross(this.name(block), this.texture(this.name(block))).renderType(ResourceLocation.withDefaultNamespace("cutout")));
    }

    public void crossBlock(Block block, ModelFile model) {
        this.getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
    }
}
