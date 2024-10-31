package game.weapons.powerweapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.weaponitems.DivineWeapon;

/**
 * Class representing the head weapon of the Divine Beast, which inherits the
 * divine power logic from {@link DivineWeapon}.
 *
 * This weapon deals 150 base damage with a hit chance of 30% and uses
 * the verb "bites" to describe the attack action. It applies the
 * divine power transitions (Wind, Frost, and Lightning) during attacks,
 * just like any other {@link DivineWeapon}.
 *
 * This class extends {@link DivineWeapon}, inheriting its divine power
 * switching and special attack logic.
 *
 * @author stanl
 */
public class DivineBeastHead extends DivineWeapon {

    /** The name of the Divine Beast's head weapon. */
    private static final String NAME = "Divine Beast Head";

    /** The character to display for this weapon on the map. */
    private static final char DISPLAY_CHAR = '$';

    /** The base damage dealt by this weapon. */
    private static final int DAMAGE = 150;

    /** The verb used to describe the attack action. */
    private static final String VERB = "bites";

    /** The hit chance percentage for the attack. */
    private static final int HIT_CHANCE = 30;

    /**
     * Constructor for the DivineBeastHead weapon.
     * Initializes the weapon with the name, display character, damage,
     * attack verb, hit chance, and no required strength.
     */
    public DivineBeastHead() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_CHANCE, 0);
    }

    /**
     * Executes an attack on a target, applying both the normal damage and any
     * effects from divine power transitions.
     *
     * @param attacker the actor performing the attack
     * @param target   the actor being attacked
     * @param map      the game map the actors are on
     * @return a string describing the outcome of the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        return super.attack(attacker, target, map);
    }
}
