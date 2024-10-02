package com.bconlon.vanillaequals.blocks;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.data.generators.loot.EqualsBlockLoot;
import com.bconlon.vanillaequals.item.EqualsItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class EqualsBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(VanillaEquals.MODID);

    public static final DeferredBlock<Block> BUTTERCUP = register(
            "buttercup",
            () -> new FlowerBlock(
                    MobEffects.JUMP,
                    6.0F,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.GRASS)
                            .offsetType(BlockBehaviour.OffsetType.XZ)
                            .pushReaction(PushReaction.DESTROY)
            )
    );
    public static final DeferredBlock<Block> POTTED_BUTTERCUP = register("potted_buttercup", () -> flowerPot(BUTTERCUP));

    private static <T extends Block> DeferredBlock<T> baseRegister(String name, Supplier<? extends T> block, Function<DeferredBlock<T>, Supplier<? extends Item>> item) {
        DeferredBlock<T> register = BLOCKS.register(name, block);
        EqualsItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <B extends Block> DeferredBlock<B> register(String name, Supplier<B> block) {
        return baseRegister(name, block, EqualsBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final DeferredBlock<T> deferredBlock) {
        return () -> {
            DeferredBlock<T> block = Objects.requireNonNull(deferredBlock);
            return new BlockItem(block.get(), new Item.Properties());
        };
    }

    private static Block flowerPot(Supplier<Block> potted) {
        return new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, potted, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
    }

    public static void registerPots() {
        FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;
        pot.addPlant(BuiltInRegistries.BLOCK.getKey(EqualsBlocks.BUTTERCUP.get()), EqualsBlocks.POTTED_BUTTERCUP);
    }

    public static void registerFlammability() {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.setFlammable(Blocks.CORNFLOWER, 60, 100);
    }
}
