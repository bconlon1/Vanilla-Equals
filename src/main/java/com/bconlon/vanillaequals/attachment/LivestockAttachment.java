package com.bconlon.vanillaequals.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;

public class LivestockAttachment {
    public static final Codec<LivestockAttachment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("hungry").forGetter(LivestockAttachment::isHungry)
    ).apply(instance, LivestockAttachment::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, LivestockAttachment> STREAM_CODEC = ByteBufCodecs.fromCodecWithRegistries(CODEC);

    private boolean hungry;

    private int eatAnimationTick;
    private EatBlockGoal eatBlockGoal;
    private float headXRot;

    protected LivestockAttachment() {

    }

    private LivestockAttachment(boolean hungry) {
        this.hungry = hungry;
    }

    public float getHeadEatPositionScale(float partialTick) {
        if (this.eatAnimationTick <= 0) {
            return 0.0F;
        } else if (this.eatAnimationTick >= 4 && this.eatAnimationTick <= 36) {
            return 1.0F;
        } else {
            return this.eatAnimationTick < 4 ? ((float) this.eatAnimationTick - partialTick) / 4.0F : -((float) (this.eatAnimationTick - 40) - partialTick) / 4.0F;
        }
    }

    public float getHeadEatAngleScale(Entity entity, float partialTick) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float f = ((float) (this.eatAnimationTick - 4) - partialTick) / 32.0F;
            return (float) (Math.PI / 5) + 0.21991149F * Mth.sin(f * 28.7F);
        } else {
            return this.eatAnimationTick > 0 ? (float) (Math.PI / 5) : entity.getXRot() * (float) (Math.PI / 180.0);
        }
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public boolean isHungry() {
        return this.hungry;
    }

    public void setEatAnimationTick(int eatAnimationTick) {
        this.eatAnimationTick = eatAnimationTick;
    }

    public int getEatAnimationTick() {
        return this.eatAnimationTick;
    }

    public void setEatBlockGoal(EatBlockGoal eatBlockGoal) {
        this.eatBlockGoal = eatBlockGoal;
    }

    public EatBlockGoal getEatBlockGoal() {
        return this.eatBlockGoal;
    }

    public void setHeadXRot(float headXRot) {
        this.headXRot = headXRot;
    }

    public float getHeadXRot() {
        return this.headXRot;
    }

    public void syncAttachment(LivestockAttachment other) {
        this.hungry = other.hungry;
    }
}
