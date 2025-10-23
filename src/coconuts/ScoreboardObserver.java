package coconuts;

/**
 * -----------------------------------------------------------------------------
 * Class Name: ScoreboardObserver
 * Description: Observes hit events in the game and updates the Scoreboard UI
 *              accordingly. Implements the Observer pattern by extending
 *              AbstractObserver.
 *
 * Author:   Dominic Celentano
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public class ScoreboardObserver extends AbstractObserver {

    /**
     * Called when the observed HitEvent notifies its observers.
     * Updates the Scoreboard with the latest game statistics.
     */
    @Override
    public void update() {
        Scoreboard.update(getBeachedCoconuts(), getDestroyedCoconuts(), getHealth());
    }

    /**
     * Retrieves the number of coconuts that reached the beach.
     *
     * @return the number of beached coconuts
     */
    private int getBeachedCoconuts() {
        return ScoreboardData.getBeachedCoconuts();
    }

    /**
     * Retrieves the number of coconuts destroyed by the crab.
     *
     * @return the number of destroyed coconuts
     */
    private int getDestroyedCoconuts() {
        return ScoreboardData.getDestroyedCoconuts();
    }

    /**
     * Retrieves the current health of the crab.
     *
     * @return the crab's health
     */
    private int getHealth() {
        return ScoreboardData.getHealth();
    }
}
