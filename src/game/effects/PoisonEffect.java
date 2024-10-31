package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityPassiveAbility;

/**
 * A class that represents the poison effect that can be applied to an actor.
 * The actor will be hurt by 5 points of damage for 3 turns.
 * If the actor has the poison resistance ability, the actor will not be hurt by the poison effect.
 * @author Hsu Chyi See
 */
public class PoisonEffect extends StatusEffect {

    /**
     * The age of the poison effect.
     */
    private int age;

    /**
     * The initial age of the poison effect.
     */
    private static final int INITIAL_AGE = 0;

    /**
     * The duration of the poison effect.
     */
    private int poisonDuration;

    /**
     * The damage dealt to an actor by the poison effect.
     */
    private int damage;

    /**
     * The name of the poison effect.
     */
    private static final String NAME = "Poison";

    /**
     * Constructor.
     *
     * @param newPoisonDuration the duration for which the poison effect lasts
     * @param newDamage the damage dealt by the poison effect each turn
     */
    public PoisonEffect(int newPoisonDuration, int newDamage) {
        super(NAME);
        age = INITIAL_AGE;
        poisonDuration = newPoisonDuration;
        damage = newDamage;
    }

    /**
     * Applies the poison effect to the actor each turn.
     * The actor takes damage unless they have poison resistance.
     * If the actor becomes unconscious, a GameOverAction may be executed.
     *
     * @param location the location of the actor
     * @param actor the actor receiving the poison effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        // If the actor has poison resistance, the actor will not be hurt by the poison effect
        if (actor.hasCapability(EntityPassiveAbility.POISON_RESISTANCE)) {
            actor.removeStatusEffect(this);
        }

        // If the actor is unconscious or the poison effect has lasted for the duration, remove the poison effect
        if (getAge() >= poisonDuration || !actor.isConscious()) {
            actor.removeStatusEffect(this);
        } else {
            // If the actor is conscious, apply the poison effect
            actor.hurt(damage);

            new Display().println(actor + " is poisoned by " + NAME);
            incrementAge();

            // If the actor is unconscious, remove the poison effect
            if (!actor.isConscious()) {
                actor.removeStatusEffect(this);
            }
        }
    }

    /**
     * Increments the age of the poison effect.
     */
    public void incrementAge() {
        age++;
    }

    /**
     * Returns the age of the poison effect.
     *
     * @return the age of the poison effect in integer
     */
    public int getAge() {
        return age;
    }
}
