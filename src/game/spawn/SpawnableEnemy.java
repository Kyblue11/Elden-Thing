package game.spawn;

import game.actors.enemies.Enemy;

/**
 * Abstract class representing an enemy that can spawn with a given chance.
 * This class provides the structure for spawnable enemies with methods to get and set the spawn chance.
 * @author stanl
 */
public abstract class SpawnableEnemy extends Enemy {

    /**
     * The chance that this enemy will spawn
     */
    private double spawnChance;

    /**
     * The default spawn chance for enemies, might change
     */
    public double defaultSpawnChance = 0;

    /**
     * Constructor for the SpawnableEnemy class.
     * Initializes the spawnable enemy with a name, display character, and hit points.
     * Sets the default spawn chance to 0.
     *
     * @param name the name of the enemy
     * @param displayChar the character representing the enemy on the map
     * @param hitPoints the health points of the enemy
     */
    public SpawnableEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.spawnChance = defaultSpawnChance;
    }

    /**
     * Gets the spawn chance of the enemy.
     *
     * @return the spawn chance
     */
    public double getSpawnChance() {
        return this.spawnChance;
    }

    /**
     * Sets the spawn chance of the enemy.
     *
     * @param spawnChance the new spawn chance
     */
    public void setSpawnChance(double spawnChance) {
        this.spawnChance = spawnChance;
    }
}
