package coconuts;

/**
 * -----------------------------------------------------------------------------
 * Class Name: Beach
 * Description: Represents the beach area in the Oh Coconuts game where coconuts
 *              land after falling. This class extends IslandObject and
 *              contributes to game logic by catching coconuts and increasing
 *              the coconut score when they hit the beach.
 *              <p>
 *              Note: This is a domain class and should not include any
 *              JavaFX or GUI-related components.
 *              </p>
 *
 * @author  German Garrido-Lestache Belinchon
 *          garrido-lestachebeli
 * @version 1.0
 * @since   10/22/25
 * -----------------------------------------------------------------------------
 */
public class Beach extends IslandObject {

    /**
     * Constructs a Beach object at the specified vertical position within
     * the game world. The beach is placed at the bottom of the screen
     * (defined by the sky height) and spans the width of the island.
     *
     * @param game         reference to the game manager controlling overall game logic
     * @param skyHeight    the vertical coordinate representing the top of the beach
     * @param islandWidth  the total width of the island, determining beach length
     */
    public Beach(OhCoconutsGameManager game, int skyHeight, int islandWidth) {
        super(game, 0, skyHeight, islandWidth, null); // Initializes the beach with given dimensions and game reference
        // System.out.println("Beach at y = " + this.y); // Debug output for verifying beach position
    }

    /**
     * Called on each game tick to update the object's state.
     * The beach does not have dynamic behavior, so this method intentionally
     * performs no actions.
     */
    @Override
    public void step() {
        /* do nothing */
    }
}
