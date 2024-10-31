package game.positions.grounds;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents bare dirt.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Aaron Lam Kong Yew
 *
 */
public class Dirt extends Ground {

    /* Character representing the dirt ground */
    private static final char DISPLAY_CHAR = '.';

    /* Name of the dirt ground */
    private static final String NAME = "Dirt";

    /**
     * Constructor.
     */
    public Dirt() {
        super(DISPLAY_CHAR, NAME);
    }
}