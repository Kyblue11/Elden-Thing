package game.tradables;

import edu.monash.fit2099.engine.actors.Actor;
/**
 * An interface that represents anything that can be traded.
 * Trading the entity will have an effect on the actor.
 * @author Kim Tae Jun
 */
public interface Tradable {

    /**
     * Trades the entity.
     * @param actor the actor consuming the entity
     * @return true if the entity is able to trade, false otherwise
     */
    boolean trade(Actor actor);
}
