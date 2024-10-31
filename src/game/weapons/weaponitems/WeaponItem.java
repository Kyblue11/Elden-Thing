package game.weapons.weaponitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.attributes.Status;
import game.actors.attributes.TarnishedActorAttributes;
import game.actions.AttackAction;
import game.effects.TimeEffect;
import game.weapons.weaponitems.weaponarts.WeaponArt;

import java.util.List;
import java.util.Random;

/**
 * Class representing items that can be used as a weapon.
 * @author Adrian Kristanto
 * Modified by Aaron Lam Kong Yew
 */
public abstract class WeaponItem extends Item implements Weapon {

    /** Default damage multiplier */
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;

    /**
     * The percentage value
     */
    private static final int PERCENTAGE = 100;

    /** Damage of the weapon */
    private final int damage;

    /** Hit rate of the weapon */
    private final int hitRate;

    /** Verb to use for this weapon */
    private final String verb;

    /** Damage multiplier of the current weapon */
    private float damageMultiplier;

    /** Added: Required strength to wield the weapon */
    private final int requiredStrength;

    /** A2 Req1: Potential abilities of the weapon (can exceed one */
    private List<WeaponArt> weaponArts;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate, int requiredStrength) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
        this.requiredStrength = requiredStrength;
    }

    /**
     * Constructor, with weapon arts.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     * @param requiredStrength the required strength to wield the weapon
     * @param weaponArts the potential abilities of the weapon
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate, int requiredStrength, List<WeaponArt> weaponArts) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
        this.requiredStrength = requiredStrength;
        this.weaponArts = weaponArts;
    }

    /**
     *  Retrieves the required strength to pick up the weapon.
     * @return requiredStrength of the weapon, as an integer
     */
    public int getRequiredStrength() {
        return requiredStrength;
    }

    /**
     * Overrides the default getPickUpAction of the Item class
     * Compares the strength of the actor with the required strength of the weapon
     * @param actor the actor that wants to pick up the weapon
     * @return a PickUpAction if the actor has the required strength to pick up the weapon, null otherwise
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
            if ( actor.hasAttribute(TarnishedActorAttributes.STRENGTH)
                     && actor.getAttribute(TarnishedActorAttributes.STRENGTH) >= this.getRequiredStrength()) {
                return super.getPickUpAction(actor);
            }
        return null;
    }

    /**
     * A weapon can be used to attack an actor so, I call AttackAction!
     * @param otherActor the actor to be attacked
     * @param location the location of the actor
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (!otherActor.hasCapability(Status.TRADER)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }

        return actions;
    }

    /**
     * Implements the attack method from the Weapon interface.
     * Attack the target actor with the weapon.
     * May also execute the weapon arts associated with the weapon.
     * @return A string representing the result of the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        String result = "";

        // Execute weapon arts
        if (weaponArts != null) {
            for (WeaponArt weaponArt : weaponArts) {
                result += weaponArt.execute(attacker, target, map);
            }
        }

        Random rand = new Random();
        if (!(rand.nextInt(PERCENTAGE) < this.hitRate)) {
            return result + attacker + " misses " + target + ".";
        }



        target.hurt(Math.round(damage * damageMultiplier));

        result +=  String.format("%s %s %s for %d damage. ", attacker, verb, target, damage * Math.round(damageMultiplier));


        return result;
    }

    /**
     * Updates the damage multiplier of the weapon.
     * @param multiplier
     */
    public void updateDamageMultiplier(float multiplier) {
        this.damageMultiplier = multiplier;
    }

}
