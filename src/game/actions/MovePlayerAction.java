package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents the action of moving the player to a new location on the map.
 * This class extends the MoveActorAction class, which is provided in the engine.
 * The MovePlayerAction class is used to move the player to a new location on the map.
 * If the player is unable to move to the new location, an IllegalArgumentException will be thrown.
 * This class is used to handle the movement of the player in the game.
 * @author Aaron Lam Kong Yew
 */
public class MovePlayerAction extends MoveActorAction {

    /**
     * Constructor to create an Action that will move the player to a Location in a given Direction.
     *
     * @param moveToLocation the destination of the move
     * @param direction the direction of the move
     */
    public MovePlayerAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    /**
     * Executes the action of moving the player to a new location on the map.
     * If the player is unable to move to the new location, an IllegalArgumentException will be thrown.
     * Gracefully handles the exception by returning the exception message.
     *
     * @param actor the actor that is being moved
     * @param map the game map
     * @return a string that represents the result of the action execution
     * @throws IllegalArgumentException if the player is unable to move to the new location
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        try {
            return super.execute(actor, map);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}