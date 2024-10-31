package game.tradables.tradableItems;

import edu.monash.fit2099.engine.actors.Actor;
import game.effects.HealingEffect;
import game.weapons.powerweapons.FurnaceEngine;

/**
 * A class that represents a RemembranceOfDancingLion tradable item.
 * A Crimson Tear is a tradable item that can be traded by an actor to access Golem's foot and stomp action
 * @author Kim Tae Jun
 */

public class RemembranceOfFurnaceGolem extends TradableItem {

    /**
     * The name of the remembrance item.
     */
    private static final String NAME = "Remembrance Of Furnace Golem";

    /**
     * Character representing the remembrance item.
     */
    private static final char DISPLAY_CHAR = '*';

    /**
     * Indicates if the item is portable
     */
    private static final boolean PORTABLE = true;

    /**
     * Constructor.
     */
    public RemembranceOfFurnaceGolem() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
    }

    /**
     * Trades the remembrance item, giving access of Golem's foot and StompAction to actor.
     * Apply the healing effect.
     * Removes the item from the actor's inventory if it is present.
     *
     * @param actor the actor trading the item
     * @return true if the item was successfully traded
     */
    @Override
    public boolean trade(Actor actor) {
        // Restore current health by 30 HP for 5 turns by healing effect
        actor.addStatusEffect(new HealingEffect());
        actor.addItemToInventory(new FurnaceEngine());

        // Remove the item from the player's inventory, if it is in there
        if (actor.getItemInventory().contains(this)) {
            actor.removeItemFromInventory(this);
        }
        return true;
    }
}
