package com.bconlon.vanillaequals.entity;

import com.bconlon.vanillaequals.entity.Variant;
import net.minecraft.world.entity.AgeableMob;

public class VariantGroupData extends AgeableMob.AgeableMobGroupData {
    public final Variant variant;

    public VariantGroupData(Variant variant) {
        super(1.0F);
        this.variant = variant;
    }
}
