package game.consumables.consumableitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actors.attributes.TarnishedActorAttributes;

/**
 * A class that represents a Shadowtree Fragment consumable item.
 * A Shadowtree Fragment is a consumable item that can be consumed by an actor to increase the actor's health by 50 points,
 * mana by 25 points, and strength by 5 points.
 * @author Aaron Lam Kong Yew
 */
public class ShadowtreeFragment extends ConsumableItem {

    /**
     * The name of the consumable item.
     */
    private static final String NAME = "Shadowtree Fragment";

    /**
     * Character representing the consumable item
     */
    private static final char DISPLAY_CHAR = 'e';

    /**
     * Indicates if the item is portable
     */
    private static final boolean PORTABLE = true;

    /**
     * Number of charges the item has
     */
    private static final int CHARGES = 1;

    /**
     * The amount of health points the actor will be healed by.
     */
    private static final int HEAL_AMOUNT = 50;

    /**
     * The amount of mana points the actor will be healed by.
     */
    private static final int MANA_AMOUNT = 25;

    /**
     * The amount of strength points the actor will be healed by.
     */
    private static final int STRENGTH_AMOUNT = 5;

    /**
     * Constructor.
     */
    public ShadowtreeFragment() {
        super(NAME, DISPLAY_CHAR, PORTABLE, CHARGES);
    }

    /**
     * Consumes the Shadowtree Fragment.
     * The actor will have their maximum health attribute increased by 50 points,
     * maximum mana attribute increased by 25 points, and maximum strength attribute increased by 5 points.
     * Besides, the actor's current health will be restored to maximum, and current mana will be restored to maximum.
     * Unlike other consumable items, the Shadowtree Fragment can only be consumed once,
     * and will disappear from the player's inventory after consumption.
     * @param actor the actor consuming the Shadowtree Fragment
     * @return true if the Shadowtree Fragment is consumed, false otherwise
     */
    @Override
    public boolean consume(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HEAL_AMOUNT);
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MANA_AMOUNT);
        actor.modifyAttributeMaximum(TarnishedActorAttributes.STRENGTH, ActorAttributeOperations.INCREASE, STRENGTH_AMOUNT);

        // Restore current Health and Mana to maximum
        actor.heal(actor.getAttributeMaximum(BaseActorAttributes.HEALTH) - actor.getAttribute(BaseActorAttributes.HEALTH));
        actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.UPDATE, actor.getAttributeMaximum(BaseActorAttributes.MANA));

        // Remove the item from the player's inventory, if it is in there
        if (actor.getItemInventory().contains(this)){
            actor.removeItemFromInventory(this);
        }
        return true;
    }
}