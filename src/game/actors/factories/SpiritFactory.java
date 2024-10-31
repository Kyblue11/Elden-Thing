package game.actors.factories;

import game.spawn.SpawnFactory;
import game.actors.enemies.Spirit;

/**
 * Factory class for creating instances of the Spirit enemy.
 * Implements the SpawnFactory interface to spawn Spirit enemies.
 * @author stanl
 */
public class SpiritFactory implements SpawnFactory<Spirit> {

    /**
     * Creates and returns a new instance of the Spirit enemy.
     *
     * @return a new Spirit instance
     */
    public Spirit createGameEntity() {
        return new Spirit();
    }
}
