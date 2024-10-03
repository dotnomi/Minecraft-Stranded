package com.dotnomi.stranded.event;

import com.dotnomi.stranded.Stranded;
import com.dotnomi.stranded.networking.packet.ExampleC2SPacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Language;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_STRANDED = "key.category.stranded";
    public static final String KEY_DEBUG = "key.stranded.debug";

    public static KeyBinding debugKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(client.player != null && debugKey.wasPressed()) {
                client.player.sendMessage(Text.translatable("message.stranded.debug_key_pressed"));

                Language language = TranslationStorage.getInstance();
                Stranded.LOGGER.info(language.get("message.stranded.server_received", "NIX"));
                //ClientPlayNetworking.send(new ExampleC2SPacket(1));
            }
        });
    }

    public static void register() {
        debugKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            KEY_DEBUG, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_STRANDED
        ));

        registerKeyInputs();
    }
}
