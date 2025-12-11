package net.micheal.vampiremod.event;

import net.micheal.vampiremod.util.capabilityUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.effect.MobEffectInstance;
public class playerEvents {


    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.level().isClientSide) return;
        if (Player.isVampire()) {
            player.getFoodData().setFoodLevel(0);
            player.getFoodData().setSaturation(0);
            if (player.tickCount % 80 == 0) {
                v.drainBlood(1);
            }

// Starvation if blood is empty
            if (v.getBlood() <= 0) {
                player.hurt(player.damageSources().starve(), 1);


            }


        }
    }
}


