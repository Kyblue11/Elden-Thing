package game.weapons.intristicweapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.weapons.weaponitems.DivineWeapon;

/**
 * Class representing the intrinsic weapon of the Divine Beast Dancing Lion, which
 * includes the divine power switching logic from {@link DivineWeapon}.
 *
 * This weapon deals a base damage of 150 and has a hit chance of 30%.
 * In addition to the normal attack logic, it uses the divine power system
 * from {@link DivineWeapon} to switch between divine powers (Wind, Frost, and Lightning)
 * during attacks, applying additional effects.
 *
 * This class extends {@link IntrinsicWeapon} but delegates much of its
 * logic to an instance of {@link DivineWeapon} to incorporate divine power functionality.
 *
 * @author stanl
 */
public class DivineBeastDancingLionBite extends IntrinsicWeapon {

    /** The base damage dealt by this weapon. */
    private static final int DAMAGE = 150;

    /** The verb used to describe the attack action. */
    private static final String VERB = "bites";

    /** The hit chance percentage for the attack. */
    private static final int HIT_CHANCE = 30;

    /** The {@link DivineWeapon} instance used to handle divine power switching and effects. */
    private DivineWeapon divineBite;

    private static final String NAME = "Divine Beast Dancing Lion Bite";

    private static final char DISPLAY_CHAR = 'L';

    private static final int REQUIRED_STRENGTH = 0;

    /**
     * Constructor for DivineBeastDancingLionBite.
     * Initializes the intrinsic weapon with a base damage, verb, and hit chance,
     * and sets up the {@link DivineWeapon} to handle divine power logic.
     */
    public DivineBeastDancingLionBite() {
        super(DAMAGE, VERB, HIT_CHANCE);
        this.divineBite = new DivineWeapon(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_CHANCE, REQUIRED_STRENGTH);
    }

    /**
     * Executes an attack on a target, using the divine power logic from {@link DivineWeapon}.
     * The attack result includes both the normal attack and any additional effects from
     * divine powers.
     *
     * @param attacker the actor performing the attack
     * @param target   the actor being attacked
     * @param map      the game map the actors are on
     * @return a string describing the outcome of the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        String result = divineBite.attack(attacker, target, map);
        return result;
    }
}
