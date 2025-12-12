package net.micheal.vampiremod;

import net.micheal.vampiremod.capability.IVampire;

public class Vampire implements IVampire {
    @Override
    public boolean isVampire() {
        return false;
    }

    @Override
    public void setVampire(boolean v) {

    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public void setLevel(int lvl) {

    }

    @Override
    public void addLevel(int amt) {

    }

    @Override
    public int getBlood() {
        return 0;
    }

    @Override
    public void setBlood(int b) {

    }

    @Override
    public void addBlood(int amt) {

    }

    @Override
    public boolean hasLevelOneAbilities() {
        return false;
    }

    private boolean vampire = false;
    private int blood = 0;
    private int maxBlood = 20;
    private int level = 1;
    private int abilityPoints = 0;
    private boolean[] unlockedAbilities = new boolean[10];





    @Override
    public int getMaxBlood() { return maxBlood; }
    @Override
    public void setMaxBlood(int amount) { maxBlood = amount; }



    @Override
    public int getAbilityPoints() { return abilityPoints; }
    @Override
    public void addAbilityPoint(int amt) { abilityPoints += amt; }

    @Override
    public boolean hasAbility(int index) { return unlockedAbilities[index]; }
    @Override
    public void unlockAbility(int index) { unlockedAbilities[index] = true; }

    @Override
    public void addAbilityPoints(int abilityPoints) {
    }
}
