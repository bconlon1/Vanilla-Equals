package com.bconlon.vanillaequals.mixin.mixins.common;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.LivestockAttachment;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.entity.animal.Sheep;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EatBlockGoal.class)
public class EatBlockGoalMixin {
    @Shadow
    @Final
    private Mob mob;

    @ModifyConstant(method = "canUse()Z", constant = @Constant(intValue = 1000))
    private int injected(int value) {
        LivestockAttachment attachment = this.mob.getData(EqualsAttachments.LIVESTOCK);
        if ((this.mob instanceof Sheep sheep && sheep.isSheared()) || attachment.isHungry()) {
            return 100;
        }
        return value;
    }
}
