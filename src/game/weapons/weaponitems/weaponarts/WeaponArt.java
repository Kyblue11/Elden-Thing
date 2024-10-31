package game.weapons.weaponitems.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface that represents the weapon arts that can be used by an actor.
 * The weapon arts are special abilities that can be used by the actor e.g., to deal damage or heal themselves.
 * @author Aaron Lam Kong Yew
 */
public interface WeaponArt {

    /**
     * Executes the weapon art.
     *
     * @param attacker the actor that is using the weapon art
     * @param target the actor that is being targeted by the weapon art
     * @param map the game map
     * @return a string that represents the result of the weapon art execution
     */
    String execute(Actor attacker, Actor target, GameMap map);

    /**
     * Returns the mana cost of the weapon art.
     *
     * @return the mana cost of the weapon art
     */
    int getManaCost();

    /**
     * Checks if the weapon art can be executed by the actor.
     *
     * @param attacker the actor that is using the weapon art
     * @return true if the weapon art can be executed, false otherwise
     */
    boolean canExecute(Actor attacker);
}