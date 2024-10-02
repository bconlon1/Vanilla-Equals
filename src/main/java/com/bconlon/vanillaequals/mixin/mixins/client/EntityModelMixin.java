package com.bconlon.vanillaequals.mixin.mixins.client;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.LivestockAttachment;
import com.bconlon.vanillaequals.mixin.mixins.client.accessor.ChickenModelAccessor;
import com.bconlon.vanillaequals.mixin.mixins.client.accessor.QuadrupedModelAccessor;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityModel.class)
public class EntityModelMixin<T extends Entity> {
    @Inject(at = @At("TAIL"), method = "prepareMobModel(Lnet/minecraft/world/entity/Entity;FFF)V")
    private void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick, CallbackInfo ci) {
        EntityModel<T> model = (EntityModel<T>) (Object) this;
        LivestockAttachment attachment = entity.getData(EqualsAttachments.LIVESTOCK);
        if (attachment.getEatBlockGoal() != null) {
            if (model instanceof QuadrupedModel<T> quadrupedModel) {
                float yOffset = 0.0F; //todo need to make positions dependent on age of mob
                float multiplier = 1.0F;
                if (entity instanceof Cow) {
                    yOffset = 4.0F;
                    multiplier = 2.0F;
                } else if (entity instanceof Pig) {
                    yOffset = 12.0F;
                    multiplier = 6.0F;
                }
                ((QuadrupedModelAccessor) quadrupedModel).vanilla_equals$getHead().y = yOffset + attachment.getHeadEatPositionScale(partialTick) * multiplier;
                attachment.setHeadXRot(attachment.getHeadEatAngleScale(entity, partialTick));
            } else if (model instanceof ChickenModel<T> chickenModel) {
                ((ChickenModelAccessor) chickenModel).vanilla_equals$getHead().y = 15 + attachment.getHeadEatPositionScale(partialTick) * 2.0F;
                ((ChickenModelAccessor) chickenModel).vanilla_equals$getBeak().y = 15 + attachment.getHeadEatPositionScale(partialTick) * 2.0F;
                ((ChickenModelAccessor) chickenModel).vanilla_equals$getRedThing().y = 15 + attachment.getHeadEatPositionScale(partialTick) * 2.0F;
                attachment.setHeadXRot(attachment.getHeadEatAngleScale(entity, partialTick));
            }
        }
    }
}
