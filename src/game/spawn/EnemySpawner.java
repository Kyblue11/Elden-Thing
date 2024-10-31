package game.spawn;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/**
 * A class that represents an enemy spawner on the game map.
 * This spawner can spawn a specified type of SpawnableEnemy at a given location based on a spawn chance.
 * @param <A> the type of SpawnableEnemy this spawner can create
 * @author stanl
 * Modified by Aaron Lam Kong Yew
 */
public class EnemySpawner<A extends SpawnableEnemy> extends Ground implements SpawnCapable {

    /* The factory responsible for creating SpawnableEnemies */
    private SpawnFactory<A> spawnableEnemyFactory;

    /* Default enemy spawner display character */
    private static final char DEFAULT_DISPLAY_CHAR = 'k';

    /* Default enemy spawner name */
    private static final String DEFAULT_SPAWNER_NAME = "Default Spawner";

    /**
     * Constructor for creating an EnemySpawner with a specific display character and name.
     * @param displayChar the character to represent the spawner
     * @param spawnerName the name of the spawner
     * @param spawnableEnemyFactory the factory responsible for creating SpawnableEnemies
     */
    public EnemySpawner(char displayChar, String spawnerName, SpawnFactory<A> spawnableEnemyFactory) {
        super(displayChar, spawnerName);
        this.spawnableEnemyFactory = spawnableEnemyFactory;
    }

    /**
     * Default constructor that initializes the spawner with a default display character and name.
     * @param spawnableEnemyFactory the factory responsible for creating SpawnableEnemies
     */
    public EnemySpawner(SpawnFactory<A> spawnableEnemyFactory) {
        super(DEFAULT_DISPLAY_CHAR, DEFAULT_SPAWNER_NAME);
        this.spawnableEnemyFactory = spawnableEnemyFactory;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        this.spawn(location, this.spawnableEnemyFactory.createGameEntity());
    }

    /**
     * Attempts to spawn an enemy at the specified location based on the spawn chance.
     * @param location the location where the enemy will be spawned
     * @param spawnableEnemy the enemy to spawn
     */
    public void spawn(Location location, SpawnableEnemy spawnableEnemy) {
        if (!location.containsAnActor()) {
            Random random = new Random();
            double randomValue = random.nextDouble();
            if (randomValue <= spawnableEnemy.getSpawnChance()) {
                location.map().addActor(spawnableEnemy, location);
            }
        }
    }
}
