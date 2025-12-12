package net.micheal.vampiremod.Items;

import SyncVampirePacket.SyncVampirePacket;
import net.micheal.vampiremod.capability.ModCapabilities;
import net.micheal.vampiremod.network.ModMessages;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.property.Properties;

public class VampireMedallion extends Item {

    public VampireMedallion(Properties props) {
        super(props);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide) {
            player.getCapability(ModCapabilities.VAMPIRE_CAP).ifPresent(v -> {

                if (!v.isVampire()) {

                    // TURN PLAYER INTO A VAMPIRE
                    v.setVampire(true);
                    v.setLevel(1);
                    v.setMaxBlood(100);
                    v.setBlood(100);

                    player.displayClientMessage(
                            Component.literal("§4You feel darkness awaken within you..."),
                            true
                    );

                    // SYNC TO CLIENT
                    if (player instanceof ServerPlayer sp) {
                        ModMessages.sendToPlayer(sp, new SyncVampirePacket(v));
                    }

                } else {
                    player.displayClientMessage(
                            Component.literal("§cYou are already a vampire."),
                            true
                    );
                }

            });
        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }
}
