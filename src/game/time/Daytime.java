package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.consumables.consumableitems.ConsumableItem;
import game.positions.ActorCollector;

import java.util.List;

/**
 * A class that represents the Daytime state of the game.
 * @Author Hsu Chyi See
 */
public class Daytime extends TimeState {
    /**
     * The initial age of the state
     */
    private static final int INITIAL_AGE = 0;

    /**
     * The damage multiplier during day time.
     */
    private static final int DAMAGE_MULTIPLIER = 1;

    /**
     * Apply the effects of the time of day.
     *
     * @param maps The list of maps in the game.
     */
    @Override
    public void applyTimeEffects(List<GameMap> maps) {
        new Display().println("It's day!");
        // Normal damage, restore flask charges to full
        if (super.getTicks() == INITIAL_AGE) {
            List<Actor> actors = ActorCollector.collectAllActors(maps);

            for (Actor actor : actors) {
                actor.updateDamageMultiplier(DAMAGE_MULTIPLIER);
            }
            new Display().println("Flask charges restored to full, normal damage from enemies");
        }
        super.incrementTicks();
    }

    /**
     * Transition to the next state of the game.
     *
     * @return The next state of the game.
     */
    @Override
    public Time transition() {
        new Display().println("Sun is setting...");
        return new Nightime();

    }
}