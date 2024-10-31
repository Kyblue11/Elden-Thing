package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.StompAction;
import game.positions.LocationUtils;

/**
 * Behaviour that allows an actor to perform a stomp attack on a target.
 * This behaviour checks the surrounding locations for potential targets
 * that are not friendly to the enemy and initiates a StompAction.
 *
 * @author stanl
 */
public class StompBehaviour implements Behaviour {

    /**
     * The target actor that our actor is attacking.
     */
    private final Actor target;

    /**
     * Constructor for the StompBehaviour.
     * Initializes the target actor for this behaviour.
     *
     * @param subject the target actor that our actor is attacking
     */
    public StompBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Determines the action the actor will take based on the presence of other actors in the vicinity.
     * If an actor is found and is not hostile to the player, a StompAction is returned.
     *
     * @param actor the actor performing the behaviour
     * @param map the game map on which the actor is located
     * @return an Action representing the stomp attack if a suitable target is found, or null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Actor target = LocationUtils.findTargetActor(actor, map);
        if (target != null) {
            return new StompAction(target,  map.locationOf(target).toString(), null);
        }
        return null;
    }
}
