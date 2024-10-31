package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.abilites.EntityPassiveAbility;

/**
 * Action to end the game when the player dies.
 * @author Aaron Lam Kong Yew
 */
public class GameOverAction extends Action {

    /**
     * Invoked when the player dies.
     * @param actor The actor that is dying (in this case, the player)
     * @param map The map that the actor is on
     *  @return "Game Over" String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(EntityPassiveAbility.ENDGAME_CAPABLE)) {
            actor.unconscious(map);
            //System.exit(0);
            return "";
        } else {
            return actor.unconscious(map);
        }
    }

    /**
     * Returns the description of the action.
     * @param actor The actor performing the action
     * @return "End the game" String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "End the game";
    }
}