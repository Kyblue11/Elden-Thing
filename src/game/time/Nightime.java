package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.positions.ActorCollector;

import java.util.List;

/**
 * A class that represents the Nighttime state of the game.
 * @Author Hsu Chyi See
 */
public class Nightime extends TimeState {
    /**
     * The player in the game.
     */
    private Player player;

    /**
     * The initial age of the state
     */
    private static final int INITIAL_AGE = 0;

    /**
     * The damage multiplier during night time.
     */
    private static final int DAMAGE_MULTIPLIER = 2;




    /**
     * Apply the effects of the time of day.
     *
     * @param maps The list of maps in the game.
     */
    @Override
    public void applyTimeEffects(List<GameMap> maps) {
        new Display().println("It's night! Double damage and half flask charges.");
        // Double damage, half flask charges
        if (super.getTicks() == INITIAL_AGE) {
            List<Actor> actors = ActorCollector.collectAllActors(maps);
            // Use the iterator to loop through all the actors
            for (Actor actor : actors) {
                actor.updateDamageMultiplier(DAMAGE_MULTIPLIER);
            }

            new Display().println("Flask charges halved, double damage");
        }
        super.incrementTicks();
    }


    /**
     * Transition to the next state of the game.
     *
     * @return The next state of the game.
     */
    public Time transition() {
        new Display().println("Sun is rising...");
        return new Daytime();
    }

}

