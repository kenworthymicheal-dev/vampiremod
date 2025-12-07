package net.micheal.vampiremod;

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

    private static class ClientInit {
        ClientInit() {
            IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
            bus.addListener((FMLClientSetupEvent evt) -> {
                // register screen: this requires the client-side class to exist
                net.minecraftforge.client.gui.screens.MenuScreens.register(VAMPIRE_MENU.get(), com.kenworthymicheal.vampirism.menu.VampireAbilitiesScreen::new);
            });
        }
    }
}
