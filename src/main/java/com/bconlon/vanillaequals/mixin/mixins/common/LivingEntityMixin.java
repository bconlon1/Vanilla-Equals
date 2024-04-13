package com.bconlon.vanillaequals.mixin.mixins.common;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "isBaby()Z", cancellable = true)
    private void isBaby(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity.getType() == EntityType.SQUID || livingEntity.getType() == EntityType.GLOW_SQUID) {
            if (livingEntity.getData(EqualsAttachments.AGEABLE).isBaby()) {
                cir.setReturnValue(true);
            }
        }
    }
}
