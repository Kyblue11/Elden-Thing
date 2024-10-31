package game.positions.maps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;

import java.util.List;

/**
 * A class that represents the Gravesite Plain map.
 * @see edu.monash.fit2099.demo.conwayslife.ConwayGameMap
 * @author Hsu Chyi See
 */
public class GravesitePlainMap extends GameMap {
    /**
     * The name of the map.
     */
    public static final String MAP_NAME = "Gravesite Plain";

    /**
     * Constructor.
     * @param groundFactory
     * @param lines
     */
    public GravesitePlainMap(GroundFactory groundFactory, List<String> lines) {
        super(MAP_NAME, groundFactory, lines);
    }
}
