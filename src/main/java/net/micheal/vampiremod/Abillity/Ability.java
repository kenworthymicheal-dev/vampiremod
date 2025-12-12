package net.micheal.vampiremod.Abillity;

public class Ability {
    public class Ability {

        private final String id;
        private final int requiredLevel;

        public Ability(String id, int requiredLevel) {
            this.id = id;
            this.requiredLevel = requiredLevel;
        }

        public String getId() {
            return id;
        }

        public int getRequiredLevel() {
            return requiredLevel;
        }
    }
}