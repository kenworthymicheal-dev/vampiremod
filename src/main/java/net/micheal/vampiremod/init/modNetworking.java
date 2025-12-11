package net.micheal.vampiremod.init;

import net.micheal.vampiremod.network.BloodSuckPacket;
import net.micheal.vampiremod.vampiremod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class modNetworking {
    private static int id = 0;

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(vampiremod.MOD_ID, "network"),
            () -> "1.0",
            s -> true,
            s -> true
    );

    public static void register() {
        CHANNEL.registerMessage(id++,
                BloodSuckPacket.class,
                BloodSuckPacket::encode,
                BloodSuckPacket::decode,
                BloodSuckPacket::handle);
    }
}
}
