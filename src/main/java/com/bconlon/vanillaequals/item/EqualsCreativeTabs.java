package com.bconlon.vanillaequals.item;

import com.bconlon.vanillaequals.VanillaEquals;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod.EventBusSubscriber(modid = VanillaEquals.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EqualsCreativeTabs {
    @SubscribeEvent
    public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if (tab == CreativeModeTabs.SPAWN_EGGS) {
            event.getEntries().putAfter(new ItemStack(Items.COW_SPAWN_EGG), new ItemStack(EqualsItems.ALBINO_COW_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(EqualsItems.ALBINO_COW_SPAWN_EGG.get()), new ItemStack(EqualsItems.ASHEN_COW_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(EqualsItems.ASHEN_COW_SPAWN_EGG.get()), new ItemStack(EqualsItems.COOKIE_COW_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(EqualsItems.COOKIE_COW_SPAWN_EGG.get()), new ItemStack(EqualsItems.CREAM_COW_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(EqualsItems.CREAM_COW_SPAWN_EGG.get()), new ItemStack(EqualsItems.DAIRY_COW_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(EqualsItems.DAIRY_COW_SPAWN_EGG.get()), new ItemStack(EqualsItems.PINTO_COW_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(EqualsItems.PINTO_COW_SPAWN_EGG.get()), new ItemStack(EqualsItems.SUNSET_COW_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
