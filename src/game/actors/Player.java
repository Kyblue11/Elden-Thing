package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.abilites.EntityPassiveAbility;
import game.displays.FancyMessage;
import game.weapons.intristicweapons.BareFist;
import game.actors.attributes.Status;
import game.actors.attributes.TarnishedActorAttributes;
import game.abilites.TraversableAbility;
import  game.actions.GameOverAction;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Aaron Lam Kong Yew
 *
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(TraversableAbility.FLOOR_TRAVERSABLE);
        this.setIntrinsicWeapon(new BareFist());
        this.addAttribute(BaseActorAttributes.MANA, new BaseActorAttribute(100));
        this.addAttribute(TarnishedActorAttributes.STRENGTH, new BaseActorAttribute(5));
        this.addCapability(EntityPassiveAbility.ENDGAME_CAPABLE);
    }

    /**
     * Player's turn in World
     * @param actions the actions that the player can perform
     * @param lastAction the last action that the player performed
     * @param map the map that the player is on
     * @param display the display that shows the game
     * @return the action that the player will perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Check if the player is conscious every turn, even when spawned
        if (!this.isConscious()) {
            return new GameOverAction();
        }

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);

        display.println(super.toString());
        display.println("Mana: (" + this.getAttribute(BaseActorAttributes.MANA) + "/" + this.getAttributeMaximum(BaseActorAttributes.MANA) + ")");
        display.println("Strength: (" + this.getAttribute(TarnishedActorAttributes.STRENGTH) + ")");

        return menu.showMenu(this, display);
    }

    /**
     * Method to handle the player's unconscious state
     * @param map the map that the player is on
     * @return the message to be displayed
     */
    @Override
    public String unconscious(GameMap map) {
        FancyMessage.printYouDied();
        return super.unconscious(map);
    }

    /**
     * Method to handle the player's unconscious state when defeated by an actor
     * @param actor the actor that defeated the player
     * @param map the map that the player is on
     * @return the message to be displayed
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        FancyMessage.printYouDied();
        return super.unconscious(actor, map);
    }
}
