package game.weapons.powerweapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityDamageAbility;
import game.abilites.EntityPassiveAbility;
import game.actions.StompAction;
import game.actors.attributes.Status;
import game.weapons.weaponitems.StompWeapon;

/**
 * Class representing the Furnace Golem's capabilities as well as its attacks
 * This class includes the explosion and fire ring effects as well as giving the actor fire resistance status
 * This item can be dropped and picked up where it allows the actor to use StompAction
 *
 * @author Kim Tae Jun
 */
public class FurnaceEngine extends StompWeapon {

    /**
     * The name of the Furnace Engine weapon.
     */
    private static final String NAME = "Furnace Engine";

    /**
     * Character representing the Furnace Engine weapon.
     */
    private static final char DISPLAY_CHAR = 'E';

    /**
     * The base damage dealt by this weapon.
     */
    private static final int DAMAGE = 100;

    /**
     * The verb used to describe the attack action.
     */
    private static final String VERB = "stomps";

    /**
     * The hit chance percentage for the attack.
     */
    private static final int HIT_CHANCE = 5;

    /**
     * Constructor for the FurnaceEngine weapon.
     * Initializes the weapon with the name, display character, damage,
     * attack verb, hit chance, and no required strength.
     */
    public FurnaceEngine() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_CHANCE, 0);
    }

    /**
     * A weapon can be used to attack an actor so, I call AttackAction!
     * @param owner the actor that has this weapon
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = super.allowableActions(owner);
        // Owner gains capabilities of FurnaceGolem
        owner.addCapability(EntityDamageAbility.EXPLOSION);
        owner.addCapability(EntityDamageAbility.FIRE_RING);
        owner.addCapability(EntityPassiveAbility.FIRE_RESISTANCE);
        return actions;
    }

    /**
     * A weapon can be used to attack an actor so, I call AttackAction!
     * @param otherActor the actor to be attacked
     * @param location the location of the actor
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor);
        GameMap map = location.map();
        if (!otherActor.hasCapability(Status.TRADER)){
            // StompAction where the weapon is FurnaceEngine
            actions.add(new StompAction(otherActor, map.locationOf(otherActor).toString(), this));
        }
        return actions;
    }
}
