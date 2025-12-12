package net.micheal.vampiremod.Abillity;

import java.util.ArrayList;
import java.util.List;

public class VampireAbilites {

    public static final Ability STRENGTH = new Ability("strength", 1);
    public static final Ability SPEED = new Ability("speed", 1);
    public static final Ability NIGHT_VISION = new Ability("night_vision", 2);
    public static final Ability BLOOD_DRAIN_BOOST = new Ability("blood_drain_boost", 3);
    public static final Ability BAT_LEAP = new Ability("bat_leap", 5);

    public static List<Ability> ALL_ABILITIES = new ArrayList<>();

    public static void register() {
        ALL_ABILITIES.add(STRENGTH);
        ALL_ABILITIES.add(SPEED);
        ALL_ABILITIES.add(NIGHT_VISION);
        ALL_ABILITIES.add(BLOOD_DRAIN_BOOST);
        ALL_ABILITIES.add(BAT_LEAP);
    }
}
}
