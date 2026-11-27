package engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {

    private StoryNode startNode;
    private StoryNode plotNode;
    private StoryNode endingNode;
    private GameEngine engine;

    @BeforeEach
    void setUp() {
        // Build a story covering start node, intermediate transitions, and ending node
        endingNode = new StoryNode(
                "end",
                "Congratulations! You found the hidden treasure.",
                List.of()
        );

        plotNode = new StoryNode(
                "plot1",
                "New roads in front of you.",
                List.of(new Choice("Claim the treasure", "end"))
        );

        startNode = new StoryNode(
                "start",
                "You are in a new world",
                List.of(
                        new Choice("Walk into path1", "plot1"),
                        new Choice("Choose unfinished path", "missingScene")
                )
        );

        Map<String, StoryNode> nodes = new HashMap<>();
        nodes.put("start", startNode);
        nodes.put("plot1", plotNode);
        nodes.put("end", endingNode);

        Story story = new Story("Fantastic Adventure", "start", nodes);
        engine = new GameEngine(story, "Player");
    }

    @Test
    void testInitialGameStarts() {
        // Engine begin with the story's start node when constructed
        assertEquals(startNode, engine.getCurrentNode());
        // Start node is not an ending, so the game should not be over yet
        assertFalse(engine.isOver());
    }

    @Test
    void testChooseMoveToTargetNode() {
        // Pick choice index (start from 0 as first option), whose target exists in Story
        engine.choose(0);

        // Current node updates to storyNode by the choice, and game is still ongoing
        assertEquals(plotNode, engine.getCurrentNode());
        assertFalse(engine.isOver());
    }

    @Test
    void testReachEndSetGameOver() {
        // When reach ending node by sequence of multiple choices, game should be over
        // Move from startNode to plotNode, then to endingNode
        engine.choose(0);
        engine.choose(0);

        assertEquals(endingNode, engine.getCurrentNode());
        assertTrue(engine.isOver());
    }

    @Test
    void testChooseThrowGameAlreadyOver() {
        // Reach ending first
        engine.choose(0);
        engine.choose(0);
        assertTrue(engine.isOver());

        // Calling choose after game is over throw IllegalStateException
        assertThrows(IllegalStateException.class, () -> engine.choose(0));
    }

    @Test
    void testChooseThrowIndexIsOutOfBounds() {
        // Negative index or index out of range of size of choices throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> engine.choose(-1));
        assertThrows(IllegalArgumentException.class, () -> engine.choose(2));

        // Failed choices should not change current node
        assertEquals(startNode, engine.getCurrentNode());
    }

    @Test
    void testChooseThrowTargetNodeDoesNotExist() {
        // Choice points to node yet completed, i.e. selected node not present in story's node map
        assertThrows(IllegalStateException.class, () -> engine.choose(1));

        // Player should remain at startNode instead of moving to missing scene
        assertEquals(startNode, engine.getCurrentNode());
    }

    @Test
    void testEngineWithStartNodeHasNoChoice() {
        // Build a story whose start node has no choices so an immediate ending
        StoryNode immediateEnd = new StoryNode("sudden", "The story ends immediately.", List.of());
        Map<String, StoryNode> singleNodeMap = new HashMap<>();
        singleNodeMap.put("sudden", immediateEnd);

        Story instantStory = new Story("Instant Adventure", "sudden", singleNodeMap);
        GameEngine instantEngine = new GameEngine(instantStory, "Player");

        // Since start node already an ending, so game should be over right away
        assertTrue(instantEngine.isOver());
    }
}