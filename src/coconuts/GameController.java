package coconuts;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;

// JavaFX Controller class for the game - generally, JavaFX elements (other than Image) should be here
public class GameController {

    /**
     * Time between calls to step() (ms)
     */
    private static final double MILLISECONDS_PER_STEP = 1000.0 / 30;
    public static Text coconutsDestroyed;
    public static Text coconutsBeached;
    private Timeline coconutTimeline;
    private Timeline controlsTimeline;
    private boolean started = false;
    private static final Set<KeyCode> activeKeys = new HashSet<>();


    @FXML
    private  Pane gamePane;
    @FXML
    private  Pane theBeach;
    private OhCoconutsGameManager theGame;


    @FXML
    public void initialize() {
        theGame = new OhCoconutsGameManager((int)(gamePane.getPrefHeight() - theBeach.getPrefHeight()),
                (int)theBeach.getPrefWidth(), gamePane);

        gamePane.setFocusTraversable(true);

                coconutTimeline = new Timeline(new KeyFrame(Duration.millis(MILLISECONDS_PER_STEP), (e) -> {
            theGame.tryDropCoconut();
            theGame.advanceOneTick();
            if (theGame.done())
                coconutTimeline.pause();
        }));
        coconutTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT && !theGame.done() && started) {
            theGame.getCrab().crawl(10);
        } else if (keyEvent.getCode() == KeyCode.LEFT && !theGame.done() && started) {
            theGame.getCrab().crawl(-10);
        } else if (keyEvent.getCode() == KeyCode.UP && !theGame.done() && started) {
            theGame.tryShootLaser();
        } else if (keyEvent.getCode() == KeyCode.SPACE) {
            if (!started) {
                coconutTimeline.play();
                started = true;
            } else {
                coconutTimeline.pause();
                started = false;
            }
        }
    }

    public static void changeCoconutsBeached(int num){
        coconutsBeached.setText("Coconuts Beached: "+ num);
    }

    public static void changeCoconutsDestroyed(int num){
        coconutsDestroyed.setText("Coconuts Destroyed: "+ num);
    }
}
