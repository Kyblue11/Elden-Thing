package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Behaviour;
import game.actions.FollowAction;
import game.positions.LocationUtils;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 * @see edu.monash.fit2099.demo.mars.behaviours.FollowBehaviour
 * @see edu.monash.fit2099.demo.mars.Application
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Copypasted and Modified by:
 * Aaron Lam Kong Yew
 * Modified by: Stanley Mah
 *
 */
public class FollowBehaviour implements Behaviour {

    /**
     * The target Actor to follow
     */
    private Actor target;

    /**
     * Constructor.
     *
     * @param subject the Actor to follow
     */
    public FollowBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Get the MoveActorAction to move the actor one step closer to the target Actor.
     *
     * @param actor the Actor that might be following
     * @param map the GameMap containing the Actor
     * @return a MoveActorAction that moves the Actor one step closer to the target Actor
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = LocationUtils.distance(here, there);
        Action closestFollow = null;

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = LocationUtils.distance(here, there);
                if (newDistance < currentDistance) {
                    currentDistance = newDistance;
                    closestFollow = new FollowAction(destination, exit.getName(), target);
                }
            }
        }

        return closestFollow;
    }
}
