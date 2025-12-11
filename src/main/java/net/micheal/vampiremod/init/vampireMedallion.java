package net.micheal.vampiremod.init;

import net.micheal.vampiremod.util.capabilityUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

public class vampireMedallion extends Item {
    public VampireMedallion(Properties props) {
        super(props);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (!capabilityUtil.CapabilityUtil.isVampire(player)) {
                capabilityUtil.CapabilityUtil.setVampire(player, true);
                player.displayClientMessage(Component.literal("The ancient power consumes you..."), true);
                stack.shrink(1);
            }
        }

        return InteractionResultHolder.success(stack);
    }
}

