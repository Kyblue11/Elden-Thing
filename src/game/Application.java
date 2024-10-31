package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.SuspiciousTrader;
import game.actors.attributes.TarnishedActorAttributes;
import game.actors.enemies.DivineBeastDancingLion;
import game.actors.enemies.FurnaceGolem;
import game.actors.Player;
import game.actors.enemies.Timekeeper;
import game.actors.factories.ManFlyFactory;
import game.actors.factories.SpiritFactory;
import game.displays.FancyMessage;
import game.effects.TimeEffect;
import game.positions.grounds.*;
import game.consumables.consumableitems.FlaskOfHealing;
import game.consumables.consumableitems.FlaskOfRejuvenation;
import game.consumables.consumableitems.ShadowtreeFragment;
import game.positions.grounds.spawners.Graveyard;
import game.positions.maps.BeluratSewersMap;
import game.positions.maps.BeluratTowerSettlementMap;
import game.positions.maps.GravesitePlainMap;
import game.positions.maps.StagefrontMap;
import game.state.statefactory.ActorAttributesStateFactory;
import game.state.statefactory.ActorStateFactory;
import game.time.Daytime;
import game.weapons.weaponitems.weaponarts.LifeSteal;
import game.weapons.weaponitems.weaponarts.MementoWeaponArt;
import game.weapons.weaponitems.weaponarts.QuickStep;
import game.weapons.weaponitems.weaponarts.WeaponArt;
import game.weapons.weaponitems.GreatKnife;
import game.weapons.weaponitems.ShortSword;
import game.weapons.weaponitems.WeaponItem;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Aaron Lam Kong Yew
 * Hsu Chyi See
 * Stanley Mah
 * Kim Tae Jun
 *
 */
public class Application {

    /**
     * The main method to run the game/ program.
     * @param args the arguments to start the game
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new PoisonSwamp());

        List<String> gravesitePlainMap = Arrays.asList(
                "..........~~~~~~~...~~~~~~~......~...........",
                "~..........~~~~~....~~~~~~...................",
                "~~.........~~~~.....~~~~~~...................",
                "~~~..#####..~~.....~~~~~~~...................",
                "~~~..#___#........~~~~~~~~~..................",
                "~~~..#___#.......~~~~~~.~~~..................",
                "~~~..##_##......~~~~~~.......................",
                "~~~~...........~~~~~~~...........~~..........",
                "~~~~~.........~~~~~~~~.......~~~~~~~.........",
                "~~~~~~.......~~~~~~~~~~.....~~~~~~~~.........");

        List<String> beluratTowerMap = Arrays.asList(
                "###########........................##########",
                "#____#____#......................._____#____#",
                "#____#_.._#.#...~~~.......~~~....#____#____##",
                "###_~~____###...~~~..~~~..~~~...####______###",
                "###...____###..~~~~..~~~~..~~~...######_____#",
                "##~~###..####..~~~...~~~.....~~~..####..#####",
                "##__.....####..~~~.~~~~~..~~~....#####____###",
                "###..##..##.#..~~..~~~~~..~~~~....####~..####",
                "#....__..__.#..~~..~~~~~~..~~....__~~~~######",
                "###########....................##############");

        List<String> beluratSewersMap = Arrays.asList(
                "##++++++#####++++++++~~~~~++++",
                "##+++++++###+++++++++~~~~~++++",
                "##++++++++++++++++++~~~~~~~++~",
                "###+++++++++++++++.~~~~~~~~.~~",
                "~~~~~.+++++~~~++++~~~~~~~~~..~",
                "~~~~~~~~~~~~~~~++++~~~~+++~...",
                "~~~~+~~~~~~~~~~+++++~~~~~~~###",
                "+~~~~++####~~~~~++++##.~++~###",
                "++~~+++#####~~~~~++###++~~~###",
                "+~~++++######~~~~++###++~~~###");

        List<String> stageFrontMap = Arrays.asList(
                "#################",
                "#~~~..........~~#",
                "#~~~...........~#",
                "#~~.............#",
                "#............~~~#",
                "#..........~~~~~#",
                "#######...#######");

        // Add all maps to the world
        GameMap gravesitePlain = new GravesitePlainMap( groundFactory, gravesitePlainMap);
        world.addGameMap(gravesitePlain);

        GameMap beluratTower = new BeluratTowerSettlementMap(groundFactory, beluratTowerMap);
        world.addGameMap(beluratTower);

        GameMap beluratSewers = new BeluratSewersMap( groundFactory, beluratSewersMap);
        world.addGameMap(beluratSewers);

        GameMap stageFront = new StagefrontMap(groundFactory, stageFrontMap);
        world.addGameMap(stageFront);

        List<GameMap> maps = new ArrayList<>();
        maps.add(gravesitePlain);
        maps.add(beluratTower);
        maps.add(beluratSewers);
        maps.add(stageFront);

        // BEHOLD, ELDEN THING!
        FancyMessage.printTitle();

        // Gravesite Plain
        Player tarnished = new Player("Tarnished", '@', 150);
        world.addPlayer(tarnished, gravesitePlain.at(7, 4));

        FlaskOfHealing flaskOfHealing = new FlaskOfHealing();
        FlaskOfRejuvenation flaskOfRejuvenation = new FlaskOfRejuvenation();

        ShadowtreeFragment stF1 = new ShadowtreeFragment();
        ShadowtreeFragment stF2 = new ShadowtreeFragment();
        ShadowtreeFragment stF3 = new ShadowtreeFragment();
        ShadowtreeFragment stF4 = new ShadowtreeFragment();
        ShadowtreeFragment stF5 = new ShadowtreeFragment();

        // add Furnace Golem
        gravesitePlain.at(42, 4).addActor(new FurnaceGolem());

        // add Suspicious Trader
        gravesitePlain.at(6, 4).addActor(new SuspiciousTrader());

        // add Timekeeper 21,0
        gravesitePlain.at(19,0).addActor(new Timekeeper());

        gravesitePlain.at(6, 5).addItem(flaskOfHealing);
        gravesitePlain.at(8, 5).addItem(flaskOfRejuvenation);

        gravesitePlain.at(16, 4).addItem(stF1);
        gravesitePlain.at(17, 5).addItem(stF2);
        gravesitePlain.at(18, 6).addItem(stF3);
        gravesitePlain.at(19, 7).addItem(stF4);
        gravesitePlain.at(20, 8).addItem(stF5);

        // A2 Req 1
        WeaponArt lifesteal = new LifeSteal();
        WeaponArt quickstep = new QuickStep();

        List<WeaponArt> lifestealOnly = Arrays.asList(lifesteal);
        WeaponItem greatKnifeWithLifeSteal = new GreatKnife(lifestealOnly);
        gravesitePlain.at(9, 9).addItem(greatKnifeWithLifeSteal);
        List<WeaponArt> quickstepOnly = Arrays.asList(quickstep);
        WeaponItem greatKnifeWithQuickstep = new GreatKnife(quickstepOnly);
        gravesitePlain.at(18, 4).addItem(greatKnifeWithQuickstep);
        WeaponItem shortSwordWithQuickStep = new ShortSword(quickstepOnly);
        gravesitePlain.at(19, 4).addItem(shortSwordWithQuickStep);

        //A3 Req 1
        // Choose our enum attributes to be remembered
        List<Enum<?>> attributes = Arrays.asList(BaseActorAttributes.HEALTH, BaseActorAttributes.MANA, TarnishedActorAttributes.STRENGTH);

        // Create factories for each different  type, such as integers, strings, enums or objects.
        ActorStateFactory attributesFactory = new ActorAttributesStateFactory(attributes);

        /* -- Example of another factory in the future --
        ActorStateFactory locationFactory = new ActorLocationStateFactory();
         */

        // Zip them up together in a list, then put them in the MementoWeaponArt
        List<ActorStateFactory> factories = Arrays.asList(attributesFactory);
        WeaponArt mementoSkill = new MementoWeaponArt(factories);
        WeaponItem greatKnifeWithMemento = new GreatKnife(Arrays.asList(mementoSkill));
        gravesitePlain.at(40, 4).addItem(greatKnifeWithMemento);




        // A2 Req 2

        // Add Gate locations
        Location graveSiteGateLocation = gravesitePlain.at(43, 5);
        Location graveSiteToStagefrontGateLocation = gravesitePlain.at(20, 0);
        Location beluratTowerGateLocation = beluratTower.at(2,2);
        Location beluratSewersGateLocation = beluratSewers.at(1,4);
        Location stagefrontArriveLocation = stageFront.at(8,6);

        // add Divine Beast Dancing Lion
        stageFront.at(8,1).addActor(new DivineBeastDancingLion(graveSiteToStagefrontGateLocation));

        // Grave Site
        List<Location> graveSiteLocations = new ArrayList<>();
        graveSiteLocations.add(beluratTowerGateLocation);
        graveSiteLocations.add(beluratSewersGateLocation);
        Gate graveSiteGate = new Gate(graveSiteLocations);
        graveSiteGateLocation.setGround(graveSiteGate);

        // Gate to Stagefront from Grave Site
        List<Location> graveSiteToStagefrontLocations = new ArrayList<>();
        graveSiteToStagefrontLocations.add(stagefrontArriveLocation);
        Gate graveSiteToStagefrontGate = new Gate(graveSiteToStagefrontLocations);
        graveSiteToStagefrontGateLocation.setGround(graveSiteToStagefrontGate);

        // Belurat, Tower Settlement
        List<Location> beluratTowerLocations = new ArrayList<>();
        beluratTowerLocations.add(graveSiteGateLocation);
        Gate towerGate = new Gate(beluratTowerLocations);
        beluratTowerGateLocation.setGround(towerGate);

        // Belurat Sewers
        List<Location> beluratSewersLocations = new ArrayList<>();
        beluratSewersLocations.add(graveSiteGateLocation);
        Gate sewersGate = new Gate(beluratSewersLocations);
        beluratSewersGateLocation.setGround(sewersGate);

        // A2 Req 3

        // Add Graveyard to Belurat Towers
        beluratTower.at(20,0).setGround(new Graveyard(new SpiritFactory()));
        beluratTower.at(20,9).setGround(new Graveyard(new SpiritFactory()));
        beluratTower.at(20,4).setGround(new Graveyard(new SpiritFactory()));

        // Add Graveyard to Belurat Sewers
        beluratSewers.at(4,4).setGround(new Graveyard(new ManFlyFactory()));
        beluratSewers.at(28,5).setGround(new Graveyard(new ManFlyFactory()));
        beluratSewers.at(3,0).setGround(new Graveyard(new ManFlyFactory()));

        // A3 REQ4
        tarnished.addStatusEffect(new TimeEffect(new Daytime(), maps));


        world.run();
    }
}
