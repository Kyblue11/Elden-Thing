package game.positions.grounds;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityPassiveAbility;
import game.actions.MovePlayerAction;

import java.util.List;

/**
 * A class that represents a gate that can transport actors to different maps
 * The gate is not portable and can only be moved by the game
 * @see Ground
 * @author Hsu Chyi See
 * Modified by: Aaron Lam Kong Yew
 */
public class Gate extends Ground {
    /**
     * The list of locations that the gate can transport actors to
     */
    private List<Location> locations;

    /**
     * The display character of the gate.
     */
    private static final char DISPLAY_CHAR = 'H';

    /**
     * The name of the gate.
     */
    private static final String NAME = "Gate";


    /**
     * Constructor.
     */
    public Gate(List<Location> locations) {
        super(DISPLAY_CHAR, NAME);
        this.locations = locations;
        this.addCapability(EntityPassiveAbility.FIRE_RESISTANCE);
    }


    /**
     * Add a location to the list of locations that the gate can transport actors to
     * @param location the location to be added
     */
    public void addLocation(Location location) {
        locations.add(location);
    }


    /**
     *  A list of Actions that can be done by the Gate
     *  invokes the super class allowableActions method to get the default actions
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        for (int i = 0; i < locations.size(); i++) {
            actions.add(new MovePlayerAction(locations.get(i), "to the " + locations.get(i).map().toString()));
        }

        return actions;
    }

}
