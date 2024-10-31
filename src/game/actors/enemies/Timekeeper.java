package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.abilites.EntityDamageAbility;
import game.abilites.EntityPassiveAbility;
import game.abilites.TraversableAbility;
import game.actors.Player;
import game.behaviours.*;
import game.effects.TimeEffect;
import game.positions.LocationUtils;
import game.weapons.intristicweapons.ClockHand;

/**
 * A class that represents the Timekeeper.
 * @Author Hsu Chyi See
 */
public class Timekeeper extends Enemy {

    /**
     * The name of the Timekeeper.
     */
    private final static String NAME = "Timekeeper";

    /**
     * The character of the Timekeeper.
     */
    private static final char CHAR = '0';

    /**
     * The hit points of the Timekeeper.
     */
    private static final int HP = 3600;

    /**
     * The priority of the Timekeeper's night behaviour.
     */
    private static final int TIMEKEEPER_NIGHT_BEHAVIOUR = 0;

    /**
     * The priority of the Timekeeper's follow behaviour.
     */
    private static final int FOLLOW_BEHAVIOUR = 20;

    /**
     * The priority of the Timekeeper's attack behaviour.
     */
    private static final int ATTACK_BEHAVIOUR = 30;

    /**
     * The priority of the Timekeeper's run away behaviour.
     */
    private static final int RUN_AWAY_BEHAVIOUR = 40;

    /**
     * The priority of the Timekeeper's time switch behaviour.
     */
    private static final int TIME_SWITCH_BEHAVIOUR = 70;




    /**
     * Constructor.
     */
    public Timekeeper() {
        super(NAME, CHAR, HP);
        this.setIntrinsicWeapon(new ClockHand());
        this.addCapability(EntityPassiveAbility.FIRE_RESISTANCE);
        this.addCapability(EntityPassiveAbility.DROWNABLE);
        this.addCapability(EntityDamageAbility.HOUR);
        this.addCapability(TraversableAbility.FLOOR_TRAVERSABLE);
    }

    /**
     * Determines the action the actor will take based on the presence of other actors in the vicinity.
     *
     * @param actions the actions that the actor can take
     * @param lastAction the action that the actor took last turn
     * @param map the game map on which the actor is located
     * @param display the I/O object to which messages may be written
     * @return an Action representing the Timekeeper's behaviour
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
         if (TimeEffect.isDay()) {
            clearNightBehaviours();
            handleDayBehaviour(map);
        } else {
             handleNightBehaviour(map);
         }

        // Call Enemy's playTurn method to perform the default behaviour
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Handle the Timekeeper's behaviour during the day.
     * @param map
     */
    private void handleDayBehaviour(GameMap map) {
        RunAwayBehaviour runAwayBehaviour = new RunAwayBehaviour(target);
        TimeSwitchBehaviour timeSwitchBehaviour = new TimeSwitchBehaviour(target);

        // Add daytime behavior: run away from the player and switch time
        this.behaviours.put(RUN_AWAY_BEHAVIOUR, runAwayBehaviour);
        this.behaviours.put(TIME_SWITCH_BEHAVIOUR, timeSwitchBehaviour);

    }

    /**
     * Handle the Timekeeper's behaviour during the night.
     * @param map
     */
    private void handleNightBehaviour(GameMap map) {
        FollowBehaviour followBehaviour = new FollowBehaviour(target);
        TimekeeperNightBehaviour timekeeperNightBehaviour = new TimekeeperNightBehaviour(target);
        AttackBehaviour attackBehaviour = new AttackBehaviour(target);


        if (TimeEffect.isMidnight()) {
            this.behaviours.put(TIMEKEEPER_NIGHT_BEHAVIOUR, timekeeperNightBehaviour);
        } else if (target != null && LocationUtils.isAdjacent(map.locationOf(this), map.locationOf(target))) {
            this.behaviours.put(ATTACK_BEHAVIOUR, attackBehaviour);
        }
        this.behaviours.put(FOLLOW_BEHAVIOUR, followBehaviour);
    }

    /**
     * Clear all night-specific behaviours.
     */
    private void clearNightBehaviours() {
        this.behaviours.remove(TIMEKEEPER_NIGHT_BEHAVIOUR);
        this.behaviours.remove(FOLLOW_BEHAVIOUR);
        this.behaviours.remove(ATTACK_BEHAVIOUR);
    }

}
