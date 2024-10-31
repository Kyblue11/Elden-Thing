package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents the healing effect that can be applied to an actor after consuming a CrimsonTear consumable.
 * The actor will gain health of 30 hp every turn for 5 turns, resulting in a final increase of hp of 150 hp.
 * @author Kim Tae Jun
 */
public class HealingEffect extends StatusEffect {

    /**
     * The age of the CrimsonTear healing effect.
     */
    private int age;

    /**
     * The initial age of the CT healing effect.
     */
    private static final int INITIAL_AGE = 0;

    /**
     * The duration of the CT healing effect.
     */
    private final int HEAL_DURATION = 5;

    /**
     * The healing dealt to an actor by the CT healing effect.
     */
    private final int HEAL = 30;

    /**
     * The name of the healing effect.
     */
    private static final String NAME = "CT Healing";

    /**
     * Constructor for the CTHealingEffect class.
     * Initializes the healing effect with a name and initial age.
     */
    public HealingEffect() {
        super(NAME);
        age = INITIAL_AGE;
    }

    /**
     * Applies the healing effect to the actor every turn until the duration is complete.
     * If the actor's health reaches zero during the effect, a game over action is executed.
     *4
     * 4
     *
     * @param location the location of the actor
     * @param actor the actor receiving the healing effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        // If the age / tick reaches the 5th turn, remove the healing effect
        if (age >= HEAL_DURATION) {
            actor.removeStatusEffect(this);
        }
        // Continue to heal if it is below 5 turns
        else {
            actor.heal(HEAL);
            age++;
        }

    }
}
