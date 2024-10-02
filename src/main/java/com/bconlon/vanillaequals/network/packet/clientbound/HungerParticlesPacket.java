package com.bconlon.vanillaequals.network.packet.clientbound;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record HungerParticlesPacket(int entityID) implements CustomPacketPayload {
    public static final Type<HungerParticlesPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "hunger_particles"));

    public static final StreamCodec<RegistryFriendlyByteBuf, HungerParticlesPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            HungerParticlesPacket::entityID,
            HungerParticlesPacket::new);

    @Override
    public Type<HungerParticlesPacket> type() {
        return TYPE;
    }

    public static void execute(HungerParticlesPacket payload, IPayloadContext context) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null && Minecraft.getInstance().level.getEntity(payload.entityID) instanceof Entity entity) {
            for (int i = 0; i < 7; i++) {
                double d0 = entity.getRandom().nextGaussian() * 0.02;
                double d1 = entity.getRandom().nextGaussian() * 0.02;
                double d2 = entity.getRandom().nextGaussian() * 0.02;
                entity.level().addParticle(ParticleTypes.ANGRY_VILLAGER, entity.getRandomX(1.0), entity.getRandomY() + 0.5, entity.getRandomZ(1.0), d0, d1, d2);
            }
        }
    }
}
