package coconuts;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.lang.classfile.Label;

public class Scoreboard {

    public static void update(int beachedCoconuts, int destroyedCoconuts) {
        GameController.changeCoconutsBeached(beachedCoconuts);
        GameController.changeCoconutsDestroyed(destroyedCoconuts);
    }


    public void reset() {
        update(0,0);
    }
}
