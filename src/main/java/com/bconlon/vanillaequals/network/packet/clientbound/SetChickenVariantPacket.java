package com.bconlon.vanillaequals.network.packet.clientbound;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.entity.passive.variant.ChickenVariant;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SetChickenVariantPacket extends SetVariantPacket {
    public static final Type<SetChickenVariantPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VanillaEquals.MODID, "set_chicken_variant"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SetChickenVariantPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SetVariantPacket::entityID,
            ByteBufCodecs.STRING_UTF8,
            SetVariantPacket::variantName,
            SetChickenVariantPacket::new);

    public SetChickenVariantPacket(int entityID, String variantName) {
        super(entityID, variantName);
    }

    @Override
    public Type<SetChickenVariantPacket> type() {
        return TYPE;
    }

    public static void execute(SetChickenVariantPacket payload, IPayloadContext context) {
        payload.execute(ChickenVariant.GET);
    }
}
