package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.attributes.Status;

/**
 * Class representing the Suspicious Trader.
 * Created by:
 * @author Kim Tae Jun
 */

public class SuspiciousTrader extends Actor {

    /**
     * The name of the Trader
     */
    private static final String NAME = "Suspicious Trader";

    /**
     * The display character of the Trader
     */
    private static final char CHAR = 'ඞ';

    /**
     * The health points of the Trader
     * The hp is set to 0 as NPCs should not have a health bar logically
     */
    private static final int HP = 0;

    /**
     * Constructor for Suspicious Trader.
     * Initializes the Trader with its name, display character, and health points.
     */
    public SuspiciousTrader() {
        super(NAME, CHAR, HP);
        this.addCapability(Status.TRADER);
    }

    /**
     * Defines the behaviour of Trader on each turn.
     *
     * @param actions available actions for the Scarab
     * @param lastAction the last action performed by the Scarab
     * @param map the current game map
     * @param display the display to output game messages
     * @return DoNothingAction as Trader does not have any action besides trading
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
