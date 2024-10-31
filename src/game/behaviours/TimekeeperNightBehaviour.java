package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TimekeeperNightAction;

/**
 * A class that represents the behaviour of the Timekeeper at night.
 * @Author Hsu Chyi See
 */
public class TimekeeperNightBehaviour implements Behaviour {
    /**
     * The target actor that our actor is attacking.
     */
    private final Actor target;

    /**
     * Constructor for the behaviour.
     * Initializes the target actor for this behaviour.
     *
     * @param subject the target actor that our actor is attacking
     */
    public TimekeeperNightBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Determines the action the actor will take
     *
     * @param actor the actor performing the behaviour
     * @param map the game map on which the actor is located
     * @return an Action representing the night attack if a suitable target is found, or null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (target != null) {
            return new TimekeeperNightAction(target,  map.locationOf(target).toString());
        }
        return null;
    }
}
