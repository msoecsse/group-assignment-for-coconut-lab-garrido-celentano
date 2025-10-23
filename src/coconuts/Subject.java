package coconuts;

/**
 * -----------------------------------------------------------------------------
 * Interface Name: Subject
 * Description: Defines the Subject in the Observer design pattern for the
 *              Oh Coconuts game. Classes implementing this interface can have
 *              observers attached, detached, and notified when events occur.
 * <p>
 * Author:   Dominic Celentano
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public interface Subject {

    /**
     * Attaches an observer to the subject.
     *
     * @param o the Observer to attach
     */
    public void attach(Observer o);

    /**
     * Detaches an observer from the subject.
     *
     * @param o the Observer to remove
     */
    public void detatch(Observer o);

    /**
     * Notifies all attached observers that the subject's state has changed.
     */
    public void notifyObservers();
}
