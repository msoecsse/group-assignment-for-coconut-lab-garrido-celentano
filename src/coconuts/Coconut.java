package coconuts;

import javafx.scene.image.Image;

/**
 * -----------------------------------------------------------------------------
 * Class Name: Coconut
 * Description: Represents a falling coconut object in the Oh Coconuts game.
 *              Coconuts fall from the sky and can interact with other game
 *              entities such as crabs and lasers. If hit by a laser, the
 *              coconut disappears; if it reaches the beach, it may affect the
 *              game score.
 *              <p>
 *              Note: This is a domain class; aside from the Image reference,
 *              no JavaFX or GUI logic should be introduced here.
 *              </p>
 *
 * @author  German Garrido-Lestache Belinchon
 *          garrido-lestachebeli
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public class Coconut extends HittableIslandObject implements Observer {

    // -------------------------------------------------------------------------
    // CONSTANTS
    // -------------------------------------------------------------------------

    /** The visual width (in pixels) of the coconut. */
    private static final int WIDTH = 50;

    /** The image representation of the coconut used for rendering in the game. */
    private static final Image coconutImage = new Image("file:images/coco-1.png");


    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Constructs a new Coconut instance positioned at the given horizontal
     * coordinate. The coconut starts from the top of the game screen and
     * falls down with each game tick.
     *
     * @param game reference to the game manager that controls overall game logic
     * @param x    the horizontal starting position of the coconut
     */
    public Coconut(OhCoconutsGameManager game, int x) {
        super(game, x, 0, WIDTH, coconutImage); // Initializes coconut with width and image
    }


    // -------------------------------------------------------------------------
    // METHODS
    // -------------------------------------------------------------------------

    /**
     * Updates the coconut's position on each game tick.
     * This simulates the coconut falling by incrementing its y-coordinate.
     */
    @Override
    public void step() {
        y += 5; // Moves the coconut downward by 5 pixels per tick
    }

    /**
     * Deletes the coconut from the game.
     * <p>
     * Currently unimplemented — intended to handle the removal of the
     * coconut from the active game objects (e.g., when hit by a laser or
     * after reaching the beach).
     * </p>
     */
    private void delete() {
        // Future implementation: remove coconut from game state
    }

    /**
     * Updates the player's score when the coconut reaches a scoring event.
     *
     * @return true if the score was successfully changed; false otherwise
     */
    private boolean changeScore() {
        return false; // Placeholder logic for score adjustment
    }
}
