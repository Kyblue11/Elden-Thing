package game.weapons.weaponitems.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A class that represents the Life Steal weapon art, which implements the WeaponArt interface.
 * The Life Steal weapon art allows the actor to heal themselves by a certain amount of health.
 * @author Aaron Lam Kong Yew
 */
public class LifeSteal implements WeaponArt {

    /**
     * The mana cost of the Life Steal weapon art
     */
    private static final int MANA_COST = 10;

    /**
     * The amount of health that the actor will heal when using the Life Steal weapon art
     */
    private static final int HEAL_AMOUNT = 20;

    /**
     * Executes the Life Steal weapon art.
     * This method will heal the actor by the amount of health specified by the healAmount attribute.
     *
     * @param attacker the actor that is using the weapon art
     * @param target   the actor that is being targeted by the weapon art
     * @param map      the game map
     * @return a string that represents the result of the weapon art execution
     */
    @Override
    public String execute(Actor attacker, Actor target, GameMap map) {
        if (canExecute(attacker)) {
            attacker.heal(getHealAmount());
            attacker.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.DECREASE, getManaCost());
            return attacker + " uses Life Steal to heal " + getHealAmount() + " health! ";
        }
        return "  " + attacker + " does not have enough mana to use Life Steal! ";
    }

    /**
     * Returns the mana cost of the Life Steal weapon art.
     *
     * @return the mana cost of the Life Steal weapon art
     */
    @Override
    public int getManaCost() {
        return MANA_COST;
    }

    /**
     * Returns the amount of health that the actor will heal when using the Life Steal weapon art.
     *
     * @return the amount of health that the actor will heal when using the Life Steal weapon art
     */
    public int getHealAmount() {
        return HEAL_AMOUNT;
    }

    /**
     * Checks if the Life Steal weapon art can be executed by the actor.
     * Depending on the actor's mana, the weapon art can only be executed if the actor has enough mana.
     *
     * @param attacker the actor that is using the weapon art
     * @return true if the Life Steal weapon art can be executed, false otherwise
     */
    @Override
    public boolean canExecute(Actor attacker) {
        return attacker.getAttribute(BaseActorAttributes.MANA) >= getManaCost();
    }

}