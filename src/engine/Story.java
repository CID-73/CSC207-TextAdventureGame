// Story: the whole story (title, start node, all nodes); only requires the start node to exist
package engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Represents the whole story of a text-based adventure game.
 *
 * A story holds all of its scenes, looked up by node ID,
 * and knows which scene the game starts from.
 * Only the start scene must exist when the story is created.
 * A choice may point to a scene that is written or generated later.
 */

public class Story {

    // Attributes
    private String title;
    private String startNodeId;
    private Map<String, StoryNode> nodes;

    public Story(String title, String startNodeId, Map<String, StoryNode> nodes) {
        this.title = title;
        this.startNodeId = startNodeId;
        // Keep the caller's map (no copy), so nodes added to it later
        this.nodes = (nodes != null) ? nodes : new HashMap<>();
        validate();
    }

    public String getTitle() {
        return title;
    }

    public StoryNode getStartNode() {
        return nodes.get(startNodeId);
    }

    public Optional<StoryNode> findNode(String nodeId) {
        return Optional.ofNullable(nodes.get(nodeId));
    }

    /**
     * @param nodeId the ID of the node to look up
     * @return the node with this ID, or null if it doesn't exist
     */
    public StoryNode getNode(String nodeId) {
        return nodes.get(nodeId);
    }

    // Only the start node is required. A choice may point to a node that doesn't exist yet,
    // because it can be written or generated later
    private void validate() {
        if (!nodes.containsKey(startNodeId)) {
            throw new IllegalArgumentException("Start node not found: " + startNodeId);
        }
    }
}
