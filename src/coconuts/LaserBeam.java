package coconuts;

import javafx.scene.image.Image;

/**
 * -----------------------------------------------------------------------------
 * Class Name: LaserBeam
 * Description: Represents a laser beam shot by the crab in the Oh Coconuts game.
 *              The laser moves upward and can hit only falling objects such as
 *              coconuts. This class extends HittableIslandObject.
 *              <p>
 *              Domain class: should not include GUI logic beyond the Image object.
 *              </p>
 *
 * @author  Dominic Celentano
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public class LaserBeam extends HittableIslandObject {

    // -------------------------------------------------------------------------
    // CONSTANTS
    // -------------------------------------------------------------------------

    /** The width of the laser beam in pixels (update with image size if needed). */
    private static final int WIDTH = 10;

    /** The image representing the laser beam visually. */
    private static final Image laserImage = new Image("file:images/laser-1.png");


    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Constructs a new LaserBeam starting at the crab's eye height and horizontal
     * center.
     *
     * @param game        reference to the game manager controlling game logic
     * @param eyeHeight   vertical coordinate representing the crab's shooting point
     * @param crabCenterX horizontal coordinate representing the crab's center
     */
    public LaserBeam(OhCoconutsGameManager game, int eyeHeight, int crabCenterX) {
        super(game, crabCenterX, eyeHeight, WIDTH, laserImage);
    }


    // -------------------------------------------------------------------------
    // METHODS
    // -------------------------------------------------------------------------

    /**
     * Returns the vertical height used for hit calculations.
     *
     * @return the y-coordinate plus the width of the laser
     */
    public int hittable_height() {
        return y + WIDTH;
    }

    /**
     * Updates the laser beam's position on each game tick.
     * The laser moves upward by 10 pixels per tick.
     */
    @Override
    public void step() {
        y -= 10; // Move laser upward
    }

    /**
     * Checks whether the laser beam has collided with a falling coconut.
     *
     * @return true if collision occurs; false otherwise
     */
    private boolean checkCollisionWithCoconut() {
        return false; // Placeholder logic for future collision detection
    }

    /**
     * Updates the destroyed coconuts score when the laser hits a coconut.
     *
     * @param scoreDelta the number of coconuts to add to the destroyed score
     */
    private void changeScore(int scoreDelta) {
        ScoreboardData.changeDestroyedCoconuts(scoreDelta);
    }
}
