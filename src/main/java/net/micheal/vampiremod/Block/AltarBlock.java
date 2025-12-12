package net.micheal.vampiremod.Block;

import net.micheal.vampiremod.capability.IVampire;
import net.micheal.vampiremod.capability.ModCapabilities;
import net.micheal.vampiremod.network.ModMessages;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.property.Properties;

public class AltarBlock extends Block {

    public AltarBlock(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {

        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        player.getCapability(ModCapabilities.VAMPIRE_CAP).ifPresent(v -> {

            // Not a vampire
            if (v.isVampire()) {
                int currentLevel = v.getLevel();
                int required = getBloodRequired(currentLevel);

                // Max level reached
                if (currentLevel >= 5) {
                    player.displayClientMessage(Component.literal("§eYou already reached max level!"), true);
                    return;
                }

                // Not enough blood
                if (v.getBlood() < required) {
                    player.displayClientMessage(
                            Component.literal("§cYou need " + required + " blood."),
                            true
                    );
                    return;
                }

                // Consume blood & level up
                v.setBlood(v.getBlood() - required);
                v.setLevel(currentLevel + 1);
                v.addAbilityPoint(1);

                applyRewards(v);

                player.displayClientMessage(
                        Component.literal("§4You feel your vampire power grow..."),
                        true
                );

                // Sync capability to client
                ModMessages.syncToPlayer((ServerPlayer) player);
            } else {
                player.displayClientMessage(Component.literal("§cYou feel nothing."), true);
                return;
            }

        });

        return InteractionResult.SUCCESS;
    }

    private int getBloodRequired(int level) {
        return switch (level) {
            case 1 -> 30;
            case 2 -> 60;
            case 3 -> 120;
            case 4 -> 200;
            default -> 999999;
        };
    }

    private void applyRewards(IVampire v) {
        switch (v.getLevel()) {
            case 2 -> v.setMaxBlood(v.getMaxBlood() + 20);
            case 3 -> v.unlockAbility(0);
            case 4 -> v.unlockAbility(1);
            case 5 -> v.unlockAbility(2); // Major ability
        }
    }
}