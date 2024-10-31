package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.consumables.consumableitems.ConsumableItem;
import game.time.Time;

import java.util.List;

/**
 * A class that represents the Time effect of the game.
 * @see StatusEffect
 * @Author Hsu Chyi See
 */
public class TimeEffect extends StatusEffect {
    /**
     * The age of the time effect.
     */
    private static int age;

    /**
     * The initial age of the time effect.
     */
    private static final int INITIAL_AGE = 0;

    /**
     * The duration of the time effect.
     */
    private static final int DURATION = 12;

    /**
     * The duration of a day.
     */
    private static final int DAY = 24;

    /**
     * The name of the time effect.
     */
    private static final String NAME = "Time Effect";

    /**
     * The duration of an hour.
     */
    private static final int ONE_HOUR = 1;

    /**
     * The list of game maps.
     */
    List<GameMap> maps;

    /**
     * The state of the time.
     */
    private static Time timeState;

    /**
     * Constructor.
     *
     * @param timeState The state of the time.
     * @param maps The list of game maps.
     */
    public TimeEffect(Time timeState, List<GameMap> maps) {
        super(NAME);
        TimeEffect.timeState = timeState;
        TimeEffect.age = INITIAL_AGE;
        this.maps = maps;

    }

    /**
     * Tick method for the time effect.
     * @param location The location of the actor.
     * @param actor The actor in the game.
     */
    @Override
    public void tick(Location location, Actor actor) {
        new Display().println("Time: " + age);
        // Transition after every 12 ticks
        if (age == DURATION) {
            timeState = timeState.transition(); // Switch between Day/Night
        } else if (age >= DAY) {
            timeState = timeState.transition(); // Switch between Day/Night
            age = INITIAL_AGE;
        }

        // Apply the current time effects (either day or night)
        timeState.getCurrentTime().applyTimeEffects(maps);
        // Increment the ticks
        age++;
    }

    /**
     * Check if the time effect is day.
     * @return True if the time effect is day, false otherwise.
     */
    public static boolean isDay() {
        return age <= DURATION;
    }


    /**
     * Check if the time effect is midnight.
     * @return True if the time effect is midnight, false otherwise.
     */
    public static boolean isMidnight() {
        return age == DAY;
    }

    /**
     * Check if the time effect is dawn.
     * @return True if the time effect is dawn, false otherwise.
     */
    public static boolean isDawn() {
        return age == INITIAL_AGE;
    }

    /**
     * Check if the time effect is sunset.
     * @return True if the time effect is sunset, false otherwise.
     */
    public static boolean isSunset() {
        return age == DURATION + ONE_HOUR;
    }



    /**
     * Toggle the time effect.
     */
    public static void toggleTime() {
        if (isDay()) {
            timeState = timeState.transition();
            age = DURATION + ONE_HOUR;
        }
    }




}

