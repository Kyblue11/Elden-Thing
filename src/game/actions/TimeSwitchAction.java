package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.TimeEffect;

import java.util.Random;

/**
 * A class that represents the TimeSwitch action.
 * @Author Hsu Chyi See
 */
public class TimeSwitchAction extends Action {
    /**
     * The chance of triggering the TimeSwitch action.
     */
    private static final int TRIGGER_CHANCE = 15;

    /**
     * The percentage value
     */
    private static final int PERCENTAGE = 100;

    /**
     * Execute the TimeSwitch action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random rand = new Random();

        if (rand.nextInt(PERCENTAGE) <= TRIGGER_CHANCE) {
            TimeEffect.toggleTime();
            return "Time has switched to night";
        }
        return "Still day!";
    }

    /**
     * Returns a description of the TimeSwitch action.
     * @param actor The actor performing the action.
     * @return A string description of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Change to nighttime";
    }
}
