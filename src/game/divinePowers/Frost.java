package game.divinePowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityPassiveAbility;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the Frost divine power.
 * This divine power causes the target to slip and drop all items if they are on a puddle of water.
 *
 * @author stanl
 */
public class Frost extends DivinePower {

    /**
     * Constructs a Frost divine power with the name "Frost".
     */
    public Frost() {
        super("Frost");
    }

    /**
     * Uses the Frost divine power on a target actor.
     * If the target is on a puddle of water, all items in their inventory are dropped.
     *
     * @param attacker the actor using the divine power
     * @param target   the actor being targeted by the divine power
     * @param map      the current game map
     * @return a message describing the outcome of using the divine power
     */
    @Override
    public String useDivinePower(Actor attacker, Actor target, GameMap map) {
        Location locationOfTarget = map.locationOf(target);
        Ground groundOfLocationOfTarget = locationOfTarget.getGround();
        Display display = new Display();

        List<Item> itemsToDrop = new ArrayList<>(target.getItemInventory());

        if (groundOfLocationOfTarget.hasCapability(EntityPassiveAbility.PUDDLE_OF_WATER)) {
            itemsToDrop.forEach(item -> {
                display.println("item dropped --> " + item);
                target.removeItemFromInventory(item);
                locationOfTarget.addItem(item);
            });
            return attacker + " used " + getDivinePowerName() + " causing " + target + " to slip and drop all their items.\n";
        } else {
            return attacker + " used " + getDivinePowerName() + " but it had no effect because " + target + " is not on water.\n";
        }
    }
}
