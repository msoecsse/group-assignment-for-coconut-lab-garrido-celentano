package coconuts;

import javafx.scene.image.Image;

/**
 * -----------------------------------------------------------------------------
 * Class Name: Crab
 * Description: Represents the player-controlled or game-controlled crab that
 *              shoots down falling coconuts. The crab can also be hit by a
 *              falling coconut, which ends the game.
 *              <p>
 *              Note: This is a domain class and should not include any JavaFX
 *              or GUI-related functionality beyond the use of the Image object
 *              for visual representation.
 *              </p>
 *
 * @author  Dominic Celentano
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public class Crab extends HittableIslandObject {

    // -------------------------------------------------------------------------
    // CONSTANTS
    // -------------------------------------------------------------------------

    /** The width (and assumed height) of the crab image in pixels. */
    private static final int WIDTH = 50;

    /** The image asset used to visually represent the crab in the game. */
    private static final Image crabImage = new Image("file:images/crab-1.png");

    /** Reference to the game manager controlling the overall game logic. */
    private static OhCoconutsGameManager game;


    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Constructs a new Crab instance placed at the center of the island's width.
     * The crab is positioned at the bottom of the sky height boundary.
     *
     * @param game         reference to the game manager that handles all game logic
     * @param skyHeight    the vertical coordinate marking the top of the beach
     * @param islandWidth  the total width of the island, used to center the crab
     */
    public Crab(OhCoconutsGameManager game, int skyHeight, int islandWidth) {
        super(game, islandWidth / 2, skyHeight, WIDTH, crabImage); // Initialize crab position and image
        this.game = game;
    }


    // -------------------------------------------------------------------------
    // METHODS
    // -------------------------------------------------------------------------

    /**
     * Updates the crab's state during each game tick.
     * <p>
     * The crab currently has no automatic movement or timed behavior, so this
     * method intentionally performs no actions.
     * </p>
     */
    @Override
    public void step() {
        // No behavior on step — crab movement is controlled manually
    }

    /**
     * Moves the crab horizontally by the specified offset.
     * <p>
     * The method ensures that the crab remains within the island's boundaries
     * and updates its displayed position after moving.
     * </p>
     *
     * @param offset the horizontal movement distance (positive = right, negative = left)
     */
    public void crawl(int offset) {
        // Prevent movement if it would cause the crab to go out of bounds
        if ((x + offset) + WIDTH > game.getWidth() || x + offset < 0) {
            return; // Ignore move if outside playable area
        }

        x += offset; // Apply horizontal movement
        display();   // Update the crab’s visual position
    }
}
