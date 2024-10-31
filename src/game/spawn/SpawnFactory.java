package game.spawn;

import edu.monash.fit2099.engine.GameEntity;

/**
 * An interface that defines a factory for creating game entities.
 *
 * @param <A> the type of game entity that this factory creates
 * @author stanl
 */
public interface SpawnFactory<A extends GameEntity> {
    /**
     * Creates a new game entity.
     *
     * @return a new instance of the game entity
     */
    A createGameEntity();
}
