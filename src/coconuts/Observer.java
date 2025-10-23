package coconuts;

/**
 * -----------------------------------------------------------------------------
 * Interface Name: Observer
 * Description: Defines the observer in the Observer design pattern for the
 *              Oh Coconuts game. Classes that implement this interface can
 *              be notified when a HitEvent or other observable event occurs.
 *              <p>
 *              The default update() method does nothing, allowing implementing
 *              classes to override it only if they need to respond to events.
 *              </p>
 *
 * @author  Dominic Celentano
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public interface Observer {

    /**
     * Called when the observed subject changes. Default implementation does nothing.
     * Implementing classes should override this method to respond to notifications.
     */
    public default void update() {
        // Default implementation does nothing
    }
}
