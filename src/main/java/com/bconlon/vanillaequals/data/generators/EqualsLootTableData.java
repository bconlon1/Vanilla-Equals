package com.bconlon.vanillaequals.data.generators;

import com.bconlon.vanillaequals.data.generators.loot.EqualsBlockLoot;
import com.bconlon.vanillaequals.loot.EqualsLoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EqualsLootTableData {
    public static LootTableProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        return new LootTableProvider(output, EqualsLoot.IMMUTABLE_LOOT_TABLES, List.of(
                new LootTableProvider.SubProviderEntry(EqualsBlockLoot::new, LootContextParamSets.BLOCK)
        ), registries);
    }
}
