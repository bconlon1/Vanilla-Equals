package com.bconlon.vanillaequals.mixin.mixins.common;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.LivestockAttachment;
import com.bconlon.vanillaequals.network.packet.clientbound.SyncLivestockAttachmentPacket;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Cow.class)
public abstract class CowMixin extends Animal {
    protected CowMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemUtils;createFilledResult(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;"), method = "mobInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;", cancellable = true)
    private void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        Cow cow = (Cow) (Object) this;
        LivestockAttachment attachment = cow.getData(EqualsAttachments.LIVESTOCK);
        if (attachment.getEatBlockGoal() != null) {
            if (!attachment.isHungry()) {
                attachment.setHungry(true);
                PacketDistributor.sendToAllPlayers(new SyncLivestockAttachmentPacket(cow.getId(), attachment));
            }
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V", shift = At.Shift.BEFORE), method = "mobInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;", cancellable = true)
    private void mobInteractCancellation(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        Cow cow = (Cow) (Object) this;
        ItemStack stack = player.getItemInHand(hand);
        LivestockAttachment attachment = cow.getData(EqualsAttachments.LIVESTOCK);
        if (attachment.getEatBlockGoal() != null) {
            if (attachment.isHungry()) {
                if (cow.level().isClientSide()) {
                    for (int i = 0; i < 7; i++) {
                        double d0 = cow.getRandom().nextGaussian() * 0.02;
                        double d1 = cow.getRandom().nextGaussian() * 0.02;
                        double d2 = cow.getRandom().nextGaussian() * 0.02;
                        cow.level().addParticle(ParticleTypes.ANGRY_VILLAGER, cow.getRandomX(1.0), cow.getRandomY() + 0.5, cow.getRandomZ(1.0), d0, d1, d2);
                    }
                }
                cir.setReturnValue(InteractionResult.PASS);
            }
        }
    }
}
