package game.consumables.consumableitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.consumables.Consumable;
import game.effects.TimeEffect;

/**
 * An abstract class that represents a consumable item.
 * A consumable item is a concrete item that can be consumed by an actor.
 * @author Aaron Lam Kong Yew
 */
public abstract class ConsumableItem extends Item implements Consumable {

    /**
     * The number of charges the consumable item has.
     * 1 charge indicates that the item can be consumed only once.
     */
    protected int charges;

    /**
     * Constructor.
     * @param name the name of the consumable item
     * @param displayChar the character that will represent the consumable item
     * @param portable whether the consumable item is portable
     * @param charges the number of charges the consumable item has
     */
    public ConsumableItem(String name, char displayChar, boolean portable, int charges) {
        super(name, displayChar, portable);
        // Halved as daytime multiplies charges by 2, so we need to divide by 2 to get the original charges
        this.charges = charges;
    }

    /**
     * Only has the ConsumeAction as an allowable action, for now.
     * @return ConsumeAction
     */
    @Override
    public ActionList allowableActions(Actor otherActor) {
        ActionList actions = super.allowableActions(otherActor);
        actions.add(new ConsumeAction(this));
        new Display().println("Charges left: " + getCharges());

        return actions;
    }

    /**
     * Apply the effects of the consumable item.
     */
    public int getCharges() {
        int i = charges;
        return i;
    }

}