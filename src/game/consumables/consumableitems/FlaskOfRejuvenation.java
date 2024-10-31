package game.consumables.consumableitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.effects.TimeEffect;

/**
 * A class that represents a Flask of Rejuvenation consumable item.
 * A Flask of Rejuvenation is a consumable item that can be consumed by an actor to increase the actor's mana by 100 points.
 * @author Aaron Lam Kong Yew
 */
public class FlaskOfRejuvenation extends ConsumableItem {

    /**
     * The name of the consumable item.
     */
    private static final String NAME = "Flask of Rejuvenation";

    /**
     * Character representing the consumable item
     */
    private static final char DISPLAY_CHAR = 'o';

    /**
     * Indicates if the item is portable
     */
    private static final boolean PORTABLE = true;

    /**
     * Indicates if the item is portable
     */
    private static final int CHARGES = 3;

    /**
     * The amount of charges deducted when the Flask of Rejuvenation is consumed during night time.
     */
    private static final int NIGHTIME_CHARGE_DEDUCTION = 2;

    /**
     * The amount of mana points the actor will be healed by.
     */
    private static final int HEAL_AMOUNT = 100;

    /**
     * The minimum number of charges the Flask of Rejuvenation can have.
     */
    private static final int MIN_CHARGES = 0;

    /**
     * Constructor.
     */
    public FlaskOfRejuvenation() {
        super(NAME, DISPLAY_CHAR, PORTABLE, CHARGES);
    }

    /**
     * Consumes the Flask of Rejuvenation.
     * The actor will have their mana attribute increased by 100 points.
     * Will run out of charges after 3 uses.
     * @param actor the actor consuming the Flask of Rejuvenation
     * @return true if the Flask of Rejuvenation is consumed, false otherwise
     */
    @Override
    public boolean consume(Actor actor) {
        if (charges > MIN_CHARGES) {
            actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, HEAL_AMOUNT);
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