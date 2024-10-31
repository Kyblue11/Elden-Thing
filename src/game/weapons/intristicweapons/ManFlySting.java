package game.weapons.intristicweapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A class that represents the Sting intrinsic weapon.
 * This weapon deals 20 damage and has a chance to inflict an additional effect.
 *
 * @author stanl
 * Modified by: Aaron Lam Kong Yew
 */
public class ManFlySting extends IntrinsicWeapon {

    /** Damage dealt by the Sting */
    private static final int DAMAGE = 20;

    /** Verb describing the action of the Sting */
    private static final String VERB = "stings";

    /** Chance to hit with the Sting */
    private static final int HIT_CHANCE = 25;

    /**
     * Constructor for the Sting weapon.
     */
    public ManFlySting() {
        super(DAMAGE, VERB, HIT_CHANCE);
    }
}
