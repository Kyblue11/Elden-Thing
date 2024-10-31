package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.AttackBehaviour;
import game.positions.LocationUtils;
import game.spawn.SpawnableEnemy;
import game.weapons.intristicweapons.BareFist;

/**
 * The Spirit class represents a specific type of enemy character in the game.
 * Spirit uses bare fists as its intrinsic weapon and can attack the player when adjacent.
 * It extends the SpawnableEnemy class, making it a spawnable entity.
 * @author stanl
 */
public class Spirit extends SpawnableEnemy {

    /**
     * Name of the Spirit enemy
     */
    private static final String NAME = "Spirit";

    /**
     * Character symbol representing the Spirit on the map
     */
    private static final char CHAR = '&';

    /**
     * Health points of the Spirit
     */
    private static final int HP = 100;

    /**
     * The chance that the Spirit will spawn
     */
    private static final double CHANCE = 0.20;

    /**
     * The behaviour to attack the player
     */
    private static final int ATTACK_BEHAVIOUR = 1;

    /**
     * Constructor for the Spirit class.
     * Initializes the enemy with predefined name, character representation, and health points (HP).
     * Also assigns the intrinsic weapon (BareFist) and spawn chance.
     */
    public Spirit(){
        super(NAME, CHAR, HP);
        this.setIntrinsicWeapon(new BareFist());
        this.setSpawnChance(CHANCE);
    }

    /**
     * Executes the actions for the Spirit on its turn.
     * If the player (target) is within an adjacent location, it adds attack behaviour.
     * then, it executes the default enemy behaviour from the parent class.
     *
     * @param actions available actions for the Spirit
     * @param lastAction the last action performed by the Spirit
     * @param map the current game map
     * @param display the display to output game messages
     * @return the next action for the Spirit to perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (target != null && LocationUtils.isAdjacent(map.locationOf(this), map.locationOf(target))) {
            display.println("Player is within Attacking Range!");
            this.behaviours.put(ATTACK_BEHAVIOUR, new AttackBehaviour(target));
        }
        // call Enemy's playTurn method to perform the default behaviour
        return super.playTurn(actions, lastAction, map, display);
    }
}
