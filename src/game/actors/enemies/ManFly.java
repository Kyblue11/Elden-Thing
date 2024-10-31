package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.FollowBehaviour;
import game.behaviours.StingBehaviour;
import game.positions.LocationUtils;
import game.spawn.SpawnableEnemy;
import game.weapons.intristicweapons.ManFlySting;

/**
 * The ManFly class represents a specific type of enemy character in the game.
 * ManFly has a sting weapon, can follow the player, and attacks when adjacent.
 * It extends the SpawnableEnemy class, making it a spawnable entity.
 * @author stanl
 */
public class ManFly extends SpawnableEnemy {

    /** Name of the ManFly enemy */
    private static final String NAME = "ManFly";

    /** Character symbol representing the ManFly on the map */
    private static final char CHAR = '%';

    /** Health points of the ManFly */
    private static final int HP = 50;

    /** The chance that the ManFly will spawn */
    private static final double CHANCE = 0.15;

    /** The behaviour to sting the player */
    private static final int STING_BEHAVIOUR = 1;

    /** The behaviour to follow the player */
    private static final int FOLLOW_BEHAVIOUR = 2;

    /**
     * Constructor for the ManFly class.
     * Initializes the enemy with predefined name, character representation, and health points (HP).
     * Also assigns the intrinsic weapon (Sting) and spawn chance.
     */
    public ManFly(){
        super(NAME, CHAR, HP);
        this.setIntrinsicWeapon(new ManFlySting());
        this.setSpawnChance(CHANCE);
    }

    /**
     * Executes the actions for the ManFly on its turn.
     * If the player (target) is within an adjacent location, it adds attack and follow behaviours.
     * then, it executes the default enemy behaviour from the parent class.
     *
     * @param actions   available actions for the ManFly
     * @param lastAction the last action performed by the ManFly
     * @param map       the current game map
     * @param display   the display to output game messages
     * @return the next action for the ManFly to perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (target != null && LocationUtils.isAdjacent(map.locationOf(this), map.locationOf(target))) {
            this.behaviours.put(STING_BEHAVIOUR, new StingBehaviour(target));
            this.behaviours.put(FOLLOW_BEHAVIOUR, new FollowBehaviour(target));
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
