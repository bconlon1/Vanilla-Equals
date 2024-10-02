package com.bconlon.vanillaequals.effect;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.effect.harmful.FatalPosionMobEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EqualsEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, VanillaEquals.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> FATAL_POISON = EFFECTS.register("fatal_poison", FatalPosionMobEffect::new);
}
