package com.bconlon.vanillaequals.mixin.mixins.common;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.LivestockAttachment;
import com.bconlon.vanillaequals.network.packet.clientbound.SyncLivestockAttachmentPacket;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Mob;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class MobMixin {
    @Inject(at = @At("HEAD"), method = "ate()V")
    private void ate(CallbackInfo ci) {
        Mob mob = (Mob) (Object) this;
        LivestockAttachment attachment = mob.getData(EqualsAttachments.LIVESTOCK);
        if (attachment.getEatBlockGoal() != null) {
            if (mob instanceof AgeableMob ageableMob) {
                if (attachment.isHungry()) {
                    attachment.setHungry(false);
                    PacketDistributor.sendToAllPlayers(new SyncLivestockAttachmentPacket(mob.getId(), attachment));
                }
                if (ageableMob.isBaby()) {
                    ageableMob.ageUp(60);
                }
            }

        }
    }

    @Inject(at = @At("HEAD"), method = "customServerAiStep()V")
    private void customServerAiStep(CallbackInfo ci) {
        Mob mob = (Mob) (Object) this;
        LivestockAttachment attachment = mob.getData(EqualsAttachments.LIVESTOCK);
        if (attachment.getEatBlockGoal() != null) {
            attachment.setEatAnimationTick(attachment.getEatBlockGoal().getEatAnimationTick());
        }
    }

    @Inject(at = @At("HEAD"), method = "aiStep()V")
    private void aiStep(CallbackInfo ci) {
        Mob mob = (Mob) (Object) this;
        LivestockAttachment attachment = mob.getData(EqualsAttachments.LIVESTOCK);
        if (attachment.getEatBlockGoal() != null) {
            if (mob.level().isClientSide()) {
                attachment.setEatAnimationTick(Math.max(0, attachment.getEatAnimationTick() - 1));
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "handleEntityEvent(B)V")
    private void handleEntityEvent(byte id, CallbackInfo ci) {
        Mob mob = (Mob) (Object) this;
        LivestockAttachment attachment = mob.getData(EqualsAttachments.LIVESTOCK);
        if (attachment.getEatBlockGoal() != null) {
            if (id == 10) {
                attachment.setEatAnimationTick(40);
            }
        }
    }
}
