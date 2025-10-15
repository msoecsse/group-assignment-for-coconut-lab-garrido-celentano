package coconuts;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardData{
    private static int beachedCoconuts = 0;
    private static int destroyedCoconuts = 0;

    public void updateScore(){
        Scoreboard.update(beachedCoconuts,destroyedCoconuts);
    }

    public static int getBeachedCoconuts() {
        return beachedCoconuts;
    }

    public static int getDestroyedCoconuts() {
        return destroyedCoconuts;
    }

    public static void changeBeachCoconuts(int deltaBeachedCoconuts) {
        beachedCoconuts+= deltaBeachedCoconuts;
    }

    public static void changeDestroyedCoconuts(int deltaDestroyedCoconuts) {
        destroyedCoconuts += deltaDestroyedCoconuts;
    }
}
