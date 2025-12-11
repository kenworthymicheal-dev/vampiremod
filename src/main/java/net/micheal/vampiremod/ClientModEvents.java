package net.micheal.vampiremod;

import net.micheal.vampiremod.client.BloodHudOverlay;
import net.micheal.vampiremod.network.BloodSuckPacket;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = vampiremod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {


    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        ModKeybinds.register(event);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (ModKeybinds.BLOOD_SUCK_KEY.consumeClick()) {
            ModNetworking.CHANNEL.sendToServer(new BloodSuckPacket());
        }
        public static void registerGui(GuiOverlayEvent.Register event) {
            event.registerAbove(VanillaGuiOverlay.FOOD_LEVEL.id(), "bloodbar", new BloodHudOverlay());
        }

    }

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Pre event) {
        // Cancel hunger overlay
        if (event.getOverlay() == VanillaGuiOverlay.FOOD_LEVEL.type()) {
            event.setCanceled(true);
        }
    }
}

