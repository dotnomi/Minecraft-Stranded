package com.dotnomi.stranded.networking;

import com.dotnomi.stranded.Stranded;
import com.dotnomi.stranded.networking.packet.ExampleC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ModC2SPackets {
    public static void initialize() {
        Stranded.LOGGER.debug("Initializing client to server packets");
        ServerPlayNetworking.registerGlobalReceiver(ExampleC2SPacket.IDENTIFIER, ExampleC2SPacket::handlePacket);
    }
}
