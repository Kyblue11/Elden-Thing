package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.tradables.tradableItems.TradableItem;

/**
 * Class representing an action to trade a tradable item
 * Note that the actor must have a tradable item in their inventory
 * @author Kim Tae Jun
 */
public class TradeAction extends Action {

    /**
     * The Tradable item to be traded
     */
    private TradableItem tradableItem;

    /**
     * Constructor.
     *
     * @param tradableItem the Tradable object
     */
    public TradeAction(TradableItem tradableItem){
        this.tradableItem = tradableItem;
    }

    /**
     * Execute the trade action
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.tradableItem.trade(actor);
        return actor + " traded " + this.tradableItem;
    }

    /**
     * Returns a description of the action.
     *
     * @param actor the actor performing the action
     * @return a String representing the description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades " + this.tradableItem;
    }
}
