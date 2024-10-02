package com.bconlon.vanillaequals.network;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.network.packet.clientbound.*;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class EqualsPackets {
    public static void packetSetup(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(VanillaEquals.MODID).versioned("1.0.0").optional();

        // CLIENTBOUND
        registrar.playToClient(HungerParticlesPacket.TYPE, HungerParticlesPacket.STREAM_CODEC, HungerParticlesPacket::execute);
        registrar.playToClient(SetChickenVariantPacket.TYPE, SetChickenVariantPacket.STREAM_CODEC, SetChickenVariantPacket::execute);
        registrar.playToClient(SetCowVariantPacket.TYPE, SetCowVariantPacket.STREAM_CODEC, SetCowVariantPacket::execute);
        registrar.playToClient(SetPigVariantPacket.TYPE, SetPigVariantPacket.STREAM_CODEC, SetPigVariantPacket::execute);
        registrar.playToClient(SyncLivestockAttachmentPacket.TYPE, SyncLivestockAttachmentPacket.STREAM_CODEC, SyncLivestockAttachmentPacket::execute);
    }
}
