package coconuts;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardData {
    private static int beachedCoconuts = 0;
    private static int destroyedCoconuts = 0;
    private static int health = 50;

    public void updateScore(){
        Scoreboard.update(beachedCoconuts,destroyedCoconuts,health);
    }

    public static int getBeachedCoconuts() {
        return beachedCoconuts;
    }

    public static int getDestroyedCoconuts() {
        return destroyedCoconuts;
    }

    public static int getHealth(){
        return health;
    }

    public static void changeBeachCoconuts(int deltaBeachedCoconuts) {
        beachedCoconuts+= deltaBeachedCoconuts;
    }

    public static void changeDestroyedCoconuts(int deltaDestroyedCoconuts) {
        destroyedCoconuts += deltaDestroyedCoconuts;
    }
    public static void changeHealth(int deltaHealth){
        if ((health + deltaHealth )<= 0){health = 0;return;}
        health += deltaHealth;
    }
}
