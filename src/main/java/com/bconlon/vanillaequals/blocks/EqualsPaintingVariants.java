package com.bconlon.vanillaequals.blocks;

import com.bconlon.vanillaequals.VanillaEquals;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EqualsPaintingVariants {
    public static DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(Registries.PAINTING_VARIANT, VanillaEquals.MODID);

    public static DeferredHolder<PaintingVariant, PaintingVariant> BIRDIE = PAINTINGS.register("birdie",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "birdie")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> CS_DESERT = PAINTINGS.register("cs_desert",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "cs_desert")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> CS_DESERT_2 = PAINTINGS.register("cs_desert_2",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "cs_desert_2")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> CS_ITALY = PAINTINGS.register("cs_italy",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "cs_italy")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> CS_ITALY_2 = PAINTINGS.register("cs_italy_2",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "cs_italy_2")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> CS_ITALY_3 = PAINTINGS.register("cs_italy_3",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "cs_italy_3")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> CS_MILITIA = PAINTINGS.register("cs_militia",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "cs_militia")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> DE_AZTEC = PAINTINGS.register("de_aztec",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "de_aztec")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> DE_CHATEAU = PAINTINGS.register("de_chateau",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "de_chateau")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> DE_DUST = PAINTINGS.register("de_dust",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "de_dust")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> DE_DUST_2 = PAINTINGS.register("de_dust_2",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "de_dust_2")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> DE_DUST2 = PAINTINGS.register("de_dust2",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "de_dust2")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> DE_DUST2_2 = PAINTINGS.register("de_dust2_2",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "de_dust2_2")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> FLUKEY = PAINTINGS.register("flukey",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "flukey")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> HS = PAINTINGS.register("hs",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "hs")));
    public static DeferredHolder<PaintingVariant, PaintingVariant> THEN = PAINTINGS.register("then",  () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "then")));


//    public static DeferredHolder<PaintingVariant, PaintingVariant> BIRDIE = PAINTINGS.register("birdie",  () -> new PaintingVariant(16, 16));

//    public static DeferredHolder<PaintingVariant, PaintingVariant> MAKRILL = PAINTINGS.register("makrill",  () -> new PaintingVariant(16, 16));
}
