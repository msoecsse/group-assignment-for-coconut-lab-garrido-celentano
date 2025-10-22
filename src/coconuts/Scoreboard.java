package coconuts;


public class Scoreboard{
    private static GameController controller;

    public Scoreboard(GameController controller){
        this.controller = controller;
    }

    public static void update(int beachedCoconuts, int destroyedCoconuts, int health) {
        controller.changeCoconutsBeached(beachedCoconuts);
        controller.changeCoconutsDestroyed(destroyedCoconuts);
        controller.changeHealth(health);
    }



    public void reset() {
        update(0,0,100);
    }
}
