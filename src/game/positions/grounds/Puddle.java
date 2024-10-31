package game.positions.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityPassiveAbility;
import game.actions.ConsumeAction;
import game.actors.enemies.Scarab;
import game.actors.factories.ScarabFactory;
import game.consumables.Consumable;
import game.spawn.EnemySpawner;

import java.util.List;
import java.util.Random;

/**
 * A class that represents a random puddle of water.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Aaron Lam Kong Yew,
 * Kim Tae Jun
 */
public class Puddle extends Ground implements Consumable {

    /**
     * A boolean to check if the puddle has been consumed by an actor.
     */
    private boolean isConsumed = false;

    /**
     * Character representing the puddle ground
     */
    private static final char DISPLAY_CHAR = '~';

    /**
     * Name of the puddle
     */
    private static final String NAME = "Puddle";

    /**
     * The chance of an actor drowning in the puddle
     */
    private static final int DROWN_CHANCE = 5;

    /**
     * The chance of a scarab spawning when the puddle is consumed
     */
    private static final int SPAWN_CHANCE = 10;

    /**
     * The amount of mana points the actor will gain when they consume the puddle
     */
    private static final int MANA_INCREASE = 5;
    /**
     * The percentage value
     */
    private static final int PERCENTAGE = 100;

    /**
     * Constructor.
     * The puddle cannot be overriden by fire (w).
     */
    public Puddle() {
        super(DISPLAY_CHAR, NAME);
        addCapability(EntityPassiveAbility.FIRE_RESISTANCE);
        addCapability(EntityPassiveAbility.PUDDLE_OF_WATER);
    }

    /**
     * Consumes the puddle.
     * The actor will gain 5 mana points.
     *
     * @param actor the actor that is consuming the puddle
     * @return true if the actor has consumed the puddle, false otherwise
     */
    @Override
    public boolean consume(Actor actor) {
        isConsumed = true;
        // Increase actor's mana by 5
        actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MANA_INCREASE);
        return isConsumed;
    }

    /**
     * Returns a list of allowable actions for the actor on the puddle.
     * The actor can consume the puddle if there is an actor on the puddle.
     *
     * @param actor the actor on the puddle
     * @param location the location of the actor
     * @param direction the direction of the actor
     * @return a list of allowable actions for the actor
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        // If there is an actor on the puddle itself, allow the actor to have a consume puddle option
        if(location.containsAnActor()){
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }

    /**
     * Tick method for the puddle.
     * If the puddle has been consumed, there is a 10% chance that a scarab will spawn at a random location.
     * The location where the actor consumed the puddle will be the factory.
     * The scarab will spawn at a possible location out of all the surrounding puddles.
     * The ground will be reset to the puddle after the scarab has spawned.
     *
     * @param location the location of the puddle
     */
    @Override
    public void tick(Location location) {
        Random r1 = new Random();
        Random r2 = new Random();

        if (isConsumed) {
            List<Exit> exits = location.getExits();
            int num = r1.nextInt(exits.size());
            Location possibleLoc = exits.get(num).getDestination();
            if (r2.nextInt(PERCENTAGE) < SPAWN_CHANCE && possibleLoc.getGround().getDisplayChar() == '~' && !possibleLoc.containsAnActor()) {
                // The location where the actor consumed puddle will be the factory
                ScarabFactory scarabFactory = new ScarabFactory();
                location.setGround(new EnemySpawner<Scarab>(scarabFactory));
                // Spawn scarab at possible location out of all the surrounding puddles
                possibleLoc.addActor(scarabFactory.createGameEntity());
                new Display().print("A scarab spawned!\n");

                // Reset the ground
                location.setGround(this);
            }
        }
        // Set it back to false
        isConsumed = false;

        // Drown the actor if they are drownable
        if (location.containsAnActor() && location.getActor().hasCapability(EntityPassiveAbility.DROWNABLE)) {
            Random r3 = new Random();
            if (r3.nextInt(PERCENTAGE) < DROWN_CHANCE) {
                new Display().print(location.getActor() + " has drowned!" + "\n");
                location.getActor().unconscious(location.map());

            }
        }
    }
}