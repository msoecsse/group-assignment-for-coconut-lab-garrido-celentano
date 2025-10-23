package coconuts;

import java.util.ArrayList;
import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * Class Name: HitEvent
 * Description: Represents an event that can be "hit" in the game (e.g., a
 *              coconut hitting a target). This class serves as the Subject
 *              in the Observer design pattern, allowing observers to be
 *              notified when the event occurs.
 *              <p>
 *              Domain class: does not contain GUI or JavaFX logic beyond
 *              any optional Image references.
 *              </p>
 *
 * @author  German Garrido-Lestache Belinchon
 *          garrido-lestachebeli
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public class HitEvent extends AbstractSubject {

    // -------------------------------------------------------------------------
    // FIELDS
    // -------------------------------------------------------------------------

    /** List of observers that will be notified when this hit event occurs. */
    private final List<Observer> observers;


    // -------------------------------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------------------------------

    /**
     * Constructs a new HitEvent instance with an empty observer list.
     */
    public HitEvent() {
        observers = new ArrayList<>(); // Initialize observer collection
    }


    // -------------------------------------------------------------------------
    // OBSERVER PATTERN METHODS
    // -------------------------------------------------------------------------

    /**
     * Attaches an observer to this hit event.
     *
     * @param o the observer to add
     */
    @Override
    public void attach(Observer o) {
        observers.add(o); // Add observer to the list
    }

    /**
     * Detaches an observer from this hit event.
     *
     * @param o the observer to remove
     */
    @Override
    public void detatch(Observer o) {
        observers.remove(o); // Remove observer from the list
    }

    /**
     * Notifies all attached observers that this hit event has occurred.
     * Each observer's update() method will be called.
     */
    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(); // Notify each observer
        }
    }
}
