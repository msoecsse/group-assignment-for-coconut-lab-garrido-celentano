package coconuts;


/**
 * -----------------------------------------------------------------------------
 * Class Name: ScoreboardData
 * Description: Maintains the core game statistics for Oh Coconuts, including the
 *              number of beached coconuts, destroyed coconuts, and the crab's
 *              health. Provides methods to update, retrieve, and modify these
 *              values, and interacts with the Scoreboard class to update the UI.
 * <p>
 * Author:   Dominic Celentano
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public class ScoreboardData {

    /** Number of coconuts that reached the beach. */
    private static int beachedCoconuts = 0;

    /** Number of coconuts destroyed by the crab. */
    private static int destroyedCoconuts = 0;

    /** Health of the crab; minimum value is 0. */
    private static int health = 50;

    // -------------------------------------------------------------------------
    // INSTANCE METHODS
    // -------------------------------------------------------------------------

    /**
     * Updates the linked Scoreboard UI with the current game statistics.
     */
    public void updateScore() {
        Scoreboard.update(beachedCoconuts, destroyedCoconuts, health);
    }

    // -------------------------------------------------------------------------
    // ACCESSORS
    // -------------------------------------------------------------------------

    /**
     * Returns the number of coconuts that reached the beach.
     *
     * @return the number of beached coconuts
     */
    public static int getBeachedCoconuts() {
        return beachedCoconuts;
    }

    /**
     * Returns the number of coconuts destroyed by the crab.
     *
     * @return the number of destroyed coconuts
     */
    public static int getDestroyedCoconuts() {
        return destroyedCoconuts;
    }

    /**
     * Returns the current health of the crab.
     *
     * @return the crab's health
     */
    public static int getHealth() {
        return health;
    }

    // -------------------------------------------------------------------------
    // MUTATORS
    // -------------------------------------------------------------------------

    /**
     * Adjusts the number of beached coconuts by the specified delta.
     *
     * @param deltaBeachedCoconuts the number to add to the current beached coconuts
     */
    public static void changeBeachCoconuts(int deltaBeachedCoconuts) {
        beachedCoconuts += deltaBeachedCoconuts;
    }

    /**
     * Adjusts the number of destroyed coconuts by the specified delta.
     *
     * @param deltaDestroyedCoconuts the number to add to the current destroyed coconuts
     */
    public static void changeDestroyedCoconuts(int deltaDestroyedCoconuts) {
        destroyedCoconuts += deltaDestroyedCoconuts;
    }

    /**
     * Adjusts the crab's health by the specified delta.
     * Ensures that health does not fall below 0.
     *
     * @param deltaHealth the amount to change the health by
     */
    public static void changeHealth(int deltaHealth) {
        if ((health + deltaHealth) <= 0) {
            health = 0;
            return;
        }
        health += deltaHealth;
    }
}
