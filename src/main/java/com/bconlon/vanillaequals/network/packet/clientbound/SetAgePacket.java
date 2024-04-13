package com.bconlon.vanillaequals.network.packet.clientbound;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.network.packet.BasePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class SetAgePacket implements BasePacket {
    public static final ResourceLocation ID = new ResourceLocation(VanillaEquals.MODID, "set_age");

    private final int entityID;
    private final int age;

    public SetAgePacket(int entityID, int age) {
        this.entityID = entityID;
        this.age = age;
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeInt(this.entityID);
        buffer.writeInt(this.age);
    }

    public static SetAgePacket decode(FriendlyByteBuf buf) {
        int entityID = buf.readInt();
        int age = buf.readInt();
        return new SetAgePacket(entityID, age);
    }

    @Override
    public void execute(@Nullable Player player) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            Entity entity = Minecraft.getInstance().player.level().getEntity(this.entityID);
            if (entity != null) {
                entity.getData(EqualsAttachments.AGEABLE).setAge(entity, this.age);
            }
        }
    }
}
