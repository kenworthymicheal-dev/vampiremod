package net.micheal.vampiremod.init;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class modKeybinds {

    public static KeyMapping BLOOD_SUCK_KEY;

    public static void register(RegisterKeyMappingsEvent event) {
        BLOOD_SUCK_KEY = new KeyMapping(
                "key.vampiremod.blood_suck",
                GLFW.GLFW_KEY_R,
                "key.categories.gameplay"
        );
        event.register(BLOOD_SUCK_KEY);
    }
}
