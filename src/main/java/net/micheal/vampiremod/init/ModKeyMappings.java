package net.micheal.vampiremod.init;

import com.google.common.graph.Network;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = "vampiremod", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModKeyMappings {

    public static KeyMapping BLOOD_SUCK;
    public static KeyMapping ABILITY_MENU;

    public static void init() {
        BLOOD_SUCK = new KeyMapping("key.vampiremod.blood_suck", GLFW.GLFW_KEY_R, "key.categories.gameplay");
        ABILITY_MENU = new KeyMapping("key.vampiremod.open_ability_menu", GLFW.GLFW_KEY_K, "key.categories.gameplay");
    }

    @SubscribeEvent
    public static void onRegisterKeys(RegisterKeyMappingsEvent event) {
        event.register(BLOOD_SUCK);
        event.register(ABILITY_MENU);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        while (BLOOD_SUCK.consumeClick()) {
            Network.CHANNEL.sendToServer(new BloodSuckPacket());
        }

        while (ABILITY_MENU.consumeClick()) {
            mc.setScreen(new net.micheal.vampiremod.client.screen.AbilityScreen(null, mc.player.getInventory(), Component.literal("Abilities")));
        }
    }
}