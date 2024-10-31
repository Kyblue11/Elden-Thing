package game.positions.maps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;

import java.util.List;

/**
 * Represents the Stagefront map in the game.
 * <p>
 * The {@code StagefrontMap} is a specific instance of {@link GameMap} and
 * corresponds to the "Stagefront" area of the game world. It initializes
 * with a {@link GroundFactory} and a list of strings representing the layout of
 * the map.
 * </p>
 * <p>
 * The map is defined by {@code lines} which describe the map layout, and the
 * {@code GroundFactory} is used to generate the specific ground elements
 * (like floor, obstacles, etc.) on this map.
 * </p>
 *
 * @see GameMap
 * @see GroundFactory
 * @see List
 *
 * @author stanl
 */
public class StagefrontMap extends GameMap {

    /**
     * The constant name of this map, representing the "Stagefront" area.
     */
    private static final String MAP_NAME = "Stagefront";

    /**
     * Constructor to create a new Stagefront map with the specified layout and ground factory.
     *
     * @param groundFactory the {@link GroundFactory} that produces the ground types for the map
     * @param lines         the list of strings that represent the map layout
     */
    public StagefrontMap(GroundFactory groundFactory, List<String> lines) {
        super(MAP_NAME, groundFactory, lines);
    }
}
