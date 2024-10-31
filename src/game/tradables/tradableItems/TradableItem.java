package game.tradables.tradableItems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TradeAction;
import game.actors.attributes.Status;
import game.tradables.Tradable;

/**
 * An abstract class that represents a tradable item.
 * A tradable item is a concrete item that can be traded by an actor.
 * @author Kim Tae Jun
 */
public abstract class TradableItem extends Item implements Tradable {

    /**
     * Constructor.
     * @param name the name of the tradable item
     * @param displayChar the character that will represent the tradable item
     * @param portable whether the tradable item is portable
     */
    public TradableItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Only has the TradeAction as an allowable action
     * @return TradeAction
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor);
        // Only allow tradeAction if the other actor has a trader status, to prevent trade action with any other actors
        if (otherActor.hasCapability(Status.TRADER)){
            actions.add(new TradeAction(this));
        }

        return actions;
    }

    /**
     * Implements the trade method from the Tradable interface.
     * @param actor the actor trading the tradable item
     * @return true if the item is traded, false otherwise
     */
    @Override
    public abstract boolean trade(Actor actor);
}
