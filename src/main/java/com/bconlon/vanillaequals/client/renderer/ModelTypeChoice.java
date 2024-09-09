package com.bconlon.vanillaequals.client.renderer;

import com.bconlon.vanillaequals.entity.Variant;
import net.minecraft.client.model.EntityModel;

public record ModelTypeChoice(EntityModel<?> defaultModel, EntityModel<?> detailedModel) {
    public EntityModel<?> choose(Variant.ModelType modelType) {
        if (modelType == Variant.ModelType.DETAILED) {
            return this.detailedModel();
        } else {
            return this.defaultModel();
        }
    }
}
