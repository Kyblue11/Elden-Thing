package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents an action where an actor runs away from another actor.
 * The actor will move in the opposite direction of the target actor.
 * @Author Hsu Chyi See
 */
public class RunAwayAction extends MoveActorAction {
    /**
     * The target actor to avoid
     */
    private final Actor TARGET;

    /**
     * The direction in which the actor will move while avoiding
     */
    private final String DIRECTION;

    /**
     * Constructor for RunAwayAction with a hotKey.
     *
     * @param moveToLocation the location the actor will move to
     * @param direction the direction in which the actor moves
     * @param hotKey the hotkey associated with the action
     * @param target the actor to be followed
     */
    public RunAwayAction(Location moveToLocation, String direction, String hotKey, Actor target) {
        super(moveToLocation, direction, hotKey);
        this.TARGET = target;
        this.DIRECTION = direction;
    }

    /**
     * Constructor for RunAwayAction without a hotKey.
     *
     * @param moveToLocation the location the actor will move to
     * @param direction the direction in which the actor moves
     * @param target the actor to be followed
     */
    public RunAwayAction(Location moveToLocation, String direction, Actor target) {
        super(moveToLocation, direction, null);
        this.TARGET = target;
        this.DIRECTION = direction;
    }

    /**
     * Provides a description of this action in the game menu.
     *
     * @param actor the actor performing the action
     * @return a string representing the menu description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " runs away from " + this.TARGET + " in the direction of " + DIRECTION;
    }
}
