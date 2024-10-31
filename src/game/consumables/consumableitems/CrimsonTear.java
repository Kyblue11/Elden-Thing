package game.consumables.consumableitems;

import edu.monash.fit2099.engine.actors.Actor;
import game.effects.HealingEffect;

/**
 * A class that represents a Crimson Tear consumable item.
 * A Crimson Tear is a consumable item that can be consumed by an actor to increase the actor's health
 * by 30 points for 5 turns, resulting in a total of 150 points.
 * @author Kim Tae Jun
 * Modified by Aaron Lam Kong Yew
 */
public class CrimsonTear extends ConsumableItem {

    /**
     * The name of the consumable item.
     */
    private static final String NAME = "Crimson Tear";

    /**
     * Character representing the consumable item
     */
    private static final char DISPLAY_CHAR = '*';

    /**
     * Indicates if the item is portable
     */
    private static final boolean PORTABLE = true;

    /**
     * The number of charges the item has
      */
    private static final int CHARGES = 1;

    /**
     * Constructor.
     */
    public CrimsonTear() {
        super(NAME, DISPLAY_CHAR, PORTABLE, CHARGES);
    }

    /**
     * Consumes the Crimson Tear item, applying its healing effects to the actor.
     * Restores current health by 30 HP for 5 turns through a healing effect.
     * Removes the item from the actor's inventory if it is present.
     *
     * @param actor the actor consuming the item
     * @return true if the item was successfully consumed
     */
    @Override
    public boolean consume(Actor actor) {
        // Restore current health by 30 HP for 5 turns by healing effect
        actor.addStatusEffect(new HealingEffect());
        // Remove the item from the player's inventory, if it is in there
        if (actor.getItemInventory().contains(this)) {
            actor.removeItemFromInventory(this);
        }
        return true;
    }
}
