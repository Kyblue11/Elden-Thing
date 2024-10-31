package game.weapons.weaponitems;

import game.weapons.weaponitems.weaponarts.WeaponArt;

import java.util.List;

/**
 * Class representing a Great Knife weapon.
 * @author Aaron Lam Kong Yew
 */
public class GreatKnife extends WeaponItem {

    /* Name of the weapon */
    private static final String NAME = "Great Knife";

    /* Character representing the weapon */
    private static final char DISPLAY_CHAR = '†';

    /* Damage dealt by the weapon */
    private static final int DAMAGE = 75;

    /* Verb describing the action of the weapon */
    private static final String VERB = "stabs";

    /* Chance to hit with the weapon */
    private static final int HIT_RATE = 60;

    /* Strength of the weapon */
    private static final int REQUIRED_STRENGTH = 5;

    /**
     * Great Knife constructor.
     */
    public GreatKnife() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, REQUIRED_STRENGTH);
    }

    /**
     * Great Knife constructor with weapon arts.
     * @param weaponArts List of weapon arts
     */
    public GreatKnife(List<WeaponArt> weaponArts) {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, REQUIRED_STRENGTH, weaponArts);
    }
}