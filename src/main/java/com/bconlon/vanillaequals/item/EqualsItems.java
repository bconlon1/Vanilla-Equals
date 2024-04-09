package com.bconlon.vanillaequals.item;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.entity.passive.CowVariant;
import com.bconlon.vanillaequals.item.miscellaneous.VariantSpawnEggItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EqualsItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VanillaEquals.MODID);

    public static final DeferredItem<SpawnEggItem> ALBINO_COW_SPAWN_EGG = ITEMS.register("albino_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.ALBINO, CowVariant.GET, 0xF6EBE9, 0xEE9791, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> ASHEN_COW_SPAWN_EGG = ITEMS.register("ashen_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.ASHEN, CowVariant.GET, 0xAE9295, 0x68546A, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> COOKIE_COW_SPAWN_EGG = ITEMS.register("cookie_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.COOKIE, CowVariant.GET, 0x566373, 0xF0E8DD, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> CREAM_COW_SPAWN_EGG = ITEMS.register("cream_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.CREAM, CowVariant.GET, 0xF0B156, 0xFFF1CA, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> DAIRY_COW_SPAWN_EGG = ITEMS.register("dairy_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.DAIRY, CowVariant.GET, 0xF6F4F9, 0x2E2E2D, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> PINTO_COW_SPAWN_EGG = ITEMS.register("pinto_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.PINTO, CowVariant.GET, 0xD7813F, 0xF7E9D8, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> SUNSET_COW_SPAWN_EGG = ITEMS.register("sunset_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.SUNSET, CowVariant.GET, 0xE58D39, 0x171514, new Item.Properties()));
}
