package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the collection of actors in the game.'
 * @Author Hsu Chyi See
 */
public class ActorCollector {


    /**
     * Collect all actors in a specific game map.
     * @param gameMap
     * @return
     */
    public static List<Actor> collectAllActors(GameMap gameMap) {
        List<Actor> allActors = new ArrayList<>();

        for (int x : gameMap.getXRange()) {
            for (int y : gameMap.getYRange()) {
                Location location = gameMap.at(x, y);
                Actor actor = gameMap.getActorAt(location);
                if (actor != null && !allActors.contains(actor)) {
                    allActors.add(actor);
                }
            }
        }
        return allActors;
    }

    /**
     * Collect all actors in the game maps.
     * @param gameMaps
     * @return
     */
    public static List<Actor> collectAllActors(List<GameMap> gameMaps) {
        List<Actor> allActors = new ArrayList<>();

        // Iterate over all game maps
        for (GameMap gameMap : gameMaps) {
            for (int x : gameMap.getXRange()) {
                for (int y : gameMap.getYRange()) {
                    Location location = gameMap.at(x, y);
                    Actor actor = gameMap.getActorAt(location);
                    if (actor != null && !allActors.contains(actor)) {
                        allActors.add(actor);
                    }
                }
            }
        }
        return allActors;
    }
}
