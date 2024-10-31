package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.effects.*;
import game.positions.LocationUtils;

import java.util.List;

/**
 * A class that represents the TimekeeperNight action.
 * @Author Hsu Chyi See
 */
public class TimekeeperNightAction extends AttackAction {
    /**
     * The effect of the hour.
     */
    private HourEffect hourEffect;

    /**
     * The target actor that our actor is attacking.
     */
    private Actor target;

    /**
     * Constructor for the action.
     * Initializes the target actor for this action.
     *
     * @param target the target actor that our actor is attacking
     * @param direction the direction of the target actor
     */
    public TimekeeperNightAction(Actor target, String direction) {
        super(target, direction, null);
        this.target = target;
    }

    /**
     * Executes the TimekeeperNight action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string description of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result =  super.execute(actor, map);

        List<Location> surroundingLocations = LocationUtils.getSurroundingLocations(map.locationOf(actor));
        hourEffect = new HourEffect();
        result += hourEffect.applyEffect(actor, surroundingLocations, map);


        return result;
    }
}
