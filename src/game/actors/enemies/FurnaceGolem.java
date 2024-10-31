package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.FollowBehaviour;
import game.behaviours.StompBehaviour;
import game.tradables.tradableItems.RemembranceOfFurnaceGolem;
import game.positions.LocationUtils;
import game.weapons.intristicweapons.Foot;
import game.weapons.powerweapons.FurnaceEngine;

/**
 * Class representing the Furnace Golem
 * For now, it can only wander around the map.
 * @author Adrian Kristanto
 * Modified by Aaron Lam Kong Yew
 * Modified by Hsu Chyi See
 * Modified by Kim Tae Jun
 */
public class FurnaceGolem extends Enemy {

    /**
     * The name of the Furnace Golem
     */
    private static String NAME = "Furnace Golem";

    /**
     * The character that represents the Furnace Golem
     */
    private static char CHAR = 'A';

    /**
     * The hit points of the Furnace Golem
     */
    private static int HP = 1000;

    /**
     * The behaviour to follow the player
     */
    private static int FOLLOW_BEHAVIOUR = 2;

    /**
     * The behaviour to stomp the player
     */
    private static int STOMP_BEHAVIOUR = 1;


    /**
     * Constructor. Invoke the superclass constructor
     */
    public FurnaceGolem() {
        super(NAME, CHAR, HP);
        // Replaced all the capabilities with FurnaceEngine
        this.setIntrinsicWeapon(new Foot());
        this.addItemToInventory(new FurnaceEngine());
    }

    /**
     * Furnace Golem's turn to play
     * Order of Priority:
     * 1. Attack the player if the player is within stomping range
     * 2. Follow the player if the player is not within stomping range
     * 3. Wander around the map
     * @param actions the actions that the Furnace Golem can perform
     * @param lastAction the last action that the Furnace Golem performed
     * @param map the map that the Furnace Golem is on
     * @param display the display that shows the game
     * @return the action that the Furnace Golem will perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (target != null && LocationUtils.isAdjacent(map.locationOf(this), map.locationOf(target))) {
            display.println("Player is within Stomping Range!");
            this.behaviours.put(FOLLOW_BEHAVIOUR, new FollowBehaviour(target));
            this.behaviours.put(STOMP_BEHAVIOUR, new StompBehaviour(target));
        }
        // call Enemy's playTurn method to perform the default behaviour
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        String result = "";
        map.locationOf(this).addItem(new RemembranceOfFurnaceGolem());
        result += super.unconscious(actor, map);
        return result;
    }
}
