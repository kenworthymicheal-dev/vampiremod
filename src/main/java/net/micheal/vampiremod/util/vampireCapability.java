package net.micheal.vampiremod.util;

public class vampireCapability {

    private boolean vampire = false;

    public boolean isVampire() {
        return vampire;
        public void setVampire ( boolean v){
            vampire = v;
        }

        public int getBlood () {
            return blood;
        }
        public void addBlood ( int amt){
            blood = Math.min(maxBlood, blood + amt);
        }
        public void drainBlood ( int amt){
            blood = Math.max(0, blood - amt);
        }

        public int getLevel () {
            return level;
        }
        public int getXP () {
            return xp;
        }

        public void addXP ( int amount){
            xp += amount;
            if (xp >= level * 20) {
                xp = 0;
                level++;
            }
        }
    }


}
