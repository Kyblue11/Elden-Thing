package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.StingAction;
import game.positions.LocationUtils;

/**
 * Behaviour that allows an actor to perform a sting attack on a target.
 * This behaviour checks the surrounding locations for potential targets
 * that are not friendly to the enemy and initiates a StingAction.
 * @author stanl
 */
public class StingBehaviour implements Behaviour {

    /**
     * The target actor for the sting attack.
     */
    private final Actor target;

    /**
     * Constructor for the StingBehaviour.
     * Initializes the target actor for this behaviour.
     *
     * @param subject the actor to be targeted for the sting action
     */
    public StingBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Determines the action to perform based on the current state of the game.
     * Checks adjacent locations for actors to sting.
     *
     * @param actor the actor performing the behaviour
     * @param map the current game map
     * @return an Action representing the sting attack or null if no action is available
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Actor target = LocationUtils.findTargetActor(actor, map);
        if (target != null) {
            return new StingAction(target,  map.locationOf(target).toString());
        }
        return null;
    }
}
