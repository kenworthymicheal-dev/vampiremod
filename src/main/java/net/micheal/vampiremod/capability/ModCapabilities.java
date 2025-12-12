package net.micheal.vampiremod.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;


public class ModCapabilities {
    public static final Capability<IVampire> VAMPIRE_CAP = CapabilityManager.get(new CapabilityToken<IVampire>(){});
}