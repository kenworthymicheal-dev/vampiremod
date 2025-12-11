package net.micheal.vampiremod.util;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;

public class vampireprovider {
    public static final Capability<vampireCapability> VAMPIRE_CAP =
            CapabilityManager.get(new CapabilityToken<>() {});

    private final vampireCapability instance = new vampireCapability();
    private final LazyOptional<vampireCapability> optional = LazyOptional.of(() -> instance);

    1@Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == VAMPIRE_CAP ? optional.cast() : LazyOptional.empty();
    }




}
