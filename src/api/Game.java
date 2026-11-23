// Game: the contract between engine and UI (title, description, choices, choose, isOver)
//
// Game(interface)
// + String getTitle()
// + String getPlayerName()
// + String getDescription()
// + List<ChoiceView> getChoices()
// + void choose(int choiceIndex)
// + boolean isOver()
package api;

import java.util.List;

/**
 * What the UI can see and do in a running game.
 *
 * The UI only uses this interface, never the classes in the engine package,
 * so the engine and the UI can be written and changed separately.
 */
public interface Game {

    /**
     * @return the title of the story being played.
     */
    String getTitle();

    /**
     * @return the player's name.
     */
    String getPlayerName();

    /**
     * @return the description of the scene the player is currently in.
     */
    String getDescription();

    /**
     * @return the choices in the current scene, in display order;
     *         an empty list when the game is over.
     */
    List<ChoiceView> getChoices();

    /**
     * Picks one of the current choices.
     *
     * @param choiceIndex which choice was picked (0 = first choice).
     *                    Players usually see choices numbered from 1, so the UI subtracts 1.
     * @throws IllegalStateException    if the game is already over,
     *                                  or the chosen scene hasn't been written yet
     * @throws IllegalArgumentException if choiceIndex is not a valid choice
     */
    void choose(int choiceIndex);

    /**
     * @return true if the player has reached an ending.
     */
    boolean isOver();
}
