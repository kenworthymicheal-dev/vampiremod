package net.micheal.vampiremod.network;

import net.micheal.vampiremod.vampiremod;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    private static int packetId = 0;
    private static int id() { return packetId++; }

    public static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(vampiremod.MOD_ID.rl("messages"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .simpleChannel();

    public static void register() {

        CHANNEL.messageBuilder(SyncVampirePacket.class, id())
                .encoder(SyncVampirePacket::toBytes)
                .decoder(SyncVampirePacket::new)
                .consumerNetworkThread(SyncVampirePacket::handle)
                .add();
    }

    // ✔ This is the correct method for 1.19.2
    public static void sendToPlayer(ServerPlayer player, Object packet) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static void syncToPlayer(ServerPlayer player) {
    }
}
