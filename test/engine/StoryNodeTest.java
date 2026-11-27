package engine;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StoryNodeTest {
    @Test
    public void testNarrativeScene() {
        // Create a narrative scene (i.e., no decision choices, not ending scene!)
        StoryNode introduction = new StoryNode(
                "intro",
                "You wake up in a mysterious underwater kingdom.",
                List.of(new Choice("Next", "crossroads"))
        );

        // Assert-check the StoryNode's attributions and behaviour
        assertEquals("intro", introduction.getId());
        assertEquals("You wake up in a mysterious underwater kingdom.", introduction.getDescription());
        assertEquals(1, introduction.getChoices().size()); //double check this assert
        assertEquals("Next", introduction.getChoices().get(0).getText());
        assertEquals("crossroads", introduction.getChoices().get(0).getTargetNodeId());
        assertFalse(introduction.isEnding());
    }
    @Test
    public void testDecisionScene(){
        // Create a scene with multiple choices (i.e., decision scene)
        StoryNode crossroads = new StoryNode(
                "crossroads",
                "Two paths lie agead of you",
                List.of(new Choice("Enter the forest", "forest"),
                        new Choice("Visit the village", "village"))
        );

        // Assert-check both choices: forest and village
        assertEquals(2, crossroads.getChoices().size());

        assertEquals("Enter the forest", crossroads.getChoices().get(0).getText());
        assertEquals("forest", crossroads.getChoices().get(0).getTargetNodeId());
        assertEquals("Visit the village", crossroads.getChoices().get(1).getText());
        assertEquals("village", crossroads.getChoices().get(1).getTargetNodeId());
        assertFalse(crossroads.isEnding());
    }

    @Test
    public void testEndingScene() {
        // Create an ending scene
        StoryNode ending = new StoryNode(
                "victory",
                "You have saved the underwater kingdom!",
                List.of());

        // Assert-check that there is no choices,
        // meaning game over!
        assertTrue(ending.getChoices().isEmpty());
        assertTrue(ending.isEnding());
    }
    @Test
    public void testChoicesNotMutable() {
        StoryNode intro = new StoryNode(
                "intro", "You wake up on a strange bed.",
                List.of(new Choice("Next", "crossroads"))
        );

        // Check that the returned list cannot be modified
        try {
            intro.getChoices().clear(); // if able to clear all the choices
            // then throw this exception error!
            throw new AssertionError("Expected UnsupportedOperationException to be thrown!");
        } catch (UnsupportedOperationException expected) {
            //test passed! :D
        }
    }
    @Test
    public void testDefensiveCopy() {
        // Create an OG list
        List<Choice> originalChoices = new ArrayList<>();
        originalChoices.add(new Choice("Next", "crossroads"));
        // Set up the story node
        StoryNode intro = new StoryNode("introduction",
                "You wake staring at the kingdom.", originalChoices);

        // Modify the OG list AFTER creating the node!
        originalChoices.add(new Choice("Enter the cave", "cave"));

        // The StoryNode should still contain only one choice!
        assertEquals(1, intro.getChoices().size());
    }
}


