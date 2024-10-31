package game.weapons.intristicweapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Class representing a heavy entity's foot, that can be used to attack other entities.
 * @author Aaron Lam Kong Yew
 */
public class Foot  extends IntrinsicWeapon {

    /** Damage dealt by the foot */
    private static final int DAMAGE = 100;

    /** Verb describing the action of the foot */
    private static final String VERB = "stomps";

    /** Chance to hit with the foot */
    private static final int HIT_CHANCE = 5;

    /**
     * Constructor.
     */
    public Foot() {
        super(DAMAGE, VERB, HIT_CHANCE);
    }
}
