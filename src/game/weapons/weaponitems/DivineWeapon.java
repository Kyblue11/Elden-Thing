package game.weapons.weaponitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.divinePowers.DivinePower;
import game.divinePowers.Frost;
import game.divinePowers.Lightning;
import game.divinePowers.Wind;
import game.effects.TimeEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Abstract class for a weapon that has divine powers.
 * Any weapon that wants to implement divine power logic can extend this class.
 * Provides the logic for divine power transitions and special attacks.
 * The weapon randomly switches between different divine powers (Wind, Frost, Lightning),
 * adding unique effects to its attacks based on predefined probabilities.
 *
 * Divine powers are switched during attacks, and the associated power's effects
 * are applied to both the attacker and the target.
 *
 * This class uses a probability-based system to determine the transition between
 * divine powers.
 *
 * @author stanl
 */
public class DivineWeapon extends WeaponItem {
    /** Default damage multiplier */
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;

    /** Damage multiplier during night time */
    private static final float NIGHT_DAMAGE_MULTIPLIER = 2.0f;

    /** The chance (percentage) that the weapon will switch divine powers on attack. */
    private static final int DIVINE_POWER_SWITCH_CHANCE = 25;

    /** The current divine power being used by the weapon. */
    protected DivinePower currentDivinePower;

    /** A mapping of divine powers to possible next divine powers and their transition chances. */
    protected Map<DivinePower, Map<DivinePower, Integer>> divinePowers;

    /** Random number generator for chance-based logic. */
    private Random random = new Random();

    /** Constant reference for Wind divine power. */
    protected static final DivinePower WIND = new Wind();

    /** Constant reference for Frost divine power. */
    protected static final DivinePower FROST = new Frost();

    /** Constant reference for Lightning divine power. */
    protected static final DivinePower LIGHTNING = new Lightning();

    /** Constant 0 used for cumulative probability */
    private static final int ZERO = 0;

    /** switching probability for wind to frost */
    private static final int WIND_TO_FROST_PROB = 30;

    /** switching probability for wind to lightning */
    private static final int WIND_TO_LIGHTNING_PROB = 70;

    /** switching probability for frost to wind */
    private static final int FROST_TO_WIND_PROB = 100;

    /** switching probability for lightning to frost */
    private static final int LIGHTNING_TO_FROST_PROB = 40;

    /** switching probability for lightning to wind */
    private static final int LIGHTNING_TO_WIND_PROB = 40;

    /** switching probability for lightning to lightning */
    private static final int LIGHTNING_TO_LIGHTNING_PROB = 20;

    /**
     * The percentage value
     */
    private static final int PERCENTAGE = 100;

    /**
     * Constructor for the DivineWeapon.
     *
     * @param name            name of the item
     * @param displayChar     character to display the item
     * @param damage          base damage of the weapon
     * @param verb            action verb for the weapon (e.g., "hits")
     * @param hitRate         hit rate percentage (chance to hit)
     * @param requiredStrength required strength to wield the weapon
     */
    public DivineWeapon(String name, char displayChar, int damage, String verb, int hitRate, int requiredStrength) {
        super(name, displayChar, damage, verb, hitRate, requiredStrength);
        this.currentDivinePower = WIND;
        this.divinePowers = new HashMap<>();
        setupDivinePowerTransitions();
    }

    /**
     * Set up the transitions between divine powers with their probabilities.
     * Specifies how divine powers transition between each other, such as Wind transitioning
     * to Frost or Lightning, based on predefined percentages.
     */
    protected void setupDivinePowerTransitions() {
        // WIND transitions
        addDivinePowerTransition(WIND, FROST, WIND_TO_FROST_PROB);
        addDivinePowerTransition(WIND, LIGHTNING, WIND_TO_LIGHTNING_PROB);

        // FROST transitions
        addDivinePowerTransition(FROST, WIND, FROST_TO_WIND_PROB);

        // LIGHTNING transitions
        addDivinePowerTransition(LIGHTNING, FROST, LIGHTNING_TO_FROST_PROB);
        addDivinePowerTransition(LIGHTNING, WIND, LIGHTNING_TO_WIND_PROB);
        addDivinePowerTransition(LIGHTNING, LIGHTNING, LIGHTNING_TO_LIGHTNING_PROB);
    }

    /**
     * Adds a divine power transition with a specified probability.
     *
     * @param currentPower the current divine power
     * @param nextPower    the next divine power to transition to
     * @param chance       the probability of transitioning to the next power
     */
    protected void addDivinePowerTransition(DivinePower currentPower, DivinePower nextPower, int chance) {
        if (!this.divinePowers.containsKey(currentPower)) {
            this.divinePowers.put(currentPower, new HashMap<>());
        }
        this.divinePowers.get(currentPower).put(nextPower, chance);
    }

    /**
     * Switches the current divine power based on predefined transition probabilities.
     * This method evaluates the current power and transitions to a new one based on
     * a random chance derived from the predefined probabilities.
     */
    protected void switchDivinePower() {
        Map<DivinePower, Integer> transitions = divinePowers.get(currentDivinePower);
        if (transitions != null && !transitions.isEmpty()) {
            int selectedProb = random.nextInt(PERCENTAGE);
            int cumulativeProb = ZERO;

            for (Map.Entry<DivinePower, Integer> entry : transitions.entrySet()) {
                cumulativeProb += entry.getValue();
                if (selectedProb < cumulativeProb) {
                    DivinePower newDivinePower = entry.getKey();
                    if (newDivinePower != this.currentDivinePower) {
                        new Display().println("Divine Power switched from "+this.currentDivinePower.getDivinePowerName()+" to "+newDivinePower.getDivinePowerName());
                        this.currentDivinePower = newDivinePower;
                    }
                    return;
                }
            }
        }
    }

    /**
     * Executes an attack on a target, optionally switching divine powers beforehand.
     * The attack triggers the current divine power's effect and performs a normal attack.
     * Essentially the "special attack" of Divine Power
     *
     * @param attacker the actor performing the attack
     * @param target   the actor being attacked
     * @param map      the game map the actors are on
     * @return a string describing the outcome of the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {

        if (random.nextInt(PERCENTAGE) < DIVINE_POWER_SWITCH_CHANCE) {
            switchDivinePower();
        }

        String result = currentDivinePower.useDivinePower(attacker, target, map);

        if (TimeEffect.isDawn()) {
            updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);
        } else if (TimeEffect.isSunset()) {
            updateDamageMultiplier(NIGHT_DAMAGE_MULTIPLIER);
        }

        result += super.attack(attacker, target, map);

        return result;
    }
}
