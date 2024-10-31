package game.spawn;

import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface that defines the capability to spawn enemies at a given location.
 * Classes implementing this interface should provide the logic to spawn a
 * {@link SpawnableEnemy} in a specific location.
 *
 * @author stanl
 */
public interface SpawnCapable {
    /**
     * Spawns an enemy at the specified location.
     *
     * @param location the location where the enemy will be spawned
     * @param enemy    the enemy to be spawned
     */
    public void spawn(Location location, SpawnableEnemy enemy);
}
