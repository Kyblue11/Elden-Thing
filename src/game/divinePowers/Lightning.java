package game.divinePowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityPassiveAbility;
import game.positions.LocationUtils;

import java.util.List;

/**
 * A class representing the Lightning divine power.
 * This divine power deals damage to surrounding actors, doubling the damage if they are standing on water.
 *
 * @author stanl
 */
public class Lightning extends DivinePower {
    /**
     * The amount of damage the Lightning divine power deals.
     */
    private static final int DAMAGE = 50;

    /**
     * The multiplier for the damage dealt to actors standing on water.
     */
    private static final int LIGHTNING = 2;


    /**
     * Constructs a Lightning divine power with the name "Lightning".
     */
    public Lightning() {
        super("Lightning");
    }

    /**
     * Uses the Lightning divine power to deal damage to surrounding actors.
     * The damage is doubled for actors standing on a puddle of water.
     *
     * @param attacker the actor using the divine power
     * @param target   the actor being targeted by the divine power (not used in this method)
     * @param map      the current game map
     * @return a message describing the outcome of using the divine power
     */
    @Override
    public String useDivinePower(Actor attacker, Actor target, GameMap map) {
        String result = attacker + " used " + getDivinePowerName() + " to shock:";

        List<Location> surroundingLocations = LocationUtils.getSurroundingLocations(map.locationOf(attacker));
        for (Location location : surroundingLocations) {
            int actorDamage = DAMAGE;
            Actor actorInLocation = map.getActorAt(location);

            if (actorInLocation != null) {
                if (location.getGround().hasCapability(EntityPassiveAbility.PUDDLE_OF_WATER)) {
                    actorDamage *= LIGHTNING;
                    result += "\n\t" + actorInLocation + " dealing " + actorDamage + " extra lightning damage since standing on body of water.\n";
                } else {
                    result += "\n\t" + actorInLocation + " dealing " + actorDamage + " normal lightning damage\n";
                }
                actorInLocation.hurt(actorDamage);
            }
        }

        return result;
    }
}
