package net.micheal.vampiremod.util;
import net.minecraft.world.entity.player.Player;
public class capabilityUtil {
    public class CapabilityUtil {
        public static boolean isVampire(Player player) {
            return player.getCapability(vampireprovider.VAMPIRE_CAP)
                    .map(vampireCapability::isVampire)
                    .orElse(false);
        }

        public static void setVampire(Player player, boolean value) {
            player.getCapability(vampireprovider.VAMPIRE_CAP)
                    .ifPresent(v -> v.setVampire(value));


        }
    }
}