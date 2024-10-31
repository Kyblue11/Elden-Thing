package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import game.actions.AttackAction;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.Behaviour;
import game.actions.GameOverAction;
import game.behaviours.WanderBehaviour;
import game.actors.attributes.Status;

import java.util.Map;
import java.util.TreeMap;

/**
 * An abstract class that represents an enemy entity
 * An enemy entity is an actor that is hostile to the player,
 * and will always include the WanderBehaviour as a default behaviour,
 * and will only attack the player if the player is hostile to it
 * @author Aaron Lam Kong Yew
 * Modified by Hsu Chyi See
 */
public abstract class Enemy extends Actor {
    /**
     * The priority of the WanderBehaviour
     */
    private static final int WANDER_BEHAVIOUR = 999;

    /**
     * The behaviours that the Enemy can perform
     * Each behaviour has a priority, where the lower the number, the higher the priority
     * Each behaviour will call a specific action to be performed
     */
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * The target that this Enemy is following
     */
    protected Actor target;

    /**
     * Constructor.
     * @param name the name of the enemy
     * @param displayChar the character that will represent the enemy
     * @param hitPoints the hit points of the enemy
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(WANDER_BEHAVIOUR, new WanderBehaviour());
        this.addCapability(Status.FRIENDLY_TO_ENEMY);
    }

    /**
     * The enemy's turn to play
     * Order of Priority:
     * 1. [Some_behaviour]
     * ...
     * 999. Wander around the map
     * @param actions the actions that the enemy can perform
     * @param lastAction the last action that the enemy performed
     * @param map the map that the enemy is on
     * @param display the display that will display the map
     * @return the action that the enemy will perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            return new GameOverAction();
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * An enemy's allowable actions
     * This actor can only be attacked by the player if the player is hostile to it
     * @param otherActor the other actor that the Enemy entity can interact with
     * @param direction the direction of the other actor
     * @param map the map that this Actor is on
     * @return the list of actions that can be performed
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            this.target = otherActor;
        }
        return actions;
    }
}