package com.bconlon.vanillaequals.mixin.mixins.client.accessor;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChickenModel.class)
public interface ChickenModelAccessor {
    @Accessor("head")
    ModelPart vanilla_equals$getHead();

    @Accessor("beak")
    ModelPart vanilla_equals$getBeak();

    @Accessor("redThing")
    ModelPart vanilla_equals$getRedThing();
}
