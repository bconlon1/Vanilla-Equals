package com.bconlon.vanillaequals.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;

public class AgeableAttachment {
    public static final Codec<AgeableAttachment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("baby").forGetter(AgeableAttachment::isBaby),
            Codec.INT.fieldOf("age").forGetter(AgeableAttachment::getAge)
    ).apply(instance, AgeableAttachment::new));

    protected boolean baby;
    protected int age;

    protected AgeableAttachment() {

    }

    private AgeableAttachment(boolean baby, int age) {
        this.baby = baby;
        this.age = age;
    }

    public void tickAge(Entity entity) {
        if (!entity.level().isClientSide()) {
            if (entity.isAlive()) {
                int i = this.getAge();
                if (i < 0) {
                    this.setAge(entity, ++i);
                } else if (i > 0) {
                    this.setAge(entity, --i);
                }
            }
        }
    }

    protected void ageBoundaryReached(Entity entity) {
        if (!this.isBaby(entity) && entity.isPassenger()) {
            Entity vehicle = entity.getVehicle();
            if (vehicle instanceof Boat boat && !boat.hasEnoughSpaceFor(entity)) {
                entity.stopRiding();
            }
        }
    }

    public void setBaby(boolean baby) {
        this.baby = baby;
    }

    public boolean isBaby() {
        return this.baby;
    }

    public boolean isBaby(Entity entity) {
        return this.getAge(entity) < 0;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAge(Entity entity, int age) {
        int i = this.getAge(entity);
        this.setAge(age);
        if (i < 0 && age >= 0 || i >= 0 && age < 0) {
            this.setBaby(age < 0);
            this.ageBoundaryReached(entity);
        }
    }

    public int getAge() {
        return this.age;
    }

    public int getAge(Entity entity) {
        if (entity.level().isClientSide()) {
            return this.isBaby() ? -1 : 1;
        } else {
            return this.getAge();
        }
    }
}
