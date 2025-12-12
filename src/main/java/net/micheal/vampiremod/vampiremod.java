package net.micheal.vampiremod;

import com.google.common.graph.Network;
import net.micheal.vampiremod.events.BloodDrainHandler;
import net.micheal.vampiremod.init.ModBlocks;
import net.micheal.vampiremod.init.ModItems;
import net.micheal.vampiremod.init.ModKeyMappings;
import net.micheal.vampiremod.init.ModMenus;
import net.micheal.vampiremod.network.ModMessages;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(vampiremod.MOD_ID)
public class vampiremod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "vampire_mod";
;


    public vampiremod()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(bus);
        ModBlocks.register(bus);
        ModMenus.register(bus);

        // register capability and network in common setup
        bus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    vampiremod(BloodDrainHandler.class) {

    }

    public static ResourceLocation rl(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        ModMessages.register();
    }

    private void clientSetup(FMLClientSetupEvent event) {
        ModKeyMappings.init();
    }


    }





