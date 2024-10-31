package game.actors.factories;

import game.spawn.SpawnFactory;
import game.actors.enemies.ManFly;

/**
 * Factory class for creating instances of the ManFly enemy.
 * Implements the SpawnFactory interface to spawn ManFly enemies.
 * @author stanl
 */
public class ManFlyFactory implements SpawnFactory<ManFly> {

    /**
     * Creates and returns a new instance of the ManFly enemy.
     *
     * @return a new ManFly instance
     */
    public ManFly createGameEntity() {
        return new ManFly();
    }
}
