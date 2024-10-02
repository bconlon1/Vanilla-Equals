package com.bconlon.vanillaequals.mixin.mixins.client;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.LivestockAttachment;
import com.bconlon.vanillaequals.mixin.mixins.client.accessor.ChickenModelAccessor;
import com.bconlon.vanillaequals.mixin.mixins.client.accessor.QuadrupedModelAccessor;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChickenModel.class)
public class ChickenModelMixin<T extends Entity> {
    @Inject(at = @At("TAIL"), method = "setupAnim(Lnet/minecraft/world/entity/Entity;FFFFF)V")
    private void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        EntityModel<T> model = (EntityModel<T>) (Object) this;
        LivestockAttachment attachment = entity.getData(EqualsAttachments.LIVESTOCK);
        if (attachment.getEatBlockGoal() != null) {
            if (model instanceof ChickenModel<T> chickenModel) {
                ((ChickenModelAccessor) chickenModel).vanilla_equals$getHead().xRot = attachment.getHeadXRot();
                ((ChickenModelAccessor) chickenModel).vanilla_equals$getBeak().xRot = attachment.getHeadXRot();
                ((ChickenModelAccessor) chickenModel).vanilla_equals$getRedThing().xRot = attachment.getHeadXRot();
            }
        }
    }
}
