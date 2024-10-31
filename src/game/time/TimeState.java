package game.time;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.consumables.consumableitems.ConsumableItem;

import java.util.List;

/**
 * A class that represents the state of time in the game.
 * @Author Hsu Chyi See
 */
public abstract class TimeState extends GameEntity implements Time {
    /**
     * The number of ticks that have passed in the game.
     */
    private int ticks;

    /**
     * The initial age of the state
     */
    private static final int INITIAL_AGE = 0;

    /**
     * Constructor.
     */
    public TimeState() {
        this.ticks = INITIAL_AGE;
    }

    /**
     * Apply the effects of the time of day.
     * @param maps The list of maps in the game.
     */
    public abstract void applyTimeEffects(List<GameMap> maps);

    /**
     * Transition to the next state of the game.
     * @return The next state of the game.
     */
    public abstract Time transition();

    /**
     * Get the current time of the game.
     * @return The current time of the game.
     */
    @Override
    public Time getCurrentTime() {
        return this;
    }

    /**
     * Apply the effects of the time of day.
     */
    public int getTicks() {
        return ticks;
    }

    /**
     * Increment the number of ticks that have passed in the game.
     */
    public void incrementTicks() {
        ticks++;
    }

}
