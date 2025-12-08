package net.micheal.vampiremod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class vampireData {
    private static final String TAG_ROOT = "vampirism";
    private static final String TAG_STATUS = "status"; // "none" or "vampire"
    private static final String TAG_STAGE = "stage";
    private static final String TAG_BLOOD_CURRENT = "blood_current";
    private static final String TAG_BLOOD_MAX = "blood_max";

    // Mark the player as vampire (persisted in player's persistent NBT)
    public static void setVampire(ServerPlayer player, boolean value) {
        CompoundTag persist = player.getPersistentData();
        CompoundTag vamp = persist.contains(TAG_ROOT) ? persist.getCompound(TAG_ROOT) : new CompoundTag();
        vamp.putString(TAG_STATUS, value ? "vampire" : "none");
        vamp.putInt(TAG_STAGE, value ? 1 : 0);
        persist.put(TAG_ROOT, vamp);
        player.sendChatMessage(Component.literal("Vampirism state set: " + (value ? "vampire" : "none")), player.getUUID());
    }

    public static boolean isVampire(Player player) {
        CompoundTag persist = player.getPersistentData();
        if (!persist.contains(TAG_ROOT)) return false;
        CompoundTag vamp = persist.getCompound(TAG_ROOT);
        return vamp.contains(TAG_STATUS) && "vampire".equals(vamp.getString(TAG_STATUS));
    }

    public static void initializeVampireStats(ServerPlayer player) {
        // Initialize blood pool and stage values.
        CompoundTag persist = player.getPersistentData();
        CompoundTag vamp = persist.contains(TAG_ROOT) ? persist.getCompound(TAG_ROOT) : new CompoundTag();

        vamp.putInt(TAG_STAGE, 1);
        vamp.putDouble(TAG_BLOOD_MAX, 50.0);
        vamp.putDouble(TAG_BLOOD_CURRENT, 20.0);

        persist.put(TAG_ROOT, vamp);
        // Optionally update player attributes (hp, etc.) here via AttributeModifiers
    }

    public static double getBloodCurrent(Player player) {
        CompoundTag persist = player.getPersistentData();
        if (!persist.contains(TAG_ROOT)) return 0.0;
        CompoundTag vamp = persist.getCompound(TAG_ROOT);
        return vamp.getDouble(TAG_BLOOD_CURRENT);
    }

    public static void setBloodCurrent(Player player, double value) {
        CompoundTag persist = player.getPersistentData();
        CompoundTag vamp = persist.contains(TAG_ROOT) ? persist.getCompound(TAG_ROOT) : new CompoundTag();
        vamp.putDouble(TAG_BLOOD_CURRENT, value);
        persist.put(TAG_ROOT, vamp);
    }

    public static double getBloodMax(Player player) {
        CompoundTag persist = player.getPersistentData();
        if (!persist.contains(TAG_ROOT)) return 0.0;
        CompoundTag vamp = persist.getCompound(TAG_ROOT);
        return vamp.getDouble(TAG_BLOOD_MAX);
    }

    public static void setBloodMax(Player player, double value) {
        CompoundTag persist = player.getPersistentData();
        CompoundTag vamp = persist.contains(TAG_ROOT) ? persist.getCompound(TAG_ROOT) : new CompoundTag();
        vamp.putDouble(TAG_BLOOD_MAX, value);
        persist.put(TAG_ROOT, vamp);



}

    private static class TextComponent implements Component {

        @Override
        public Style getStyle() {
            return null;
        }

        @Override
        public ComponentContents getContents() {
            return null;
        }

        @Override
        public List<Component> getSiblings() {
            return List.of();
        }

        @Override
        public FormattedCharSequence getVisualOrderText() {
            return null;
        }
    }
}
