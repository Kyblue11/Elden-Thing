package game.actors.factories;

import game.spawn.SpawnFactory;
import game.actors.enemies.Scarab;
import game.effects.ExplosionEffect;

/**
 * Factory class for creating instances of the Scarab enemy.
 * Implements the SpawnFactory interface to spawn Scarab enemies.
 * @author Kim Tae Jun
 */
public class ScarabFactory implements SpawnFactory<Scarab> {
    /**
     * The damage dealt by the explosion effect of the Scarab.
     */
    private static final int EXPLOSION_DAMAGE = 25;

    /**
     * Creates and returns a new instance of the Scarab enemy.
     *
     * @return a new Scarab instance
     */
    public Scarab createGameEntity() {
        ExplosionEffect explosionEffect = new ExplosionEffect(EXPLOSION_DAMAGE);
        return new Scarab(explosionEffect);
    }
}
