package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityDamageAbility;
import game.abilites.EntityPassiveAbility;
import game.actions.AttackAction;
import game.actions.ConsumeAction;
import game.actors.attributes.Status;
import game.consumables.Consumable;
import game.consumables.consumableitems.CrimsonTear;
import game.effects.ExplosionEffect;
import game.effects.ScarabEffect;
import game.positions.LocationUtils;
import game.spawn.SpawnableEnemy;

import java.util.List;

/**
 * Class representing the Scarab.
 * Scarab has an explosion effect and can be consumed to provide buffs to the player.
 * It also drops a Crimson Tear item upon its demise.
 * Created by: Kim Tae Jun
 * @author Kim Tae Jun
 * Modified by: Aaron Lam Kong Yew
 */
public class Scarab extends SpawnableEnemy implements Consumable {

    /**
     * Explosion effect that deals damage to surrounding actors
     */
    private ExplosionEffect explosionEffect;

    /**
     * Boolean to track if the Scarab has been consumed
     */
    private boolean isConsumed = false;

    /**
     * The name of the Scarab
     */
    private static final String NAME = "Scarab";

    /**
     * The display character of the Scarab
     */
    private static final char CHAR = 'b';

    /**
     * The health points of the Scarab
     */
    private static final int HP = 25;

    /**
     * Constructor for Scarab.
     * Initializes the Scarab with its name, display character, and health points.
     */
    public Scarab(ExplosionEffect explosionEffect) {
        super(NAME, CHAR, HP);
        this.addCapability(EntityDamageAbility.EXPLOSION);
        this.addCapability(EntityPassiveAbility.POISON_RESISTANCE);
        this.explosionEffect = explosionEffect;
    }

    /**
     * Defines the behaviour of Scarab on each turn.
     * If consumed, it is removed from the map. Otherwise, it performs its behaviour actions.
     *
     * @param actions available actions for the Scarab
     * @param lastAction the last action performed by the Scarab
     * @param map the current game map
     * @param display the display to output game messages
     * @return the next action for the Scarab to perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.isConsumed) {
            this.hurt(this.getAttribute(BaseActorAttributes.HEALTH));
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Marks the Scarab as consumed, providing the consuming actor with a buff.
     *
     * @param actor the actor consuming the Scarab
     * @return true if the Scarab is successfully consumed
     */
    @Override
    public boolean consume(Actor actor) {
        this.isConsumed = true;
        actor.addStatusEffect(new ScarabEffect());
        return true;
    }

    /**
     * Handles the Scarab's unconscious state, causing an explosion effect and dropping a Crimson Tear.
     *
     * @param actor the actor that defeated the Scarab
     * @param map the current game map
     * @return a message indicating the Scarab's demise
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        String result = "";
        List<Location> surroundingLocations = LocationUtils.getSurroundingLocations(map.locationOf(this));

        result +=  this.explosionEffect.applyEffect(this, surroundingLocations, map);
        map.locationOf(this).addItem(new CrimsonTear());
        result += super.unconscious(actor, map);
        return result;
    }

    /**
     * Defines the allowable actions other actors can perform on the Scarab.
     * Actors can either consume or attack the Scarab if they are hostile to it.
     *
     * @param otherActor the actor interacting with the Scarab
     * @param direction the direction of the interaction
     * @param map the current game map
     * @return the list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}
