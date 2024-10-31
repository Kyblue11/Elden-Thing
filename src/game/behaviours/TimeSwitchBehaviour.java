package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TimeSwitchAction;
import game.actors.Player;

/**
 * A class that represents the behaviour of the TimeSwitch.
 * @Author Hsu Chyi See
 */
public class TimeSwitchBehaviour implements Behaviour {
    /**
     * The target actor that our actor is attacking.
     */
    private final Actor target;

    /**
     * Constructor.
     *
     * @param player The player in the game.
     */
    public TimeSwitchBehaviour(Actor player) {
        this.target = player;
    }

    /**
     * Determines the action the actor will take based on the presence of other actors in the vicinity.
     *
     * @param actor the actor performing the behaviour
     * @param map the game map on which the actor is located
     * @return an Action representing the TimeSwitch attack if a suitable target is found, or null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (target != null) {
            return new TimeSwitchAction();
        }
        return null;
    }
}
