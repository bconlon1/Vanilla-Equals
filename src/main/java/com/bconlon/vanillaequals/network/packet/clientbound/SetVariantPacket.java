package com.bconlon.vanillaequals.network.packet.clientbound;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.entity.passive.CowVariant;
import com.bconlon.vanillaequals.entity.passive.Variant;
import com.bconlon.vanillaequals.network.packet.BasePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public abstract class SetVariantPacket implements BasePacket {
    private final int entityID;
    private final String variantName;

    public SetVariantPacket(int entityID, String variantName) {
        this.entityID = entityID;
        this.variantName = variantName;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.entityID);
        buf.writeUtf(this.variantName);
    }

    public void execute(Function<String, Variant> function) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            Entity entity = Minecraft.getInstance().player.level().getEntity(this.entityID);
            if (entity != null) {
                Variant variant = function.apply(this.variantName);
                entity.getData(EqualsAttachments.MOB_VARIANT).setVariant(variant);
            }
        }
    }

    public static class Cow extends SetVariantPacket {
        public static final ResourceLocation ID = new ResourceLocation(VanillaEquals.MODID, "set_cow_variant");

        public Cow(int entityID, String variantName) {
            super(entityID, variantName);
        }

        @Override
        public ResourceLocation id() {
            return ID;
        }

        public static SetVariantPacket.Cow decode(FriendlyByteBuf buf) {
            int entityID = buf.readInt();
            String variantName = buf.readUtf();
            return new SetVariantPacket.Cow(entityID, variantName);
        }

        @Override
        public void execute(@Nullable Player player) {
            this.execute(CowVariant.GET);
        }
    }
}
