package net.micheal.vampiremod.capability;

import net.minecraft.nbt.CompoundTag;

public interface IVampire {
    boolean isVampire();
    void setVampire(boolean v);

    int getLevel();
    void setLevel(int lvl);
    void addLevel(int amt);

    int getBlood();
    void setBlood(int b);
    void addBlood(int amt);

    boolean hasLevelOneAbilities();

    int getMaxBlood();

    void setMaxBlood(int amount);

    int getAbilityPoints();

    void addAbilityPoint(int amt);

    boolean hasAbility(int index);

    void unlockAbility(int index);

    void addAbilityPoints(int abilityPoints);

    void removeAbilityPoint();

    // --------------------------
    // NBT SAVE
    // --------------------------
    CompoundTag saveNBT();

    // --------------------------
    // NBT LOAD
    // --------------------------
    void loadNBT(CompoundTag tag);
}
