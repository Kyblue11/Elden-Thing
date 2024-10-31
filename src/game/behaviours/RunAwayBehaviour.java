package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RunAwayAction;
import game.positions.LocationUtils;

/**
 * A class that represents a behaviour where an actor runs away from another actor.
 * The actor will move in the opposite direction of the target actor.
 * @Author Hsu Chyi See
 */
public class RunAwayBehaviour implements Behaviour {

    /**
     * The target Actor to follow
     */
    private Actor target;

    /**
     * Constructor.
     *
     * @param subject the Actor to follow
     */
    public RunAwayBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Get the MoveActorAction to move the actor one step away from the target Actor.
     * Leverages from FollowBehaviour, since running away is the negation of following
     *
     * @param actor the Actor that might be following
     * @param map the GameMap containing the Actor
     * @return a MoveActorAction that moves the Actor one step away from the target Actor
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor)) {
            return null;
        }
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = LocationUtils.distance(here, there);
        Action furthestRunAway = null;

        // Loop through all available exits to find the furthest one from the target
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = LocationUtils.distance(destination, there);
                // Now we want to find the exit that increases the distance
                if (newDistance > currentDistance) {
                    currentDistance = newDistance;
                    furthestRunAway = new RunAwayAction(destination, exit.getName(), target);
                }
            }
        }

        return furthestRunAway;
    }
}
