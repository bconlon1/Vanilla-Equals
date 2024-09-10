package com.bconlon.vanillaequals.network.packet.clientbound;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.entity.passive.variant.CowVariant;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SetCowVariantPacket extends SetVariantPacket {
    public static final Type<SetCowVariantPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "set_cow_variant"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SetCowVariantPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SetVariantPacket::entityID,
            ByteBufCodecs.STRING_UTF8,
            SetVariantPacket::variantName,
            SetCowVariantPacket::new);

    public SetCowVariantPacket(int entityID, String variantName) {
        super(entityID, variantName);
    }

    @Override
    public Type<SetCowVariantPacket> type() {
        return TYPE;
    }

    public static void execute(SetCowVariantPacket payload, IPayloadContext context) {
        payload.execute(CowVariant.GET);
    }
}
