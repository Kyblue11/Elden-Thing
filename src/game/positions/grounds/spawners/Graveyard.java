package game.positions.grounds.spawners;

import game.spawn.EnemySpawner;
import game.spawn.SpawnFactory;

/**
 * A class that represents a Graveyard, which is a type of enemy spawner.
 * The Graveyard spawns enemies at a specified location using a given SpawnFactory.
 * This spawner is represented by the character 'n' on the game map.
 *
 * @author stanl
 */
public class Graveyard extends EnemySpawner {

    // Character representing the Graveyard on the game map
    private static final char DISPLAY_CHAR = 'n';

    // Name of the spawner
    private static final String NAME = "Graveyard";

    /**
     * Constructor for creating a Graveyard spawner.
     *
     * @param spawnFactory the factory responsible for creating enemies to spawn
     */
    public Graveyard(SpawnFactory spawnFactory) {
        super(DISPLAY_CHAR, NAME, spawnFactory);
    }
}
