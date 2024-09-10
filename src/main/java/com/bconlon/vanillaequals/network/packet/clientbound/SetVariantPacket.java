package com.bconlon.vanillaequals.network.packet.clientbound;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.entity.Variant;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;

import java.util.function.Function;

public abstract class SetVariantPacket implements CustomPacketPayload {
    protected final int entityID;
    protected final String variantName;

    public SetVariantPacket(int entityID, String variantName) {
        this.entityID = entityID;
        this.variantName = variantName;
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

    public int entityID() {
        return this.entityID;
    }

    public String variantName() {
        return this.variantName;
    }
}
