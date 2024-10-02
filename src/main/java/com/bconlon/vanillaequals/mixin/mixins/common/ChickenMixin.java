package com.bconlon.vanillaequals.mixin.mixins.common;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.LivestockAttachment;
import com.bconlon.vanillaequals.network.packet.clientbound.HungerParticlesPacket;
import net.minecraft.world.entity.animal.Chicken;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chicken.class)
public class ChickenMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Chicken;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V", shift = At.Shift.BEFORE), method = "aiStep()V")
    private void aiStep(CallbackInfo ci) {
        Chicken chicken = (Chicken) (Object) this;
        LivestockAttachment attachment = chicken.getData(EqualsAttachments.LIVESTOCK);
        if (attachment.getEatBlockGoal() != null) {
            if (attachment.isHungry()) {
                PacketDistributor.sendToAllPlayers(new HungerParticlesPacket(chicken.getId()));
                ci.cancel();
            }
        }
    }
}
