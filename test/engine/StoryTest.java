package engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StoryTest {
    private StoryNode startNode;
    private Map<String, StoryNode> nodes;
    private Story story;

    @BeforeEach
    void setUp() {
        // Build a story with a valid start node and a choice pointing to a future node
        startNode = new StoryNode(
                "start",
                "You stand in front of a food truck.",
                List.of(new Choice("Ask for the menu", "plot1"))
        );
        nodes = new HashMap<>();
        nodes.put("start", startNode);

        story = new Story("Fantastic Adventure", "start",  nodes);
    }

    @Test
    void testConstructorStory() {
        // Verify title and start node are initialized properly
        assertEquals("Fantastic Adventure", story.getTitle());
        assertEquals(startNode, story.getStartNode());
    }

    @Test
    void testConstructorThrowStartNodeMissing() {
        // Story creation must throw IllegalArgumentException if startNodeId is not found in nodes
        Map<String, StoryNode> otherNodes = new HashMap<>();
        otherNodes.put("node_other", new StoryNode("node_other", "nodes elsewhere", List.of()));

        assertThrows(IllegalArgumentException.class, () -> new Story("Broken Story", "start", null));
        assertThrows(IllegalArgumentException.class, () -> new Story("Broken Story", "start", new HashMap<>()));
        assertThrows(IllegalArgumentException.class, () -> new Story("Broken Story", "start", otherNodes));
    }

    @Test
    void testGetTitle() {
        // Verify getTitle returns the story title
        assertEquals("Fantastic Adventure", story.getTitle());
    }

    @Test
    void testGetStartNode() {
        // Verify getStartNode returns the node matching startNodeId
        assertEquals(startNode, story.getStartNode());
    }

    @Test
    void testGetNodeReturnNodeOrNull() {
        // Existing node is returned, while unknown node ID returns null
        assertEquals(startNode, story.getNode("start"));
        assertNull(story.getNode("not exist"));
    }

    @Test
    void testFindNodeReturnsOptionalPresentOrEmpty() {
        // Existing nodes wrapped in Optional, while missing node returns empty Optional
        Optional<StoryNode> found = story.findNode("start");
        assertTrue(found.isPresent());
        assertEquals(startNode, found.get());

        assertTrue(story.findNode("not exist").isEmpty());
    }

    @Test
    void testNodeAddedLaterCanBeFound() {
        // Nodes added to the map of all nodes after construction should be discoverable
        StoryNode bridgeNode = new StoryNode("bridgeScene", "bridge about to crack", List.of());
        nodes.put("bridgeScene", bridgeNode);

        assertEquals(bridgeNode, story.getNode("bridgeScene"));
        assertEquals(Optional.of(bridgeNode), story.findNode("bridgeScene"));
    }
}
