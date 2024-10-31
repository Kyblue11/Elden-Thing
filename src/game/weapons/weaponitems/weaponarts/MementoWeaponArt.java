package game.weapons.weaponitems.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.state.ActorState;
import game.state.statefactory.ActorStateFactory;

import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * A class that represents the Memento weapon art.
 * This class is used to remember and restore the state of an actor, based on the attributes provided.
 * It will save a 'snapshot' of the actor's attributes state from a previous turn and restore it randomly.
 * Implements the WeaponArt interface to execute the Memento weapon art.
 * Created by:
 * Aaron Lam Kong Yew
 */
public class MementoWeaponArt implements WeaponArt {

    /**
     * A stack that stores the mementos of the actor's state, as a list of ActorState objects.
     */
    private final Stack<List<ActorState>> mementos = new Stack<>();

    /**
     * The list of factories that will make up the remembered 'state' of the actor.
     */
    private final List<ActorStateFactory> stateFactories;

    /**
     * The health cost to use the Memento weapon art.
     */
    private static final int HEALTH_COST = 5;

    /**
     * The mana cost to use the Memento weapon art.
     */
    private static final int MANA_COST = 0;

    /**
     * Constructor.
     * @param stateFactories the list of factories that will create the 'remembrance' of the actor's past state.
     */
    public MementoWeaponArt(List<ActorStateFactory> stateFactories) {
        this.stateFactories = stateFactories;
    }

    /**
     * Executes the Memento weapon art.
     * The actor will remember their state and save it as a memento.
     * If the actor has a memento, the actor will restore their state from the memento.
     * Uses the two primary methods, createMemento and restoreMemento, to remember and restore the actor's state.
     * @param attacker the actor that uses the weapon art
     * @param target the target actor that is optionally used for the weapon art
     * @param map the game map
     * @return a string that represents the result of the weapon art execution
     */
    @Override
    public String execute(Actor attacker, Actor target, GameMap map) {
        if (!canExecute(attacker)) {
            return attacker + " does not have enough health to use Memento!\n";
        }

        Random rand = new Random();
        if (rand.nextBoolean()) {
            // push to the mementos stack
            createMemento(attacker, map.locationOf(attacker), map);
            attacker.hurt(getHealthCost());
            return attacker + " uses Memento and will remember their state!\n";

        } else if (!mementos.isEmpty()) {
            // pop from the mementos stack
            restoreMemento(attacker, target, map, mementos.pop());
            attacker.hurt(getHealthCost());
            return attacker + " used Memento to restore their past state!\n";
        }
        return attacker + " does not have any remembered state to restore!\n";
    }

    /**
     * Creates a memento of the actor's current state.
     * The actor will save his attributes and values as a list of ActorState objects,
     * where each ActorState list represents a zip-file of different values belonging to an actor, such as
     * the actor's attributes, location, wallet, effects, etc.
     * These values may have different types, such as integers, strings, enums or objects.
     * @param actor the actor to create the memento for
     * @param location the location of the actor
     * @param map the game map
     */
    private void createMemento(Actor actor, Location location, GameMap map) {
        List<ActorState> states = stateFactories.stream()
                .map(factory -> factory.createState(actor, location, map))
                .toList();
        mementos.push(states);
    }

    /**
     * Restores the actor's state from the most recent memento on the stack.
     * The actor will restore their attributes and stats from the memento, 'reverting' to the previous state.
     * @param actor the actor to restore the state to
     * @param target the target actor that optionally, might also be restored
     * @param map the game map
     * @param mementos the list of mementos to restore the state from
     */
    private void restoreMemento(Actor actor, Actor target, GameMap map, List<ActorState> mementos) {
        for (ActorState memento : mementos) {
            memento.restoreState(actor, target, map, memento);
        }
    }

    /**
     * Gets the mana cost to use the Memento weapon art.
     * @return the mana cost to use the Memento weapon art is free
     */
    @Override
    public int getManaCost() {
        return MANA_COST;
    }

    /**
     * Gets the health cost to use the Memento weapon art.
     * @return the health cost to use the Memento weapon art
     */
    public int getHealthCost() {
        return HEALTH_COST;
    }

    /**
     * Checks if the actor can execute the Memento weapon art.
     * The actor must have more health than the health cost to use the Memento weapon art.
     * @param attacker the actor that uses the weapon art
     * @return true if the actor can execute the Memento weapon art, false otherwise
     */
    @Override
    public boolean canExecute(Actor attacker) {
        return attacker.getAttribute(BaseActorAttributes.HEALTH) > getHealthCost();
    }
}
