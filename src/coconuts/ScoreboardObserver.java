package coconuts;

public class ScoreboardObserver extends AbstractObserver{

    @Override
    public void update() {
        Scoreboard.update(getBeachedCoconuts(),getDestroyedCoconuts());
    }

    private int getBeachedCoconuts(){
        return ScoreboardData.getBeachedCoconuts();
    }

    private int getDestroyedCoconuts(){
        return ScoreboardData.getDestroyedCoconuts();
    }
}
