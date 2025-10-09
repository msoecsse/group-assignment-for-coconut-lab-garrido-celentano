package coconuts;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;

// JavaFX Controller class for the game - generally, JavaFX elements (other than Image) should be here
public class GameController {

    /**
     * Time between calls to step() (ms)
     */
    private static final double MILLISECONDS_PER_STEP = 1000.0 / 30;
    private Timeline coconutTimeline;
    private boolean started = false;
    private static final Set<String> activeKeys = new HashSet<>();


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
            update();
            theGame.tryDropCoconut();
            theGame.advanceOneTick();
            if (theGame.done())
                coconutTimeline.pause();
        }));
        coconutTimeline.setCycleCount(Timeline.INDEFINITE);
    }

//    @FXML
//    public void onKeyPressed(KeyEvent keyEvent) {
//
//        if (keyEvent.getCode() == KeyCode.RIGHT && !theGame.done() && started) {
//            theGame.getCrab().crawl(10);
//        } else if (keyEvent.getCode() == KeyCode.LEFT && !theGame.done() && started) {
//            theGame.getCrab().crawl(-10);
//        } else if (keyEvent.getCode() == KeyCode.UP && !theGame.done() && started) {
//            theGame.tryShootLaser();
//        } else if (keyEvent.getCode() == KeyCode.SPACE) {
//            if (!started) {
//                coconutTimeline.play();
//                started = true;
//            } else {
//                coconutTimeline.pause();
//                started = false;
//            }
//        }
//    }

    public static void setupControls(Scene scene) {
        System.out.println("Controls set up");

        scene.setOnKeyPressed(e -> {
            System.out.println("Pressed: " + e.getCode());
            activeKeys.add(e.getCode().toString());});
        scene.setOnKeyReleased(e -> activeKeys.remove(e.getCode().toString()));
        System.out.println("Focus requested on gamePane");
    }

    private void update() {
         if (activeKeys.contains("LEFT")) {
            theGame.getCrab().crawl(-10);
        }
        if (activeKeys.contains("RIGHT")) {
            theGame.getCrab().crawl(10);
        }
        if (activeKeys.contains("UP")) {
            theGame.tryShootLaser();
        }
        if (activeKeys.contains("SPACE")) {
            if (!started) {
                coconutTimeline.play();
                started = true;
            } else {
                coconutTimeline.pause();
                started = false;
            }
        }
    }

    public void requestFocusForGamePane() {
        gamePane.requestFocus();
        System.out.println("Focus requested on gamePane");
    }

}
