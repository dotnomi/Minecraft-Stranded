package com.dotnomi.stranded.networking;

import com.dotnomi.stranded.Stranded;
import com.dotnomi.stranded.networking.packet.ExampleS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ModS2CPackets {
    public static void initialize() {
        Stranded.LOGGER.debug("Initializing server to client packets");
        ClientPlayNetworking.registerGlobalReceiver(ExampleS2CPacket.IDENTIFIER, ExampleS2CPacket::handlePacket);
    }
}
