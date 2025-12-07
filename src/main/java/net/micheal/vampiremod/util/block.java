package net.micheal.vampiremod.util;

import net.micheal.vampiremod.vampiremod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Properties;

public class  block {
    private static final double BLOOD_PER_VIAL = 10.0;
    private static final double XP_PER_VIAL = 20.0;


    public VampireAltarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS; // client: show animation only
        }

        if (!(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResult.CONSUME;
        }

        // check chest slot for the vampire medallion
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!chest.isEmpty() && chest.getItem() == vampiremod.MOD_ID.VAMPIRE_MEDALLION.get()) {
            // Convert the player to a vampire
            if (VampireData.isVampire(serverPlayer)) {
                serverPlayer.sendSystemMessage(Component.literal("You are already a vampire."));
                return InteractionResult.SUCCESS;
            }

            // consume the medallion (armor items don't normally stack; safe to clear the slot)
            player.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);

            VampireData.setVampire(serverPlayer, true);
            VampireData.initializeVampireStats(serverPlayer);

            serverPlayer.sendSystemMessage(Component.literal("The altar consumes the medallion... You feel the hunger awaken."));

            return InteractionResult.SUCCESS;
        } else {
            serverPlayer.sendSystemMessage(Component.literal("You must be wearing the vampire medallion to activate this altar."));
            return InteractionResult.SUCCESS;
        }
        // 2) If holding a blood vial: deposit blood into altar to gain blood and vampiric XP
        ItemStack held = player.getItemInHand(hand);
        if (!held.isEmpty() && held.getItem() == VampirismMod.BLOOD_VIAL.get()) {
            // require the player to already be a vampire to gain blood/xp from the altar
            if (!VampireData.isVampire(serverPlayer)) {
                serverPlayer.sendSystemMessage(Component.literal("Only vampires may offer blood to the altar."));
                return InteractionResult.SUCCESS;
            }

            double current = VampireData.getBloodCurrent(serverPlayer);
            double max = VampireData.getBloodMax(serverPlayer);
            if (current >= max) {
                serverPlayer.sendSystemMessage(Component.literal("Your blood pool is already full."));
                return InteractionResult.SUCCESS;
            }

            // Consume one vial
            ItemStack newStack = held.copy();
            newStack.shrink(1);
            player.setItemInHand(hand, newStack);

            // Add blood, capped at max
            double added = Math.min(BLOOD_PER_VIAL, max - current);
            VampireData.setBloodCurrent(serverPlayer, current + added);

            // give vampiric XP (this may level the vampire via VampireData.addXp)
            VampireData.addXp(serverPlayer, XP_PER_VIAL);

            serverPlayer.sendSystemMessage(Component.literal("You offer a blood vial to the altar. +" + (int)added + " blood, +" + (int)XP_PER_VIAL + " XP."));
            return InteractionResult.SUCCESS;
        }

        // default: tell player how to use altar
        serverPlayer.sendSystemMessage(Component.literal("Wear the vampire medallion to convert, or use a blood vial to feed the altar."));
        return InteractionResult.SUCCESS;
    }

    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, BlockPos neighborPos, BlockState neighborState, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, neighborPos, neighborState, isMoving);
    }


    }

    // Optional: prevent placement side-effects (not required)
    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, BlockPos neighborPos, BlockState neighborState, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, neighborPos, neighborState, isMoving);
    }





}
