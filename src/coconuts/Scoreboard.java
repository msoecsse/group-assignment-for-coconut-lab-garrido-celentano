package coconuts;

/**
 * -----------------------------------------------------------------------------
 * Class Name: Scoreboard
 * Description: Provides an interface to update the game UI with the current
 *              statistics, including the number of beached coconuts, destroyed
 *              coconuts, and player health. Interacts with the GameController
 *              to update the labels in the JavaFX view.
 *
 *              The class uses a static reference to the controller to allow
 *              global updates without creating multiple instances.
 *
 * Author:   German Garrido-Lestache Belinchon
 *          garrido-lestachebeli
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public class Scoreboard {

    /** Static reference to the GameController to update the UI. */
    private static GameController controller;

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Creates a new Scoreboard instance linked to a GameController.
     *
     * @param controller the GameController instance managing the UI labels
     */
    public Scoreboard(GameController controller) {
        this.controller = controller;
    }

    // -------------------------------------------------------------------------
    // STATIC METHODS
    // -------------------------------------------------------------------------

    /**
     * Updates the GameController with the current game statistics.
     *
     * @param beachedCoconuts   number of coconuts that reached the beach
     * @param destroyedCoconuts number of coconuts destroyed by the crab
     * @param health            current health of the crab
     */
    public static void update(int beachedCoconuts, int destroyedCoconuts, int health) {
        controller.changeCoconutsBeached(beachedCoconuts);
        controller.changeCoconutsDestroyed(destroyedCoconuts);
        controller.changeHealth(health);
    }

    // -------------------------------------------------------------------------
    // INSTANCE METHODS
    // -------------------------------------------------------------------------

    /**
     * Resets the scoreboard to the default initial values:
     * 0 beached coconuts, 0 destroyed coconuts, and full health (100).
     */
    public void reset() {
        update(0, 0, 100);
    }
}
