package game.state;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;

/**
 * A class that represents the state of an actor's ENUM attributes.
 * Implements the  ActorState interface to store and restore the state of an actor's attributes.
 * Created by:
 * @author Aaron Lam Kong Yew
 */
public class ActorAttributesState implements ActorState {

    /**
     * A map that stores the ENUM attributes and their values.
     */
    private final Map<Enum<?>, Integer> attributes;

    /**
     * Constructor.
     * @param attributes the map of ENUM attributes and their values
     */
    public ActorAttributesState(Map<Enum<?>, Integer> attributes) {
        this.attributes = attributes;
    }

    /**
     * Restores the state of the actor's ENUM attributes.
     * @param actor the owner actor to restore the state to
     * @param target the target actor that optionally, might also be restored
     * @param map the game map
     * @param memento the memento to restore the state from
     */
    @Override
    public void restoreState(Actor actor, Actor target, GameMap map, ActorState memento) {
        for (Map.Entry<Enum<?>, Integer> entry : attributes.entrySet()) {
            Enum<?> attribute = entry.getKey();
            int value = entry.getValue();
            actor.modifyAttribute(attribute, ActorAttributeOperations.UPDATE, value);
        }
    }
}