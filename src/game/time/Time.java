package game.time;

import edu.monash.fit2099.engine.positions.GameMap;
import game.consumables.consumableitems.ConsumableItem;

import java.util.List;

/**
 * An interface that represents the time in the game.
 * @Author Hsu Chyi See
 */
public interface Time {
    /**
     * Apply the effects of the time of day.
     *
     * @param maps The list of maps in the game.
     */
    void applyTimeEffects(List<GameMap> maps);

    /**
     * Transition to the next state of the game.
     *
     * @return The next state of the game.
     */
    Time transition();

    /**
     * Get the current time of the game.
     *
     * @return The current time of the game.
     */
    Time getCurrentTime();
}