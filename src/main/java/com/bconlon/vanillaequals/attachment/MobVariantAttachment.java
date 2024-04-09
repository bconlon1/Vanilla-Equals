package com.bconlon.vanillaequals.attachment;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.entity.passive.Variant;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.function.Function;

public class MobVariantAttachment {
    public static final Codec<MobVariantAttachment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("variant").forGetter(MobVariantAttachment::getVariantName)
    ).apply(instance, MobVariantAttachment::new));

    private Variant variant;
    private String variantName = "";

    protected MobVariantAttachment() {

    }

    private MobVariantAttachment(String variantName) {
        this.variantName = variantName;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
        this.variantName = variant.getSerializedName();
    }

    public Variant getVariant(Function<String, Variant> function) {
        if (this.variant == null) {
            this.variant = function.apply(this.variantName);
        }
        return this.variant;
    }

    public String getVariantName() {
        return this.variantName;
    }
}
