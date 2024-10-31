package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.attributes.Status;

import java.util.List;
import java.util.Random;

/**
 * A class that contains utility methods for locations calculations and logic.
 * @author Aaron Lam Kong Yew
 */
public class LocationUtils {

    /**
     * Returns true if the two locations are adjacent to each other.
     * Two locations are adjacent if the difference in x and y coordinates is less than or equal to 1.
     * @param a the first location
     * @param b the second location
     * @return true if the two locations are adjacent to each other, false otherwise
     */
    public static boolean isAdjacent(Location a, Location b) {
        return Math.abs(a.x() - b.x()) <= 1 && Math.abs(a.y() - b.y()) <= 1;
    }

    /**
     * Returns a list of locations that are adjacent to the given location.
     * Uses FIT2102's stream API concepts.
     * @param location the location to get the adjacent locations from
     * @return a list of locations that are adjacent to the given location
     */
    public static List<Location> getSurroundingLocations(Location location) {
        return location.getExits().stream()
                .map(exit -> exit.getDestination())
                .toList();
    }

    /**
     * Returns a random location that is adjacent to the given location and can be entered by the given actor.
     * @param location the location to get the random surrounding location from
     * @param actor the actor that wants to enter the location
     * @return a random location that is adjacent to the given location and can be entered by the given actor
     */
    public static Location getRandomSurroundingLocation(Location location, Actor actor) {
        List<Location> surroundingLocations = getSurroundingLocations(location);
        List<Location> accessibleLocations = surroundingLocations.stream()
                .filter(loc -> loc.canActorEnter(actor))
                .toList();

        // An edge case where the actor is surrounded by non-traversable grounds or other actors, and cannot move.
        if (accessibleLocations.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return accessibleLocations.get(random.nextInt(accessibleLocations.size()));
    }

    /**
     * Finds a target actor in the surrounding locations that is not friendly to the enemy.
     * Primarily used by behaviours that require a target actor to perform an action.
     * @param actor the actor performing the behaviour
     * @param map the game map on which the actor is located
     * @return the target actor if found, or null otherwise
     */
    public static Actor findTargetActor(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && !destination.getActor().hasCapability(Status.FRIENDLY_TO_ENEMY)) {
                return destination.getActor();
            }
        }
        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    public static int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
