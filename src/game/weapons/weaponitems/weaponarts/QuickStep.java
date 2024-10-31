package game.weapons.weaponitems.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.LocationUtils;

/**
 * A class that represents the Quickstep weapon art, which implements the WeaponArt interface.
 * The Quickstep weapon art allows the actor to move to a random location on the map.
 * @author Aaron Lam Kong Yew
 */
public class QuickStep implements WeaponArt {
    /**
     * The mana cost of the Quickstep weapon art
     */
    private static final int MANA_COST = 0;

    /**
     * Executes the Quickstep weapon art.
     * This method will move the actor to a random location on the map, as long as the location is valid.
     *
     * @param attacker the actor that is using the weapon art
     * @param target the actor that is being targeted by the weapon art
     * @param map the game map
     * @return a string that represents the result of the weapon art execution
     */
    @Override
    public String execute(Actor attacker, Actor target, GameMap map) {
        if (canExecute(attacker)) {
            if (moveRandomly(attacker, map)) {
                attacker.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.DECREASE, getManaCost());
                return attacker + " uses Quickstep to move to a random location! ";
            } else {
                return "  " + attacker + " can't move anywhere! ";
            }
        }
        return "  " + attacker + " does not have enough mana to use Quickstep! ";
    }

    /**
     * Returns the mana cost of the Quickstep weapon art.
     * The Quickstep weapon art does not require any mana to execute.
     *
     * @return the mana cost of the Quickstep weapon art
     */
    @Override
    public int getManaCost() {
        return MANA_COST;
    }

    /**
     * Checks if the Quickstep weapon art can be executed by the actor.
     * Depending on the actor's mana, the weapon art can only be executed if the actor has enough mana.
     *
     * @param attacker the actor that is using the weapon art
     * @return true if the Quickstep weapon art can be executed, false otherwise
     */
    @Override
    public boolean canExecute(Actor attacker) {
        return attacker.getAttribute(BaseActorAttributes.MANA) >= getManaCost();
    }

    /**
     * Moves the actor to a random location on the map, as long as the location is valid.
     *
     * @param attacker the actor that is using the weapon art
     * @param map the game map
     * @return true if the actor is moved to a random location, false otherwise
     */
    private boolean moveRandomly(Actor attacker, GameMap map) {
        Location newLocation = LocationUtils.getRandomSurroundingLocation(map.locationOf(attacker), attacker);
        if (newLocation != null) {
            map.moveActor(attacker, newLocation);
            return true;
        }
        return false;
    }
}