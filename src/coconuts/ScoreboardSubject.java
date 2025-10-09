package coconuts;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardSubject implements Subject{
    private List<Observer> observers;

    public ScoreboardSubject() {
        observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detatch(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
