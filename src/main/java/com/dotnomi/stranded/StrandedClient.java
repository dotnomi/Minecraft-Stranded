package com.dotnomi.stranded;

import com.dotnomi.stranded.client.voiceover.VoiceoverManager;
import com.dotnomi.stranded.event.handler.ClientPlayDisconnectHandler;
import com.dotnomi.stranded.event.handler.KeyInputHandler;
import com.dotnomi.stranded.networking.ModS2CPackets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class StrandedClient implements ClientModInitializer {
    public static final VoiceoverManager VOICEOVER_MANAGER = new VoiceoverManager();

    @Override
    public void onInitializeClient() {
        ModS2CPackets.initialize();
        KeyInputHandler.register();

        ClientPlayConnectionEvents.DISCONNECT.register(new ClientPlayDisconnectHandler());
    }
}
