package game.positions.maps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;

import java.util.List;

/**
 * A class that represents the Belurat Tower Settlement map.
 * @see edu.monash.fit2099.demo.conwayslife.ConwayGameMap
 * @author Hsu Chyi See
 */
public class BeluratTowerSettlementMap extends GameMap {
    /**
     * The name of the map.
     */
    public static final String MAP_NAME = "Belurat Tower Settlement";

    /**
     * Constructor.
     * @param groundFactory the factory that creates Ground objects
     * @param lines the list of strings that represents the map
     */
    public BeluratTowerSettlementMap(GroundFactory groundFactory, List<String> lines) {
        super(MAP_NAME, groundFactory, lines);
    }


}
