package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents an action where an Actor follows a target in a specific direction.
 * @author stanl
 */
public class FollowAction extends MoveActorAction {

    /**
     * The target actor to be followed.
     */
    private final Actor TARGET;

    /**
     * The direction in which the actor will move while following the target.
     */
    private final String DIRECTION;

    /**
     * Constructor for FollowAction with a hotKey.
     *
     * @param moveToLocation the location the actor will move to
     * @param direction the direction in which the actor moves
     * @param hotKey the hotkey associated with the action
     * @param target the actor to be followed
     */
    public FollowAction(Location moveToLocation, String direction, String hotKey, Actor target) {
        super(moveToLocation, direction, hotKey);
        this.TARGET = target;
        this.DIRECTION = direction;
    }

    /**
     * Constructor for FollowAction without a hotKey.
     *
     * @param moveToLocation the location the actor will move to
     * @param direction the direction in which the actor moves
     * @param target the actor to be followed
     */
    public FollowAction(Location moveToLocation, String direction, Actor target) {
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
        return actor + " follows " + this.TARGET + " in the direction of " + DIRECTION;
    }

}
