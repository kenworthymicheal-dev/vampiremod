package net.micheal.vampiremod.abilites;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class vampireAbilityHandler {

    // Called each tick by PlayerEvents
    public static void applyLevelAbilities(Player player, int level) {

        switch (level) {

            case 1 -> applyLevelOneAbilities(player);

            // Future levels:
            // case 2 -> applyLevelTwoAbilities(player);
            // case 3 -> applyLevelThreeAbilities(player);

        }
    }

    private static void applyLevelOneAbilities(Player player) {
        // Strength 1
        player.addEffect(new MobEffectInstance(
                MobEffects.DAMAGE_BOOST, 220, 0, true, false, false
        ));

        // Speed 1
        player.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SPEED, 220, 0, true, false, false
        ));
    }
}










