package com.bconlon.vanillaequals.network.packet.clientbound;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.attachment.LivestockAttachment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SyncLivestockAttachmentPacket(int entityID, LivestockAttachment attachment) implements CustomPacketPayload {
    public static final Type<SyncLivestockAttachmentPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "sync_livestock_attachment"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SyncLivestockAttachmentPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SyncLivestockAttachmentPacket::entityID,
            LivestockAttachment.STREAM_CODEC,
            SyncLivestockAttachmentPacket::attachment,
            SyncLivestockAttachmentPacket::new);

    @Override
    public Type<SyncLivestockAttachmentPacket> type() {
        return TYPE;
    }

    public static void execute(SyncLivestockAttachmentPacket payload, IPayloadContext context) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null && Minecraft.getInstance().level.getEntity(payload.entityID) instanceof Entity entity) {
            entity.getData(EqualsAttachments.LIVESTOCK).syncAttachment(payload.attachment());
        }
    }
}
