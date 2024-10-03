package com.dotnomi.stranded.networking;

import com.dotnomi.stranded.Stranded;
import com.dotnomi.stranded.networking.packet.ExampleC2SPacket;
import com.dotnomi.stranded.networking.packet.ExampleS2CPacket;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

@SuppressWarnings("SameParameterValue")
public class ModPayloads {
    public static void initialize() {
        Stranded.LOGGER.debug("Initializing ModPayloads");
        registerS2C(ExampleS2CPacket.IDENTIFIER, ExampleS2CPacket.CODEC);
        registerC2S(ExampleC2SPacket.IDENTIFIER, ExampleC2SPacket.CODEC);
    }

    private static <T extends CustomPayload> void registerS2C(
        CustomPayload.Id<T> packetIdentifier,
        PacketCodec<RegistryByteBuf, T> codec
    ) {
        PayloadTypeRegistry.playS2C().register(packetIdentifier, codec);
    }

    private static <T extends CustomPayload> void registerC2S(
        CustomPayload.Id<T> packetIdentifier,
        PacketCodec<RegistryByteBuf, T> codec
    ) {
        PayloadTypeRegistry.playC2S().register(packetIdentifier, codec);
    }
}
