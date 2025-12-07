package net.micheal.vampiremod.base;
import net.micheal.vampiremod.event;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpendPointPacket {
    private final String abilityId;

    public SpendPointPacket(String abilityId) {
        this.abilityId = abilityId;
    }

    public static void encode(SpendPointPacket pkt, FriendlyByteBuf buf) {
        buf.writeUtf(pkt.abilityId);
    }

    public static SpendPointPacket decode(FriendlyByteBuf buf) {
        return new SpendPointPacket(buf.readUtf(32767));
    }

    public static void handle(SpendPointPacket pkt, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            if (!(ctx.getSender() instanceof ServerPlayer player)) return;
            if (!VampireData.isVampire(player)) {
                player.sendMessage(net.minecraft.network.chat.Component.literal("You are not a vampire."), player.getUUID());
                return;
            }
            if (VampireData.getAbilityPoints(player) <= 0) {
                player.sendMessage(net.minecraft.network.chat.Component.literal("No ability points available."), player.getUUID());
                return;
            }
            VampireData.spendAbilityPoint(player, pkt.abilityId);
        });
        ctx.setPacketHandled(true);


    }
}
