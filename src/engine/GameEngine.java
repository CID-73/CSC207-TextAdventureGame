// GameEngine: implements Game and enforces the game rules; does no input or output
//
// GameEngine(game loop)
// - Story story
// - GameState state
// + StoryNode getCurrentNode()
// + String getTitle()
// + String getPlayerName()
// + String getDescription()
// + List<ChoiceView> getChoices()
// + void choose(int choiceIndex)
// + boolean isOver()
package engine;

import api.ChoiceView;
import api.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs the game: keeps track of where the player is and moves them
 * when they pick a choice. Does no input or output (that's the UI's job).
 */
public class GameEngine implements Game {

    // Attributes
    private final Story story;
    private final GameState state;

    /**
     * Starts a new game at the story's start scene.
     *
     * @param story      the story to play
     * @param playerName the player's name
     */
    public GameEngine(Story story, String playerName) {
        this.story = story;
        this.state = new GameState(playerName, story.getStartNode().getId());
    }

    /**
     * @return the scene the player is currently in.
     */
    public StoryNode getCurrentNode() {
        return story.getNode(state.getCurrentNodeId());
    }

    /**
     * @return the title of the story being played.
     */
    @Override
    public String getTitle() {
        return story.getTitle();
    }

    /**
     * @return the player's name.
     */
    @Override
    public String getPlayerName() {
        return state.getPlayerName();
    }

    /**
     * @return the description of the current scene.
     */
    @Override
    public String getDescription() {
        return getCurrentNode().getDescription();
    }

    /**
     * Turns the current scene's choices into ChoiceViews,
     * so the UI only sees the text, not the target node IDs.
     *
     * @return the current choices; an empty list when the game is over.
     */
    @Override
    public List<ChoiceView> getChoices() {
        List<ChoiceView> views = new ArrayList<>();
        for (Choice choice : getCurrentNode().getChoices()) {
            views.add(new ChoiceView(choice.getText()));
        }
        return views;
    }

    /**
     * Moves the player along the choice they picked.
     *
     * @param choiceIndex which choice was picked (0 = first choice)
     * @throws IllegalStateException    if the game is already over,
     *                                  or the chosen scene hasn't been written yet
     * @throws IllegalArgumentException if choiceIndex is not a valid choice
     */
    @Override
    public void choose(int choiceIndex) {
        if (isOver()) {
            throw new IllegalStateException("The game is over; no more choices can be made");
        }

        List<Choice> choices = getCurrentNode().getChoices();
        if (choiceIndex < 0 || choiceIndex >= choices.size()) {
            throw new IllegalArgumentException("Invalid choice: " + choiceIndex);
        }

        String targetId = choices.get(choiceIndex).getTargetNodeId();

        // A choice may point to a scene that hasn't been written yet.
        // Don't move the player there; stay in the current scene instead.
        if (story.getNode(targetId) == null) {
            throw new IllegalStateException("Scene not written yet: " + targetId);
        }

        state.moveTo(targetId);
    }

    /**
     * @return true if the player has reached an ending.
     */
    @Override
    public boolean isOver() {
        return getCurrentNode().isEnding();
    }
}