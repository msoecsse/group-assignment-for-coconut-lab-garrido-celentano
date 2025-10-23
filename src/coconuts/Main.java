package coconuts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public Main() {
    }/**
     * -----------------------------------------------------------------------------
     * Class Name: ${NAME}
     * Description: ${Purpose}
     *
     * @author  German Garrido-Lestache Belinchon
     *          garrido-lestachebeli
     * @version 1.0
     * @since   ${Date}
     * -----------------------------------------------------------------------------
     */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("coconuts.fxml"));


        Parent root = loader.load();
        primaryStage.setTitle("A Lonely Beach");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


        GameController gameController = loader.getController();
        Scoreboard scoreboard = new Scoreboard(gameController);
    }
}
