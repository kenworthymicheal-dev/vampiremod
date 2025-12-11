package net.micheal.vampiremod.network;

import com.mojang.datafixers.types.templates.List;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BloodSuckPacket {
    public static void handle(BloodSuckPacket pkt, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            player.getCapability(VampireDataProvider.VAMP_DATA).ifPresent(vamp -> {
                if (!vamp.isVampire()) return;

                List<LivingEntity> entities = player.level().getEntitiesOfClass(
                        LivingEntity.class,
                        player.getBoundingBox().inflate(2)
                );

                for (LivingEntity e : entities) {
                    if (e == player) continue;
                    if (e.isAlive()) {
                        e.hurt(player.damageSources().playerAttack(player), 2f);
                        vamp.setBlood(vamp.getBlood() + 4);
                        break;
                    }
                }
            });
        });

        ctx.get().setPacketHandled(true);
    }
}

