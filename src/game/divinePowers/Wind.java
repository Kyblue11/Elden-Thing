package game.divinePowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.LocationUtils;

import java.util.List;
import java.util.Random;

/**
 * A class representing the Wind divine power.
 * This divine power blows a target actor to a random adjacent location if possible.
 *
 * @author stanl
 */
public class Wind extends DivinePower {

    /**
     * Constructs a Wind divine power with the name "Wind".
     */
    public Wind() {
        super("Wind");
    }

    /**
     * Uses the Wind divine power to blow the target actor to a random adjacent location.
     * If there are no accessible locations, the action fails.
     *
     * @param attacker the actor using the divine power
     * @param target   the actor being blown by the divine power
     * @param map      the current game map
     * @return a message describing the outcome of using the divine power
     */
    @Override
    public String useDivinePower(Actor attacker, Actor target, GameMap map) {
        Location targetLocation = map.locationOf(target);
        List<Location> surroundingLocations = LocationUtils.getSurroundingLocations(map.locationOf(attacker));

        List<Location> accessibleLocations = surroundingLocations.stream()
                .filter(loc -> loc.canActorEnter(target) && !loc.equals(targetLocation))
                .toList();

        if (accessibleLocations.isEmpty()) {
            return attacker + " used " + getDivinePowerName() + " but there is no place to blow " + target + " to.\n";
        }

        Random random = new Random();
        Location newLocation = accessibleLocations.get(random.nextInt(accessibleLocations.size()));

        map.moveActor(target, newLocation);
        return attacker + " used " + getDivinePowerName() + " to blow " + target + " to " + newLocation + ".\n";
    }
}
