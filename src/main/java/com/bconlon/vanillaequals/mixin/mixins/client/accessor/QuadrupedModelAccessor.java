package com.bconlon.vanillaequals.mixin.mixins.client.accessor;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(QuadrupedModel.class)
public interface QuadrupedModelAccessor {
    @Accessor("head")
    ModelPart vanilla_equals$getHead();
}
