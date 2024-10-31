package game.state.statefactory;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.state.ActorAttributesState;
import game.state.ActorState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A concrete class that creates the state of an actor's ENUM attributes.
 * Implements the  ActorStateFactory interface to create the state of an actor's multiple enumerable attributes.
 * @see ActorAttributesState
 * Created by:
 * @author Aaron Lam Kong Yew
 */
public class ActorAttributesStateFactory implements ActorStateFactory {

    /**
     * A list of ENUM attributes.
     * Examples: BaseActorAttributes, TarnishedActorAttributes, Status, etc.
     */
    private final List<Enum<?>> attributes;

    /**
     * Constructor.
     * @param attributes the list of ENUM attributes
     */
    public ActorAttributesStateFactory(List<Enum<?>> attributes) {
        this.attributes = attributes;
    }

    /**
     * Initializes the state.
     * @param actor the actor to create the state for
     * @param location the location of the actor
     * @param map the game map
     * @return the state of the actor's ENUM attributes,
     * zipped with their values into an ActorAttributesState object
     */
    @Override
    public ActorState createState(Actor actor, Location location, GameMap map) {
        Map<Enum<?>, Integer> stateAttributes = new HashMap<>();
        for (Enum<?> attribute : attributes) {
            stateAttributes.put(attribute, actor.getAttribute(attribute));
        }
        return new ActorAttributesState(stateAttributes);
    }
}