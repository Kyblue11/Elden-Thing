package game.tradables.tradableItems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.weapons.powerweapons.DivineBeastHead;

/**
 * A class that represents a RemembranceOfDancingLion tradable item.
 * A Crimson Tear is a tradable item that can be traded by an actor to access divine powers possessed by DancingLion
 * @author Kim Tae Jun
 */

public class RemembranceOfDancingLion extends TradableItem{

    /**
     * The name of the remembrance item.
     */
    private static final String NAME = "Remembrance Of Dancing Lion";

    /**
     * Character representing the remembrance item.
     */
    private static final char DISPLAY_CHAR = '*';

    /**
     * Indicates if the item is portable
     */
    private static final boolean PORTABLE = true;

    /**
     * The amount of health and mana points the actor will gain when they trade the remembrance item.
     */
    private static final int HEALTH_INCREASE = 50;

    /**
     * The amount of mana points the actor will gain when they trade the remembrance item.
     */
    private static final int MANA_INCREASE = 100;

    /**
     * Constructor.
     */
    public RemembranceOfDancingLion() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
    }

    /**
     * Trades the remembrance item, giving access of Divine powers to actor.
     * Increase the max HP and MANA by 50 and 100 respectively.
     * Removes the item from the actor's inventory if it is present.
     *
     * @param actor the actor trading the item
     * @return true if the item was successfully traded
     */
    @Override
    public boolean trade(Actor actor) {
        // Increase the maximum health and mana by 50 and 100 respectively
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HEALTH_INCREASE);
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MANA_INCREASE);
        actor.addItemToInventory(new DivineBeastHead());

        // Remove the item from the player's inventory, if it is in there
        if (actor.getItemInventory().contains(this)) {
            actor.removeItemFromInventory(this);
        }
        return true;
    }
}
