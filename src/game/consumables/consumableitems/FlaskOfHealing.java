package game.consumables.consumableitems;

import edu.monash.fit2099.engine.actors.Actor;
import game.effects.TimeEffect;

/**
 * A class that represents a Flask of Healing consumable item.
 * A Flask of Healing is a consumable item that can be consumed by an actor to heal the actor by 150 hit points.
 * @author Aaron Lam Kong Yew
 */
public class FlaskOfHealing extends ConsumableItem {

    /**
     * Name of the consumable item
     */
    private static final String NAME = "Flask of Healing";

    /**
     * Character representing the consumable item
      */
    private static final char DISPLAY_CHAR = 'u';

    /**
     * Indicates if the item is portable
      */
    private static final boolean PORTABLE = true;

    /**
     * Indicates if the item is portable
      */
    private static final int CHARGES = 5;

    /**
     * The amount of charges deducted when the Flask of Rejuvenation is consumed during night time.
     */
    private static final int NIGHTIME_CHARGE_DEDUCTION = 2;

    /**
     * The amount of health points the actor will be healed by.
     */
    private static final int HEAL_AMOUNT = 150;

    /**
     * The minimum number of charges the Flask of Healing can have.
     */
    private static final int MIN_CHARGES = 0;

    /**
     * Constructor.
     */
    public FlaskOfHealing() {
        super(NAME, DISPLAY_CHAR, PORTABLE, CHARGES);
    }

    /**
     * Consumes the Flask of Healing.
     * The actor will be healed by 150 hit points.
     * Will run out of charges after 5 uses.
     * @param actor the actor consuming the Flask of Healing
     * @return true if the Flask of Healing is consumed, false otherwise
     */
    @Override
    public boolean consume(Actor actor) {
        if (charges > MIN_CHARGES) {
            actor.heal(HEAL_AMOUNT);
            if (TimeEffect.isDay()){
                charges--;
            } else {
                charges = charges - NIGHTIME_CHARGE_DEDUCTION;
            }
            return true;
        } else {
            return false;
        }
    }

}