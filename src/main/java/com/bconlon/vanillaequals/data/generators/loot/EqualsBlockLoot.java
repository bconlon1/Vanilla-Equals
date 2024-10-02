package com.bconlon.vanillaequals.data.generators.loot;

import com.bconlon.vanillaequals.blocks.EqualsBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EqualsBlockLoot extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Set.of();

    public EqualsBlockLoot(HolderLookup.Provider registries) {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        this.dropSelf(EqualsBlocks.BUTTERCUP.get());
        this.add(EqualsBlocks.POTTED_BUTTERCUP.get(), this::createPotFlowerItemTable);
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        return EqualsBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
    }
}
