package com.bconlon.vanillaequals.network;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.network.packet.clientbound.SetAgePacket;
import com.bconlon.vanillaequals.network.packet.clientbound.SetVariantPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

public class EqualsPackets {
    public static void packetSetup(RegisterPayloadHandlerEvent event) {
        IPayloadRegistrar registrar = event.registrar(VanillaEquals.MODID).versioned("1.0.0").optional();

        // CLIENTBOUND
        registrar.play(SetAgePacket.ID, SetAgePacket::decode, payload -> payload.client(SetAgePacket::handle));
        registrar.play(SetVariantPacket.Chicken.ID, SetVariantPacket.Chicken::decode, payload -> payload.client(SetVariantPacket.Chicken::handle));
        registrar.play(SetVariantPacket.Cow.ID, SetVariantPacket.Cow::decode, payload -> payload.client(SetVariantPacket.Cow::handle));
        registrar.play(SetVariantPacket.Pig.ID, SetVariantPacket.Pig::decode, payload -> payload.client(SetVariantPacket.Pig::handle));
    }

    public static <MSG extends CustomPacketPayload> void sendToPlayer(MSG message, ServerPlayer player) {
        PacketDistributor.PLAYER.with(player).send(message); // Clientbound
    }

    public static <MSG extends CustomPacketPayload> void sendToNear(MSG message, double x, double y, double z, double radius, ResourceKey<Level> dimension) {
        PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p(x, y, z, radius, dimension).get()).send(message); // Clientbound
    }

    public static <MSG extends CustomPacketPayload> void sendToAll(MSG message) {
        PacketDistributor.ALL.noArg().send(message); // Clientbound
    }

    public static <MSG extends CustomPacketPayload> void sendToServer(MSG message) {
        PacketDistributor.SERVER.noArg().send(message); // Serverbound
    }

    public static <MSG extends CustomPacketPayload> void sendToDimension(MSG message, ResourceKey<Level> dimension) {
        PacketDistributor.DIMENSION.with(dimension).send(message); // Clientbound
    }
}
