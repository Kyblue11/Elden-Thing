package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.AttackBehaviour;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.FollowBehaviour;
import game.positions.grounds.Gate;
import game.weapons.intristicweapons.DivineBeastDancingLionBite;
import game.positions.LocationUtils;

import game.tradables.tradableItems.RemembranceOfDancingLion;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the Divine Beast Dancing Lion enemy,
 * which possesses divine powers and has the ability to follow a target.
 * This enemy can transition between various divine powers during gameplay.
 *
 * @author stanl
 */
public class DivineBeastDancingLion extends Enemy {
    /**
     * The name of the Divine Beast Dancing Lion.
     */
    private static final String NAME = "Divine Beast Dancing Lion";

    /**
     * The character representation of the Divine Beast Dancing Lion.
     */
    private static final char CHAR = 'S';

    /**
     * The hit points of the Divine Beast Dancing Lion.
     */
    private static final int HP = 10000;

    /**
     * The priority of the attack behaviour.
     */
    private static final int ATTACK_BEHAVIOUR_PRIORITY = 1;

    /**
     * The priority of the follow behaviour.
     */
    private static final int FOLLOW_BEHAVIOUR_PRIORITY = 2;

    /** The location where the Divine Beast Dancing Lion will return upon unconsciousness. */
    private Location returnGateLocation;

    /**
     * Constructor for DivineBeastDancingLion
     *
     * @param location the location where the enemy is placed
     */
    public DivineBeastDancingLion(Location location) {
        super(NAME, CHAR, HP);
        this.setIntrinsicWeapon(new DivineBeastDancingLionBite());
        this.returnGateLocation = location;
    }

    /**
     * Plays the turn for this enemy. If a target is adjacent,
     * it sets a follow behaviour for the target, allowing the enemy
     * to pursue it strategically.
     *
     * @param actions    the list of actions available to the enemy
     * @param lastAction the last action performed
     * @param map       the current game map
     * @param display    the display for outputting messages
     * @return the action to be performed this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (target != null && LocationUtils.isAdjacent(map.locationOf(this), map.locationOf(target))) {
            this.behaviours.put(ATTACK_BEHAVIOUR_PRIORITY, new AttackBehaviour(target));
            this.behaviours.put(FOLLOW_BEHAVIOUR_PRIORITY, new FollowBehaviour(target));
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Handles the unconscious state of the enemy, removing it from the map
     * and setting a gate at its location. This method is called when the
     * enemy's hit points reach zero.
     *
     * @param actor the actor responsible for this enemy's demise
     * @param map   the current game map
     * @return a message describing the demise of the enemy
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location stagefrontGateLocation = map.locationOf(this);
        map.locationOf(this).addItem(new RemembranceOfDancingLion());
        map.removeActor(this);

        List<Location> stagefrontLocations = new ArrayList<>();
        stagefrontLocations.add(returnGateLocation);
        Gate stagefrontGate = new Gate(stagefrontLocations);
        stagefrontGateLocation.setGround(stagefrontGate);

        return this + " met their demise in the hand of " + actor;
    }
}
