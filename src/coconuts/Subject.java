package coconuts;

public interface Subject {
    public void attach(Observer o);
    public void detatch(Observer o);
    public void notifyObservers();
}