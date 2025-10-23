package coconuts;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * -----------------------------------------------------------------------------
 * Class Name: IslandObject
 * Description: Abstract base class for all objects in the Oh Coconuts game,
 *              including objects on the island and falling objects. Each object
 *              has a position, size, and optional image representation, and can
 *              interact with other island objects (e.g., hit detection).
 *              <p>
 *              Domain class: should not include GUI logic beyond the optional
 *              ImageView for representation.
 *              </p>
 *
 * @author  German Garrido-Lestache Belinchon
 *          garrido-lestachebeli
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public abstract class IslandObject {

    // -------------------------------------------------------------------------
    // FIELDS
    // -------------------------------------------------------------------------

    /** The width of the object in pixels. */
    protected final int width;

    /** Minimum distance in pixels for two objects to be considered touching. */
    final int minimumTouchingDistance = 35;

    /** Reference to the game manager containing this object. */
    protected final OhCoconutsGameManager containingGame;

    /** The horizontal and vertical coordinates of this object. */
    protected int x, y;

    /** Optional JavaFX ImageView used for rendering this object. */
    ImageView imageView = null;


    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Constructs a new IslandObject with the specified position, size, and image.
     *
     * @param game  the game manager that contains this object
     * @param x     the horizontal position of the object
     * @param y     the vertical position of the object
     * @param width the width of the object
     * @param image the image representing the object visually (can be null)
     */
    public IslandObject(OhCoconutsGameManager game, int x, int y, int width, Image image) {
        containingGame = game;
        if (image != null) {
            imageView = new ImageView(image); // Create ImageView for visual representation
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(width);
        }
        this.x = x;
        this.y = y;
        this.width = width;
        display(); // Update ImageView position
        // System.out.println(this + " left " + left() + " right " + right()); // Debug output
    }


    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    /**
     * Returns the ImageView associated with this object.
     *
     * @return the ImageView for rendering, or null if no image was provided
     */
    protected ImageView getImageView() {
        return imageView;
    }


    // -------------------------------------------------------------------------
    // DISPLAY METHODS
    // -------------------------------------------------------------------------

    /**
     * Updates the ImageView's layout position to match the object's coordinates.
     */
    public void display() {
        if (imageView != null) {
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
        }
    }


    // -------------------------------------------------------------------------
    // HIT DETECTION METHODS
    // -------------------------------------------------------------------------

    /**
     * Determines whether this object can be hit by other objects.
     *
     * @return true if this object is a Crab, Coconut, or LaserBeam
     */
    public boolean isHittable() {
        return this instanceof Crab || this instanceof Coconut || this instanceof LaserBeam;
    }

    /**
     * Returns the vertical height used for hit calculations.
     *
     * @return the hittable height (default 0, can be overridden)
     */
    protected int hittable_height() {
        return 0;
    }

    /**
     * Determines whether this object is a ground object.
     *
     * @return true if this object is a Beach
     */
    public boolean isGroundObject() {
        return this instanceof Beach;
    }

    /**
     * Determines whether this object is a falling object.
     *
     * @return true if this object is a Coconut
     */
    public boolean isFalling() {
        return this instanceof Coconut;
    }

    /**
     * Determines whether this object can hit another object.
     *
     * @param other the other IslandObject to check against
     * @return true if this object can hit the other object, false otherwise
     */
    public boolean canHit(IslandObject other) {
        return (this instanceof Beach && other instanceof Coconut)
                || (this instanceof Crab && other instanceof Coconut)
                || (this instanceof LaserBeam && other instanceof Coconut);
    }

    /**
     * Determines whether this object is currently touching another object.
     *
     * @param other the other IslandObject to check for collision
     * @return true if the objects are close enough to be considered touching
     */
    public boolean isTouching(IslandObject other) {
        // Special case: Beach and Coconut
        if (this instanceof Beach && other instanceof Coconut && other.y >= 520) {
            return true;
        }

        int deltaX = Math.abs(other.x - this.x);
        int deltaY = Math.abs(other.y - this.y);

        return deltaY <= minimumTouchingDistance && deltaX <= minimumTouchingDistance;
    }


    // -------------------------------------------------------------------------
    // ABSTRACT METHODS
    // -------------------------------------------------------------------------

    /**
     * Updates the object's state on each game tick.
     * Must be implemented by subclasses.
     */
    public abstract void step();
}
