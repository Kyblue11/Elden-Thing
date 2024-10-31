package game.divinePowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Abstract class representing a Divine Power that can be used by a game entity.
 * Subclasses will define specific types of divine powers.
 *
 * @author stanl
 */
public abstract class DivinePower {

    /**
     * The name of the divine power.
     */
    private String divinePowerName;

    /**
     * Constructor to initialize the divine power with its name.
     *
     * @param name The name of the divine power.
     */
    public DivinePower(String name) {
        this.divinePowerName = name;
    }

    /**
     * Gets the name of the divine power.
     *
     * @return the name of the divine power.
     */
    public String getDivinePowerName() {
        return divinePowerName;
    }

    /**
     * Abstract method to define the usage of the divine power.
     * Subclasses must implement how the divine power is used.
     *
     * @param actor  The actor using the power.
     * @param target The target actor on whom the power is being used.
     * @param map    The game map where the power is being used.
     * @return A description of the action performed by the divine power.
     */
    public abstract String useDivinePower(Actor actor, Actor target, GameMap map);
}
