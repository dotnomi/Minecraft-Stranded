package com.dotnomi.stranded;

import com.dotnomi.stranded.event.KeyInputHandler;
import com.dotnomi.stranded.networking.ModS2CPackets;
import net.fabricmc.api.ClientModInitializer;

public class StrandedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModS2CPackets.initialize();
        KeyInputHandler.register();
    }
}
