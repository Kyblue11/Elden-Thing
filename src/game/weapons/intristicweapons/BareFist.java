package game.weapons.intristicweapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Class representing a bare fist
 * @author Adrian Kristanto
 * Modified by Aaron Lam Kong Yew
 */
public class BareFist extends IntrinsicWeapon {

    /* Damage dealt by the bare fist */
    private static final int DAMAGE = 25;

    /* Verb describing the action of the bare fist */
    private static final String VERB = "punches";

    /* Chance to hit with the bare fist */
    private static final int HIT_CHANCE = 50;

    /**
     * Constructor.
     */
    public BareFist() {
        super(DAMAGE, VERB, HIT_CHANCE);
    }
}
