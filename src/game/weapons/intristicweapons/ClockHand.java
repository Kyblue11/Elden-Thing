package game.weapons.intristicweapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import java.util.Random;

public class ClockHand extends IntrinsicWeapon {
    /**
     * The damage dealt by the minute hand
     */
    private static final int DAMAGE = 30;

    private static final int DAMAGE_SECOND = 5;


    /**
     * The verb describing the action of the minute hand
     */
    private static final String VERB = "stabs";

    /**
     * The chance to hit with the minute hand
     */
    private static final int HIT_CHANCE_MINUTE = 10;

    /**
     * The chance to hit with the second hand
     */
    private static final int HIT_CHANCE_SECOND = 50;

    /**
     * The damage dealt by the second hand
     */
    private int hitRateSecond;

    /**
     * The damage dealt by the second hand
     */
    private int damageSecond;
    /**
     * The percentage value
     */
    private static final int PERCENTAGE = 100;


    /**
     * Constructor.
     */
    public ClockHand(){
        super(DAMAGE,VERB,HIT_CHANCE_MINUTE);
        this.hitRateSecond = HIT_CHANCE_SECOND;
        this.damageSecond = DAMAGE_SECOND;
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand1 = new Random();
        Random rand2 = new Random();
        if ((rand1.nextInt(PERCENTAGE) < this.hitRate)) {
            target.hurt(damage);
            return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
        } else if ((rand2.nextInt(PERCENTAGE) < this.hitRateSecond)) {
            target.hurt(damageSecond);
            return String.format("%s %s %s for %d damage", attacker, verb, target, damageSecond);

        } else {
            return attacker + " misses " + target + ".";
        }


    }
}
