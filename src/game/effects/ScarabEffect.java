package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;


/**
 * A class that represents the buff effect that can be applied to an actor after consuming a Scarab.
 * The actor's maximum health and mana will be increased by 30 and 50 respectively for 10 turns.
 *
 * @author Kim Tae Jun
 */
public class ScarabEffect extends StatusEffect {

    /**
     * The age of the Scarab buff effect.
     */
    private int age;

    /**
     * The initial age of the Scarab buff effect.
     */
    private static final int INITIAL_AGE = 0;

    /**
     * The duration of the Scarab buff effect.
     */
    private final int BUFF_DURATION = 10;

    /**
     * The amount of max health gained, which is 30 hp.
     */
    private final int HP_MAX = 30;

    /**
     * The amount of max mana gained, which is 50 mana.
     */
    private final int MANA_MAX = 50;

    /**
     * The name of the Scarab buff effect.
     */
    private static final String NAME = "Scarab Buff";

    /**
     * Constructor.
     * Initializes the ScarabEffect with the specified name and initial age.
     */
    public ScarabEffect() {
        super(NAME);
        age = INITIAL_AGE;
    }

    /**
     * Applies the buff effect to the actor each turn.
     * Increases max health and mana for a duration and removes the effect after expiration.
     * If the actor becomes unconscious, a GameOverAction is executed.
     *
     * @param location the location of the actor
     * @param actor the actor receiving the buff effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        // Check if the age / tick hits the 10th turn, then remove the max hp and max mana
        if (age >= BUFF_DURATION) {
            actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, HP_MAX);
            actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.DECREASE, MANA_MAX);
            actor.removeStatusEffect(this);
        }
        // If below the 10 turns, keep adding the age at every tick
        else if (age > INITIAL_AGE) {
            age++;
        }
        // If age is at initial age which is 0, apply the increase in max hp and max mana
        else {
            actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HP_MAX);
            actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MANA_MAX);
            age++;
        }

    }
}
