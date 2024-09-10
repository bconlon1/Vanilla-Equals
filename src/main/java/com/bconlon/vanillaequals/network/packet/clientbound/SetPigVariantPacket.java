package com.bconlon.vanillaequals.network.packet.clientbound;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.entity.passive.variant.PigVariant;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SetPigVariantPacket extends SetVariantPacket {
    public static final Type<SetPigVariantPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "set_pig_variant"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SetPigVariantPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SetVariantPacket::entityID,
            ByteBufCodecs.STRING_UTF8,
            SetVariantPacket::variantName,
            SetPigVariantPacket::new);

    public SetPigVariantPacket(int entityID, String variantName) {
        super(entityID, variantName);
    }

    @Override
    public Type<SetPigVariantPacket> type() {
        return TYPE;
    }

    public static void execute(SetPigVariantPacket payload, IPayloadContext context) {
        payload.execute(PigVariant.GET);
    }
}
