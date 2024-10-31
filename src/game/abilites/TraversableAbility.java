package game.abilites;

/**
 * This enum is used to represent  whether a non-stationary actor  can traverse a certain type of ground.
 * Example #1: if our player is capable of traversing floors, attach GroundCapabilities.FLOORTRAVERSABLE to the player class
 * @author Aaron Lam Kong Yew
 */
public enum TraversableAbility {
    FLOOR_TRAVERSABLE,
    WALL_TRAVERSABLE,
}
