package com.bconlon.vanillaequals.network;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.network.packet.clientbound.SetChickenVariantPacket;
import com.bconlon.vanillaequals.network.packet.clientbound.SetCowVariantPacket;
import com.bconlon.vanillaequals.network.packet.clientbound.SetPigVariantPacket;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class EqualsPackets {
    public static void packetSetup(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(VanillaEquals.MODID).versioned("1.0.0").optional();

        // CLIENTBOUND
        registrar.playToClient(SetChickenVariantPacket.TYPE, SetChickenVariantPacket.STREAM_CODEC, SetChickenVariantPacket::execute);
        registrar.playToClient(SetCowVariantPacket.TYPE, SetCowVariantPacket.STREAM_CODEC, SetCowVariantPacket::execute);
        registrar.playToClient(SetPigVariantPacket.TYPE, SetPigVariantPacket.STREAM_CODEC, SetPigVariantPacket::execute);
    }
}
