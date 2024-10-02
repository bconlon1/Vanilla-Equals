package com.bconlon.vanillaequals.effect.harmful;

import com.bconlon.vanillaequals.data.resources.registries.EqualsDamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FatalPosionMobEffect extends MobEffect {
    public FatalPosionMobEffect() {
        super(MobEffectCategory.HARMFUL, 5149489);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        var damageTypeRegistry = livingEntity.damageSources().damageTypes;
        var damageType = damageTypeRegistry.getHolder(EqualsDamageTypes.FATAL_POISON_DAMAGE).orElse(damageTypeRegistry.getHolderOrThrow(DamageTypes.MAGIC));
        livingEntity.hurt(new DamageSource(damageType), 1.0F);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int i = 25 >> amplifier;
        return i == 0 || duration % i == 0;
    }
}