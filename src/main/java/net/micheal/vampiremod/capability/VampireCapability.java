package net.micheal.vampiremod.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import java.util.ArrayList;
import java.util.List;


public static class Vampire implements IVampire {

    private boolean isVampire = false;
    private int blood = 0;
    private int maxBlood = 20;
    private int level = 1;
    private int abilityPoints = 0;

    @Override
    public boolean isVampire() {
        return isVampire;
    }

    @Override
    public void setVampire(boolean state) {
        this.isVampire = state;
    }

    @Override
    public int getBlood() {
        return blood;
    }

    @Override
    public void setBlood(int value) {
        this.blood = Math.max(0, Math.min(value, maxBlood));
    }

    @Override
    public void addBlood(int amount) {
        setBlood(blood + amount);
    }

    @Override
    public int getMaxBlood() {
        return maxBlood;
    }

    @Override
    public void setMaxBlood(int value) {
        this.maxBlood = value;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int lvl) {
        this.level = lvl;
    }

    @Override
    public int getAbilityPoints() {
        return abilityPoints;
    }

    @Override
    public void addAbilityPoints(int amt) {
        abilityPoints += amt;
    }

    @Override
    public void removeAbilityPoint() {
        abilityPoints = Math.max(0, abilityPoints - 1);
    }

    // --------------------------
    // NBT SAVE
    // --------------------------
    @Override
    public CompoundTag saveNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("IsVampire", isVampire);
        tag.putInt("Blood", blood);
        tag.putInt("MaxBlood", maxBlood);
        tag.putInt("Level", level);
        tag.putInt("AbilityPoints", abilityPoints);
        return tag;
    }

    // --------------------------
    // NBT LOAD
    // --------------------------
    @Override
    public void loadNBT(CompoundTag tag) {
        isVampire = tag.getBoolean("IsVampire");
        blood = tag.getInt("Blood");
        maxBlood = tag.getInt("MaxBlood");
        level = tag.getInt("Level");
        abilityPoints = tag.getInt("AbilityPoints");
    }
    private final List<String> unlockedAbilities = new ArrayList<>();

    public boolean hasAbility(String id) {
        return unlockedAbilities.contains(id);
    }

    public void unlockAbility(String id) {
        if (!unlockedAbilities.contains(id)) {
            unlockedAbilities.add(id);
        }
    }



}





