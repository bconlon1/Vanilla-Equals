package com.bconlon.vanillaequals.item;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.entity.passive.variant.ChickenVariant;
import com.bconlon.vanillaequals.entity.passive.variant.CowVariant;
import com.bconlon.vanillaequals.entity.passive.variant.PigVariant;
import com.bconlon.vanillaequals.item.miscellaneous.VariantSpawnEggItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EqualsItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VanillaEquals.MODID);

    public static final DeferredItem<SpawnEggItem> AMBER_CHICKEN_SPAWN_EGG = ITEMS.register("amber_chicken_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.CHICKEN, ChickenVariant.AMBER, ChickenVariant.GET, 0xDB9844, 0xAF2E33, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> BRONZED_CHICKEN_SPAWN_EGG = ITEMS.register("bronzed_chicken_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.CHICKEN, ChickenVariant.BRONZED, ChickenVariant.GET, 0x5F62AA, 0xA20000, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> GOLD_CRESTED_CHICKEN_SPAWN_EGG = ITEMS.register("gold_crested_chicken_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.CHICKEN, ChickenVariant.GOLD_CRESTED, ChickenVariant.GET, 0xF4C241, 0xEB4E36, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> MIDNIGHT_CHICKEN_SPAWN_EGG = ITEMS.register("midnight_chicken_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.CHICKEN, ChickenVariant.MIDNIGHT, ChickenVariant.GET, 0x294162, 0x0D0D12, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> SKEWBALD_CHICKEN_SPAWN_EGG = ITEMS.register("skewbald_chicken_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.CHICKEN, ChickenVariant.SKEWBALD, ChickenVariant.GET, 0xCBB391, 0xD42A2A, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> STORMY_CHICKEN_SPAWN_EGG = ITEMS.register("stormy_chicken_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.CHICKEN, ChickenVariant.STORMY, ChickenVariant.GET, 0x584C5E, 0xEB412A, new Item.Properties()));

    public static final DeferredItem<SpawnEggItem> ALBINO_COW_SPAWN_EGG = ITEMS.register("albino_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.ALBINO, CowVariant.GET, 0xF6EBE9, 0xEE9791, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> ASHEN_COW_SPAWN_EGG = ITEMS.register("ashen_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.ASHEN, CowVariant.GET, 0xAE9295, 0x68546A, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> COOKIE_COW_SPAWN_EGG = ITEMS.register("cookie_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.COOKIE, CowVariant.GET, 0x566373, 0xF0E8DD, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> CREAM_COW_SPAWN_EGG = ITEMS.register("cream_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.CREAM, CowVariant.GET, 0xF0B156, 0xFFF1CA, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> DAIRY_COW_SPAWN_EGG = ITEMS.register("dairy_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.DAIRY, CowVariant.GET, 0xF6F4F9, 0x2E2E2D, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> PINTO_COW_SPAWN_EGG = ITEMS.register("pinto_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.PINTO, CowVariant.GET, 0xD7813F, 0xF7E9D8, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> SUNSET_COW_SPAWN_EGG = ITEMS.register("sunset_cow_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.COW, CowVariant.SUNSET, CowVariant.GET, 0xE58D39, 0x171514, new Item.Properties()));

    public static final DeferredItem<SpawnEggItem> MOTTLED_PIG_SPAWN_EGG = ITEMS.register("mottled_pig_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.PIG, PigVariant.MOTTLED, PigVariant.GET, 0x9D8077, 0x50403C, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> PALE_PIG_SPAWN_EGG = ITEMS.register("pale_pig_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.PIG, PigVariant.PALE, PigVariant.GET, 0xEAD3D3, 0xB07E7E, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> PIEBALD_PIG_SPAWN_EGG = ITEMS.register("piebald_pig_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.PIG, PigVariant.PIEBALD, PigVariant.GET, 0xE6BEB9, 0x594E4E, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> PINK_FOOTED_PIG_SPAWN_EGG = ITEMS.register("pink_footed_pig_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.PIG, PigVariant.PINK_FOOTED, PigVariant.GET, 0x807672, 0x392E32, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> SOOTY_PIG_SPAWN_EGG = ITEMS.register("sooty_pig_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.PIG, PigVariant.SOOTY, PigVariant.GET, 0xEAB8AB, 0x2A2A34, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> SPOTTED_PIG_SPAWN_EGG = ITEMS.register("spotted_pig_spawn_egg", () -> new VariantSpawnEggItem(() -> EntityType.PIG, PigVariant.SPOTTED, PigVariant.GET, 0xE6BEB9, 0x594E4E, new Item.Properties()));
}
