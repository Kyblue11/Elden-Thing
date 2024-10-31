package game.state.statefactory;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.state.ActorState;

/**
 * An interface for creating an abstract factory of an actor's state.
 * Used to create the state of an actor.
 * An actor's state may contain different data values that are independent of one another
 * Created by:
 * @author Aaron Lam Kong Yew
 */
public interface ActorStateFactory {

    /**
     * Creates the state of an actor, based on different type values.
     * @param actor the actor to create the state for
     * @param location the location of the actor
     * @param map the game map
     * @return the state of the actor
     */
    ActorState createState(Actor actor, Location location, GameMap map);
}