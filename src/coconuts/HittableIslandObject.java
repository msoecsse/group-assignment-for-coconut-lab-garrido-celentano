package coconuts;

import javafx.scene.image.Image;

/**
 * -----------------------------------------------------------------------------
 * Class Name: HittableIslandObject
 * Description: Abstract class representing island objects that can be hit by
 *              other objects (e.g., coconuts or lasers). Serves as a base
 *              class for all objects that participate in hit events within
 *              the game.
 *              <p>
 *              Domain class: should not include JavaFX or GUI logic beyond
 *              the Image reference.
 *              </p>
 *
 * @author  German Garrido-Lestache Belinchon
 *          garrido-lestachebeli
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public abstract class HittableIslandObject extends IslandObject {

    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Constructs a new hittable island object with the specified position,
     * size, and image.
     *
     * @param game  reference to the game manager controlling game logic
     * @param x     the horizontal position of the object
     * @param y     the vertical position of the object
     * @param width the width of the object
     * @param image the image representing the object visually
     */
    public HittableIslandObject(OhCoconutsGameManager game, int x, int y, int width, Image image) {
        super(game, x, y, width, image); // Initialize base IslandObject
    }

    // -------------------------------------------------------------------------
    // METHODS
    // -------------------------------------------------------------------------

    /**
     * Determines if this object can be hit by another object.
     *
     * @return true because all instances of HittableIslandObject are hittable
     */
    public boolean isHittable() {
        return true;
    }
}
