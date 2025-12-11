package net.micheal.vampiremod.init;

import net.micheal.vampiremod.util.vampireCapability;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.micheal.vampiremod.util.vampireprovider;
public class ModCapabilities {


        public static void register(RegisterCapabilitiesEvent event) {
            event.register(vampireCapability.class);

        }
}
