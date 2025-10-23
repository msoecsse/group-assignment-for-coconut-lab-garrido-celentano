package coconuts;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * -----------------------------------------------------------------------------
 * Class Name: GameController
 * Description: JavaFX controller for the Oh Coconuts game. Manages the game's
 *              UI elements, player input, and the main game loop (coconut drops
 *              and crab movement). All GUI-related functionality is here, while
 *              core game logic is delegated to OhCoconutsGameManager.
 *
 *              This class handles:
 *              - Key input for crab movement and laser shooting
 *              - Starting and pausing the game
 *              - Updating the UI labels for score and health
 *
 * @author  German Garrido-Lestache Belinchon
 *          garrido-lestachebeli
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public class GameController {

    // -------------------------------------------------------------------------
    // CONSTANTS
    // -------------------------------------------------------------------------

    /** Time between calls to step() in milliseconds (30 FPS). */
    private static final double MILLISECONDS_PER_STEP = 1000.0 / 30;

    // -------------------------------------------------------------------------
    // FXML UI ELEMENTS
    // -------------------------------------------------------------------------

    public Label coconutsDestroyed;
    public Label coconutsBeached;
    public Label health;

    @FXML
    private Pane gamePane;

    @FXML
    private Pane theBeach;

    // -------------------------------------------------------------------------
    // GAME STATE VARIABLES
    // -------------------------------------------------------------------------

    /** Timeline controlling coconut drops and game ticks. */
    private Timeline coconutTimeline;

    /** Tracks whether the game has started. */
    private boolean started = false;

    /** Stores currently pressed keys (not used extensively here). */
    private static final Set<KeyCode> activeKeys = new HashSet<>();

    /** Reference to the game manager handling game logic. */
    private OhCoconutsGameManager theGame;


    // -------------------------------------------------------------------------
    // INITIALIZATION
    // -------------------------------------------------------------------------

    /**
     * Initializes the game controller, creating the game manager, setting up
     * the main game loop, and initializing the UI labels.
     * <p>
     * This method is automatically called by JavaFX after the FXML is loaded.
     * </p>
     */
    @FXML
    public void initialize() {
        // Prevent initialization if health is zero
        if (health.getText().equals("0")) { return; }

        // Initialize the game manager with game pane dimensions
        theGame = new OhCoconutsGameManager(
                (int)(gamePane.getPrefHeight() - theBeach.getPrefHeight()),
                (int)theBeach.getPrefWidth(),
                gamePane
        );

        gamePane.setFocusTraversable(true); // Allows pane to receive key events

        // Set up timeline for automatic game ticks (coconuts dropping)
        coconutTimeline = new Timeline(
                new KeyFrame(Duration.millis(MILLISECONDS_PER_STEP), (e) -> {
                    theGame.tryDropCoconut();  // Attempt to drop a new coconut
                    theGame.advanceOneTick();  // Advance the game state by one tick
                    if (theGame.done())         // Pause if the game is over
                        coconutTimeline.pause();
                })
        );

        coconutTimeline.setCycleCount(Timeline.INDEFINITE); // Loop indefinitely
        coconutsDestroyed.setText("Coconuts Destroyed: 0"); // Initialize score label
    }


    // -------------------------------------------------------------------------
    // EVENT HANDLERS
    // -------------------------------------------------------------------------

    /**
     * Handles key press events for player control.
     * <p>
     * - LEFT / RIGHT arrows move the crab horizontally.
     * - UP arrow triggers a laser shot.
     * - SPACE starts or pauses the game.
     * </p>
     *
     * @param keyEvent the KeyEvent triggered by a player key press
     */
    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (theGame.getCrab() == null) { return; } // No crab to control

        if (keyEvent.getCode() == KeyCode.RIGHT && !theGame.done() && started) {
            theGame.getCrab().crawl(10); // Move crab right
        } else if (keyEvent.getCode() == KeyCode.LEFT && !theGame.done() && started) {
            theGame.getCrab().crawl(-10); // Move crab left
        } else if (keyEvent.getCode() == KeyCode.UP && !theGame.done() && started) {
            theGame.tryShootLaser(); // Fire laser
        } else if (keyEvent.getCode() == KeyCode.SPACE) {
            if (!started) {
                coconutTimeline.play(); // Start game
                started = true;
            } else {
                coconutTimeline.pause(); // Pause game
                started = false;
            }
        }
    }


    // -------------------------------------------------------------------------
    // UI UPDATE METHODS
    // -------------------------------------------------------------------------

    /**
     * Updates the label showing the number of coconuts that reached the beach.
     *
     * @param num the new number of beached coconuts
     */
    public void changeCoconutsBeached(int num) {
        coconutsBeached.setText("Coconuts Beached: " + num);
    }

    /**
     * Updates the label showing the number of coconuts destroyed by the crab.
     *
     * @param num the new number of coconuts destroyed
     */
    public void changeCoconutsDestroyed(int num) {
        coconutsDestroyed.setText("Coconuts Destroyed: " + num);
    }

    /**
     * Updates the health label in the UI.
     *
     * @param num the current health of the crab
     */
    public void changeHealth(int num) {
        health.setText("Health: " + num);
    }
}

