package game.weapons.weaponitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Abstract class for a weapon is similar to Golem's intrinsic weapon
 * Allows actors that pick up this weapon to use Golem's Foot stomp.

 * @author Kim Tae Jun
 */

public class StompWeapon extends WeaponItem{

    /**
     * Constructor for the StompWeapon.
     *
     * @param name            name of the item
     * @param displayChar     character to display the item
     * @param damage          base damage of the weapon
     * @param verb            action verb for the weapon (e.g., "hits")
     * @param hitRate         hit rate percentage (chance to hit)
     * @param requiredStrength required strength to wield the weapon
     */
    public StompWeapon(String name, char displayChar, int damage, String verb, int hitRate, int requiredStrength) {
        super(name, displayChar, damage, verb, hitRate, requiredStrength);
    }

    /**
     * Executes an attack on a target
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
