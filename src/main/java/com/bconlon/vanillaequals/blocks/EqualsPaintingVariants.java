package com.bconlon.vanillaequals.blocks;

import com.bconlon.vanillaequals.VanillaEquals;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EqualsPaintingVariants {
    public static DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(Registries.PAINTING_VARIANT, VanillaEquals.MODID);

    public static DeferredHolder<PaintingVariant, PaintingVariant> MAKRILL = PAINTINGS.register("makrill",  () -> new PaintingVariant(16, 16));
}
