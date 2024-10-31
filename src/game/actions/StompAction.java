package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.effects.ExplosionEffect;
import game.effects.FireRingEffect;
import game.positions.LocationUtils;

import java.util.List;
import java.util.Random;

/**
 * A class that represents a stomp attack action.
 * A stomp action is the same as an attack action,
 * but with a chance to deal explosion damage to surrounding actors,
 * and set surrounding ground on fire.
 * @author Aaron Lam Kong Yew
 * Modified by Kim Tae Jun
 */
public class StompAction extends AttackAction {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The chance of an extra explosion damaging the surrounding actors
     */
    private static final double EXPLOSION_CHANCE = 10;

    /**
     * The damage dealt by explosion;
     */
    private static final int EXPLOSION_DAMAGE = 50;

    /**
     * The percentage value
     */
    private static final int PERCENTAGE = 100;

    /**
     * An explosion effect that deals extra explosion damage to surrounding actors
     */
    //50
    private ExplosionEffect explosionEffect;

    /**
     * A fire ring effect that sets surrounding ground on fire
     */
    private FireRingEffect fireRingEffect;

    /**
     * Constructor.
     *
     * @param target the Actor that is to be attacked
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public StompAction(Actor target, String direction, Weapon weapon) {
        super(target, direction, weapon);
        this.target = target;

    }

    /**
     * Execute the stomp attack action
     * If the actor has the explosion capability, there is a chance to deal explosion damage to surrounding actors,
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result =  super.execute(actor, map);

        List<Location> surroundingLocations = LocationUtils.getSurroundingLocations(map.locationOf(actor));
        Random rand = new Random();
        explosionEffect = new ExplosionEffect(EXPLOSION_DAMAGE);
        fireRingEffect = new FireRingEffect();
        if (rand.nextInt(PERCENTAGE) < EXPLOSION_CHANCE) {
            result += "\n" + actor + "'s stomp attack results in a shockwave in the surrounding areas.";
            result += explosionEffect.applyEffect(actor, surroundingLocations, map);
            result += fireRingEffect.applyEffect(actor, surroundingLocations, map);

        }

        return result;
    }
}

