package coconuts;

import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * Class Name: OhCoconutsGameManager
 * Description: Manages the state and logic of the Oh Coconuts game. This includes
 *              tracking all island objects, handling object creation and removal,
 *              advancing the game tick, detecting collisions/hits, and updating
 *              the scoreboard.
 *              <p>
 *              Acts as the bridge between the domain objects (Crab, Coconut,
 *              Beach, LaserBeam) and the JavaFX view (Pane).
 *              </p>
 *
 * Author:   German Garrido-Lestache Belinchon
 *          garrido-lestachebeli
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public class OhCoconutsGameManager {

    // -------------------------------------------------------------------------
    // FIELDS
    // -------------------------------------------------------------------------

    /** All island objects in the game. */
    private final Collection<IslandObject> allObjects = new LinkedList<>();

    /** Hittable objects in the game, used for collision detection. */
    private final Collection<HittableIslandObject> hittableIslandSubjects = new LinkedList<>();

    /** Objects scheduled for removal after processing a tick. */
    private final Collection<IslandObject> scheduledForRemoval = new LinkedList<>();

    /** Dimensions of the game area. */
    private final int height, width;

    /** Interval in ticks between dropping coconuts. */
    private final int DROP_INTERVAL = 10;

    /** Maximum number of game ticks before stopping. */
    private final int MAX_TIME = 100;

    /** JavaFX pane representing the game area. */
    private Pane gamePane;

    /** Reference to the player's crab. */
    private Crab theCrab;

    /** Reference to the beach object. */
    private Beach theBeach;

    /** Number of coconuts currently in flight. */
    private int coconutsInFlight = 0;

    /** Current game tick. */
    private int gameTick = 0;

    /** Hit event used to notify observers about hits. */
    private HitEvent hitEvent = new HitEvent();


    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Constructs a new game manager with the specified height, width, and pane.
     * Initializes the crab, beach, and attaches observers to hit events.
     *
     * @param height   the vertical size of the game area
     * @param width    the horizontal size of the game area
     * @param gamePane the JavaFX Pane used to display game objects
     */
    public OhCoconutsGameManager(int height, int width, Pane gamePane) {
        this.height = height;
        this.width = width;
        this.gamePane = gamePane;

        // Initialize crab and register it
        this.theCrab = new Crab(this, height, width);
        registerObject(theCrab);
        gamePane.getChildren().add(theCrab.getImageView());

        // Initialize beach and register it
        this.theBeach = new Beach(this, height, width);
        registerObject(theBeach);
        if (theBeach.getImageView() != null)
            System.out.println("Unexpected image view for beach");

        // Attach scoreboard observer to hit events
        hitEvent.attach(new ScoreboardObserver());
    }


    // -------------------------------------------------------------------------
    // OBJECT MANAGEMENT
    // -------------------------------------------------------------------------

    /**
     * Registers an island object with the game.
     *
     * @param object the IslandObject to register
     */
    private void registerObject(IslandObject object) {
        allObjects.add(object);
        if (object.isHittable()) {
            hittableIslandSubjects.add((HittableIslandObject) object);
        }
    }


    // -------------------------------------------------------------------------
    // ACCESSORS
    // -------------------------------------------------------------------------

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Crab getCrab() {
        return theCrab;
    }


    // -------------------------------------------------------------------------
    // GAMEPLAY METHODS
    // -------------------------------------------------------------------------

    /**
     * Decrements the count of coconuts in flight when one is destroyed.
     */
    public void coconutDestroyed() {
        coconutsInFlight -= 1;
    }

    /**
     * Attempts to drop a new coconut according to the drop interval.
     * Increments the game tick on each call.
     */
    public void tryDropCoconut() {
        if (gameTick % DROP_INTERVAL == 0 && theCrab != null) {
            coconutsInFlight += 1;
            Coconut c = new Coconut(this, (int) (Math.random() * width));
            registerObject(c);
            gamePane.getChildren().add(c.getImageView());
        }
        gameTick++;
    }

    /**
     * Fires a laser from the crab if it exists.
     * Increments the game tick on each call.
     */
    public void tryShootLaser() {
        if (theCrab != null) {
            LaserBeam l = new LaserBeam(this, theCrab.y + 25, theCrab.x + 25);
            registerObject(l);
            gamePane.getChildren().add(l.getImageView());
        }
        gameTick++;
    }

    /**
     * Removes the crab from the game and hides its image.
     */
    public void killCrab() {
        theCrab.getImageView().setVisible(false);
        theCrab = null;
    }


    // -------------------------------------------------------------------------
    // GAME LOOP
    // -------------------------------------------------------------------------

    /**
     * Advances the game state by one tick:
     * - Updates all objects
     * - Checks collisions and updates scores
     * - Removes objects scheduled for deletion
     * - Ends game if health reaches 0
     */
    public void advanceOneTick() {
        // Update all objects
        for (IslandObject o : allObjects) {
            o.step();
            o.display();
        }

        // Check collisions and collect objects to remove
        scheduledForRemoval.clear();
        for (IslandObject thisObj : allObjects) {
            for (HittableIslandObject hittableObject : hittableIslandSubjects) {
                if (thisObj.canHit(hittableObject) && thisObj.isTouching(hittableObject)) {
                    switch (thisObj) {
                        case Crab crab when hittableObject instanceof Coconut -> ScoreboardData.changeHealth(-5);
                        case LaserBeam laserBeam when hittableObject instanceof Coconut -> ScoreboardData.changeDestroyedCoconuts(1);
                        case Beach beach when hittableObject instanceof Coconut -> ScoreboardData.changeBeachCoconuts(1);
                        default -> { }
                    }

                    hitEvent.notifyObservers();
                    scheduledForRemoval.add(hittableObject);
                    gamePane.getChildren().remove(hittableObject.getImageView());
                }
            }
        }

        // Remove scheduled objects
        for (IslandObject thisObj : scheduledForRemoval) {
            allObjects.remove(thisObj);
            if (thisObj instanceof HittableIslandObject) {
                hittableIslandSubjects.remove((HittableIslandObject) thisObj);
            }
        }
        scheduledForRemoval.clear();

        // Handle game over: remove crab and all laser beams
        if (ScoreboardData.getHealth() <= 0 && theCrab != null) {
            killCrab();

            List<IslandObject> lasersToRemove = new ArrayList<>();
            for (IslandObject o : allObjects) {
                if (o instanceof LaserBeam) {
                    lasersToRemove.add(o);
                }
            }

            for (IslandObject o : lasersToRemove) {
                allObjects.remove(o);
                hittableIslandSubjects.remove(o);
                gamePane.getChildren().remove(o.getImageView());
            }
        }
    }


    // -------------------------------------------------------------------------
    // DELETION SCHEDULING
    // -------------------------------------------------------------------------

    /**
     * Marks an island object for removal at the end of the tick.
     *
     * @param islandObject the object to remove
     */
    public void scheduleForDeletion(IslandObject islandObject) {
        scheduledForRemoval.add(islandObject);
    }


    // -------------------------------------------------------------------------
    // GAME STATE CHECK
    // -------------------------------------------------------------------------

    /**
     * Determines whether the game is finished.
     *
     * @return true if no coconuts are in flight and max game ticks reached
     */
    public boolean done() {
        return coconutsInFlight == 0 && gameTick >= MAX_TIME;
    }
}

