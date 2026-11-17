// StoryNode: one scene (ID, description, choices); a node with no choices is an ending
//
// StoryNode
// - String id
// - String description
// - List<Choice> choices
// + String getId()
// + String getDescription()
// + List<Choice> getChoices()// returns an unmodifiable list
// + boolean isEnding()// choices.isEmpty()
package engine;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents one scene in a text-based adventure game.
 *
 * A narrative scene may have one choice (e.g., "Next").
 * A decision scene may have multiple choices.
 * A scene with no choices is an ending.
 */
public class StoryNode {

    // Attributes
    private final String id;
    private final String description;
    // Available choices, each containing text, and a target node ID
    private final List<Choice> choices;

    // Constructor
    public StoryNode(String id, String description,
                     List<Choice> choices) {
        this.id = id;
        this.description = description;
        // Create copy of the list, so other parts of the program
        // can't add or remove from OG list; ≠ affecting the scene!
        this.choices = new ArrayList<>(choices);
    }

    /**
     * @return The unique ID of this, current, scene.
     */
    public String getId() {
        return id;
    }

    /**
     * @return The description of this, current, scene.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return an unmodifiable list of available choices.
     */
    public List<Choice> getChoices() {
        // The choices the classes can be read, but ≠ modify the elements!
        // Ex: 1) scene.getChoices().get(0) // gets first choice.
        // 2) scene.getChoices().add(newChoice); // Throws UnsupportedOpperationException to add anything.
        return Collections.unmodifiableList(choices);
    }

    /**
     * Returns true iff this scene has no choices,
     * indicating the end of the adventure.
     */
    public boolean isEnding() {
        return choices.isEmpty();
    }
}
