package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.abilites.EntityPassiveAbility;
import game.effects.PoisonEffect;

import java.util.Random;

/**
 * Class representing an action to sting an actor
 * @author stanl
 */
public class StingAction extends AttackAction{
    /**
     * The target actor to be stung
     */
    private Actor target;

    /**
     * The chance of poisoning the target
     */
    private static final double POISON_CHANCE = 30;

    /**
     * The duration of the poison effect
     */
    private static final int POISON_DURATION = 2;

    /**
     * The damage of the poison effect
     */
    private static final int POISON_DAMAGE = 10;

    /**
     * Constructor.
     *
     * @param target the Actor to sting
     * @param direction the direction where the sting should be performed (only used for display purposes)
     */
    public StingAction(Actor target, String direction){
        super(target,direction,null);
        this.target = target;
    }

    /**
     * Execute the sting action
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map){
        int healthBefore = target.getAttribute(BaseActorAttributes.HEALTH);
        String result =  super.execute(actor, map);
        Random rand = new Random();
        int healthAfter = target.getAttribute(BaseActorAttributes.HEALTH);
        if (rand.nextInt(100) < POISON_CHANCE && healthBefore>healthAfter){
            result += "\n" + actor + "'s sting results in a poison effect to "+target;
            if (target != null && !target.hasCapability(EntityPassiveAbility.POISON_RESISTANCE)){
                target.addStatusEffect(new PoisonEffect(POISON_DURATION, POISON_DAMAGE));
            }
        }
        return result;
    }
}
