package game.weapons.weaponitems;

import game.weapons.weaponitems.weaponarts.WeaponArt;

import java.util.List;

/**
 * Class representing a Short Sword weapon.
 * @author Aaron Lam Kong Yew
 */
public class ShortSword extends WeaponItem {

    /* Name of the weapon */
    private static final String NAME = "Short Sword";

    /* Character representing the weapon */
    private static final char DISPLAY_CHAR = '!';

    /* Damage dealt by the weapon */
    private static final int DAMAGE = 100;

    /* Verb describing the action of the weapon */
    private static final String VERB = "slashes";

    /* Chance to hit with the weapon */
    private static final int HIT_RATE = 75;

    /* Strength of the weapon */
    private static final int REQUIRED_STRENGTH = 10;

    /**
     * Short Sword constructor.
     */
    public ShortSword() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, REQUIRED_STRENGTH);
    }

    /**
     * Short Sword constructor with weapon arts.
     * @param weaponArts List of weapon arts
     */
    public ShortSword(List<WeaponArt> weaponArts) {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, REQUIRED_STRENGTH, weaponArts);
    }
}