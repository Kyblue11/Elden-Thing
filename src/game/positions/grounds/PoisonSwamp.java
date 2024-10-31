package game.positions.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityPassiveAbility;
import game.effects.PoisonEffect;

/**
 * A class that represents a poison swamp.
 * The poison swamp will apply a poison effect to actors
 * @see PoisonEffect
 * @author Hsu Chyi See
 */
public class PoisonSwamp extends Ground  {
    /**
     * The display character of the poison swamp.
     */
    private static final char DISPLAY_CHAR = '+';

    /**
     * The name of the poison swamp.
     */
    private static final String NAME = "Poison Swamp";

    /**
     * The duration of the poison effect.
     */
    private final int DURATION = 3;

    /**
     * The damage dealt to an actor by the poison effect.
     */
    private final int DAMAGE = 5;

    /**
     * Constructor.
     */
    public PoisonSwamp() {
        super(DISPLAY_CHAR, NAME);
        addCapability(EntityPassiveAbility.PUDDLE_OF_WATER);
    }

    /**
     * Applies the poison effect to the actor every turn if the actor is standing on the poison swamp.
     *
     * @param location the location of the actor
     */
    @Override
    public void tick(Location location) {
        Actor actor = location.getActor();
        if (location.containsAnActor()){
            actor.addStatusEffect(new PoisonEffect(DURATION, DAMAGE));

        }
    }


}
