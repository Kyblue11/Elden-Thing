package game.state;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface for the state of an actor.
 * Used to store and restore the state of an actor.
 * @author Aaron Lam Kong Yew
 */
public interface ActorState {

    /**
     * Restores the state of the actor.
     * @param actor the owner actor to restore the state to
     * @param target the target actor that optionally, might also be restored
     * @param map the game map
     * @param memento the memento to restore the state from
     */
    void restoreState(Actor actor, Actor target, GameMap map, ActorState memento);
}

