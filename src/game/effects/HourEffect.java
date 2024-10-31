package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityDamageAbility;
import game.positions.ActorCollector;

import java.util.List;

public class HourEffect implements DamageEffect {
    /**
     * The percentage of damage dealt to surrounding actors per hour.
     */
    private static final int HOUR_DAMAGE_PERCENTAGE = 30;


    /**
     * Applies the effect of the explosion.
     *
     * @param actor    the actor causing the explosion
     * @param location the location of the explosion
     */
    @Override
    public void applyEffect(Actor actor, Location location) {
        // For future implementation
    }

    /**
     * Deals extra explosion damage to surrounding actors.
     *
     * @param actor     the actor causing the explosion
     * @param locations a list of surrounding locations
     * @param map       the game map
     * @return a string describing the result of the explosion damage, or an empty string if no damage is dealt
     */
    @Override
    public String applyEffect(Actor actor, List<Location> locations, GameMap map) {
        if (!actor.hasCapability(EntityDamageAbility.HOUR)) {
            return "";
        }

        String result = "";
        if (TimeEffect.isMidnight()) {
            List<Actor> actors = ActorCollector.collectAllActors(map);
            for (Actor entity : actors) {
                int damage = entity.getAttribute(BaseActorAttributes.HEALTH) * HOUR_DAMAGE_PERCENTAGE / 100;
                entity.hurt(damage);
                result += "\n" + entity + " takes " + damage + " hour damage! ";
            }

        }
        return result;
    }
}
