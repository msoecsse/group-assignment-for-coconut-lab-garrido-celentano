package coconuts;

import java.util.ArrayList;

abstract class AbstractObserver implements Observer{
    ArrayList<Observer> observers = new ArrayList<>();
}
